package br.com.banco.dsweb.service.operation;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.banco.dsweb.config.ModelConvert;
import br.com.banco.dsweb.domain.account.Account;
import br.com.banco.dsweb.domain.agency.Agency;
import br.com.banco.dsweb.domain.extratc.Extratc;
import br.com.banco.dsweb.dto.operation.TransactionResponseDTO;
import br.com.banco.dsweb.dto.operation.TransferDTO;
import br.com.banco.dsweb.dto.operation.WithdrawDepositDTO;
import br.com.banco.dsweb.enums.Rate;
import br.com.banco.dsweb.enums.TypeOperation;
import br.com.banco.dsweb.exception.operation.OperationException;
import br.com.banco.dsweb.service.account.AccountService;
import br.com.banco.dsweb.service.agency.AgencyService;
import br.com.banco.dsweb.service.extratc.ExtratcService;
import br.com.banco.dsweb.util.ConstantUtil;

@Service
public class OperationServiceImpl implements OperationService{
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ExtratcService extratcService;
	
	@Autowired
	private AgencyService agencyService;
	
	private Double previousBalance;
		
	@Override
	public TransactionResponseDTO depositAccount(WithdrawDepositDTO deposit) {
		
		Agency agencyOrigin = agencyService.findAgency(deposit.getAgencyOrigin());
		Account account = accountService.findByAccountAgency(deposit.getAccountOrigin(), agencyOrigin);
		
		Double newBalance;
		previousBalance = account.getBalance();
		
		if(previousBalance < 0) {
			
			newBalance = calculateTransaction(previousBalance, deposit.getValueOperation(), TypeOperation.DEPOSIT, Rate.RATE_NEGATIVA_BALANCE.tax);
			account.setBalance(newBalance);
			
		}
		else{
			
			newBalance = calculateTransaction(previousBalance, deposit.getValueOperation(), TypeOperation.DEPOSIT, Rate.NO_FEE.tax);
			account.setBalance(newBalance);
			
		}
		
		accountService.updateBalance(account.getBalance(), account.getId());
		Extratc extractAccount = Extratc.builder(account, TypeOperation.DEPOSIT, previousBalance, ConstantUtil.SUM + deposit.getValueOperation().toString()); 
		
		saveExtract(Arrays.asList(extractAccount));
		
		return MountTransactionResponseDTO(deposit,previousBalance, newBalance, TypeOperation.DEPOSIT);
	}
	
	@Override
	public TransactionResponseDTO withdrawAccount(WithdrawDepositDTO withdraw) {
		
		Agency agencyOrigin = agencyService.findAgency(withdraw.getAgencyOrigin());
		Account account = accountService.findByAccountAgency(withdraw.getAccountOrigin(), agencyOrigin);
		
		Double newBalance;
		previousBalance = account.getBalance();
		
		newBalance = calculateTransaction(previousBalance, withdraw.getValueOperation(), TypeOperation.WITHDRAW,Rate.NO_FEE.tax);
		
		account.setBalance(newBalance);
		accountService.updateBalance(account.getBalance(), account.getId());
		
		Extratc extractAccount = Extratc.builder(account, TypeOperation.WITHDRAW, previousBalance, ConstantUtil.SUBTRACT + withdraw.getValueOperation().toString());
		
		saveExtract(Arrays.asList(extractAccount));
		
		return MountTransactionResponseDTO(withdraw, previousBalance, newBalance, TypeOperation.WITHDRAW);
	}

	@Override
	public TransactionResponseDTO trasferAccount(TransferDTO transfer) {
		
		Agency agencyOrigin = agencyService.findAgency(transfer.getAgencyOrigin());
		Agency agencyDestine = agencyService.findAgency(transfer.getAgencyDestine());
		
		Account accountOrigin = accountService.findByAccountAgency(transfer.getAccountOrigin(), agencyOrigin);
		Account accountDestine = accountService.findByAccountAgency(transfer.getAccountDestine(), agencyDestine);
		
		previousBalance = accountOrigin.getBalance();
		Double newBalanceOrigin;
		Double newBalanceDestine;
		Double previousBalanceDestine = accountDestine.getBalance(); 
		
		
		verifySituationAccount(previousBalance, transfer.getValueOperation(), TypeOperation.TRANSFER);
		
		if(accountOrigin.getAgency().equals(accountDestine.getAgency())){
			newBalanceOrigin = calculateTransaction(accountOrigin.getBalance(), transfer.getValueOperation(), TypeOperation.WITHDRAW, Rate.NO_FEE.tax);
			accountOrigin.setBalance(newBalanceOrigin);
			
			newBalanceDestine = calculateTransaction(accountDestine.getBalance(), transfer.getValueOperation(), TypeOperation.TRANSFER, Rate.NO_FEE.tax);
			accountDestine.setBalance(newBalanceDestine);
			
		}
		else {
			
			newBalanceOrigin = calculateTransaction(accountOrigin.getBalance(), transfer.getValueOperation(), TypeOperation.WITHDRAW, Rate.RATE_TRANSF.tax);
			accountOrigin.setBalance(newBalanceOrigin);
			
			newBalanceDestine = calculateTransaction(accountDestine.getBalance(), transfer.getValueOperation(), TypeOperation.TRANSFER, Rate.NO_FEE.tax);
			accountDestine.setBalance(newBalanceDestine);
			
		}
		
		accountService.updateBalance(accountOrigin.getBalance(), accountOrigin.getId());
		accountService.updateBalance(accountDestine.getBalance(), accountDestine.getId());
		
		Extratc extractAccountOrigin = Extratc.builder(accountOrigin, accountDestine, TypeOperation.TRANSFER, previousBalance, ConstantUtil.SUBTRACT + transfer.getValueOperation().toString()); 
		Extratc extractAccountDestine = Extratc.builder(accountDestine, accountDestine,TypeOperation.TRANSFER, previousBalanceDestine, ConstantUtil.SUM + transfer.getValueOperation().toString()); 
		
		saveExtract(Arrays.asList(extractAccountOrigin, extractAccountDestine));
		
		return  MountTransactionResponseDTO(transfer, previousBalance, newBalanceOrigin, TypeOperation.TRANSFER);
	}
	
	private Double calculateTransaction(Double balancePresent, Double valueOperation, TypeOperation typeOperation, Double rate) {
		
		if(typeOperation.compareTo(TypeOperation.DEPOSIT) == 0 && rate.compareTo(Rate.NO_FEE.tax) > 0) {
			return valueOperation + balancePresent  * rate;
		}
		else if(typeOperation.compareTo(TypeOperation.DEPOSIT) == 0 || typeOperation.compareTo(TypeOperation.TRANSFER) == 0) {
			return balancePresent += valueOperation;
		}
		else if(typeOperation.compareTo(TypeOperation.WITHDRAW) == 0 && rate.compareTo(Rate.NO_FEE.tax) > 0) {
			return balancePresent - valueOperation * rate;
		}
		else{
			return balancePresent -= valueOperation;
		}
		
	}
	
	private void verifySituationAccount(Double balance, Double valueOperation, TypeOperation typeOperation) {
		
		if((valueOperation.compareTo(balance) > 0) && (typeOperation.compareTo(TypeOperation.TRANSFER) == 0)) {
			throw new OperationException(ConstantUtil.OPERATION_TRANSFER, HttpStatus.BAD_REQUEST);
		}
	}
	
	private void saveExtract(List<Extratc> extratcs) {
		extratcs.forEach(e -> extratcService.saveExtratc(e));
	}
	
	private <T> TransactionResponseDTO MountTransactionResponseDTO(T transaction, Double previousBalance, Double newbalance, TypeOperation typeOperacion) {
		
		DecimalFormat formaterMoney = new DecimalFormat("###,###,###,##0.00");
		
		TransactionResponseDTO response = ModelConvert.convertDto().map(transaction, TransactionResponseDTO.class);
		response.setPreviousBalance(formaterMoney.format(previousBalance));
		response.setTypeOperacion(typeOperacion);
		response.setBalance(formaterMoney.format(newbalance));
		return response;
		
	}
	
}
