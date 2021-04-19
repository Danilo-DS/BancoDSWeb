package br.com.banco.dsweb.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.banco.dsweb.config.ModelConvert;
import br.com.banco.dsweb.domain.Account;
import br.com.banco.dsweb.domain.Extratc;
import br.com.banco.dsweb.dto.TransactionResponseDTO;
import br.com.banco.dsweb.dto.TransferDepositDTO;
import br.com.banco.dsweb.dto.WithdrawDTO;
import br.com.banco.dsweb.enums.Rate;
import br.com.banco.dsweb.enums.TypeOperation;
import br.com.banco.dsweb.util.ConstantUtil;

@Service
public class OperationServiceImpl implements OperationService{
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ExtratcService extratcService;
	
	private Double previousBalance;
	
	//private Double negativeLimit;
	
	@Transactional(readOnly = true)
	@Override
	public TransactionResponseDTO depositAccount(TransferDepositDTO deposit) {
		Account accountOrigin = accountService.findByAccountAgency(deposit.getAccountOrigin(), deposit.getAgencyOrigin());
		Account accountDestine = accountService.findByAccountAgency(deposit.getAccountDestine(), deposit.getAgencyDestine());
		
		previousBalance = accountOrigin.getBalance();
		
		verifyBalanceAccount(previousBalance, deposit.getValueOperation(), TypeOperation.Deposit);
		
		accountOrigin.setBalance(calculateTransaction(accountOrigin.getBalance(), deposit.getValueOperation(), TypeOperation.Withdraw, Rate.NO_FEE.tax));
		accountDestine.setBalance(calculateTransaction(accountDestine.getBalance(), deposit.getValueOperation(), TypeOperation.Deposit, Rate.NO_FEE.tax));
			
		Extratc extractAccountOrigin = Extratc.builder(accountOrigin, accountDestine, TypeOperation.Deposit, ConstantUtil.subtract + deposit.getValueOperation().toString()); 
		Extratc extractAccountDestine = Extratc.builder(accountDestine, accountDestine,TypeOperation.Deposit, ConstantUtil.sum + deposit.getValueOperation().toString()); 
		
		saveExtract(Arrays.asList(extractAccountOrigin, extractAccountDestine));
		
		
		return MountTransactionDTO(deposit, previousBalance, TypeOperation.Deposit);
	}
	
	@Transactional(readOnly = true)
	@Override
	public TransactionResponseDTO depositMyAccount(TransferDepositDTO deposit) {
		
		Account account = accountService.findByAccountAgency(deposit.getAccountOrigin(), deposit.getAgencyOrigin());
		
		previousBalance = account.getBalance();
		
		if(previousBalance < 0) {
			account.setBalance(calculateTransaction(previousBalance, deposit.getValueOperation(), TypeOperation.Deposit, Rate.RATE_NEGATIVA_BALANCE.tax));
		}
		else{
			account.setBalance(calculateTransaction(previousBalance, deposit.getValueOperation(), TypeOperation.Deposit, Rate.RATE_NEGATIVA_BALANCE.tax));
		}
		
		Extratc extractAccount = Extratc.builder(account, null, TypeOperation.Deposit, ConstantUtil.sum + deposit.getValueOperation().toString()); 
		
		saveExtract(Arrays.asList(extractAccount));
		
		return MountTransactionDTO(deposit, previousBalance, TypeOperation.Deposit);
	}
	
	@Transactional(readOnly = true)
	@Override
	public TransactionResponseDTO withdrawAccount(WithdrawDTO withdraw) {
		
		Account account = accountService.findByAccountAgency(withdraw.getAccountOrigin(), withdraw.getAgencyOrigin());
		
		previousBalance = account.getBalance();
		
//		negativeLimit = account.
		
		account.setBalance(calculateTransaction(account.getBalance(), withdraw.getValueOperation(), TypeOperation.Withdraw,Rate.NO_FEE.tax));
		
		Extratc extractAccount = Extratc.builder(account, null, TypeOperation.Withdraw, ConstantUtil.subtract + withdraw.getValueOperation().toString());
		
		saveExtract(Arrays.asList(extractAccount));
		
		return MountTransactionDTO(withdraw, previousBalance, TypeOperation.Withdraw);
	}

	@Transactional(readOnly = true)
	@Override
	public TransactionResponseDTO trasferAccount(TransferDepositDTO transfer) {
		
		Account accountOrigin = accountService.findByAccountAgency(transfer.getAccountOrigin(), transfer.getAgencyOrigin());
		Account accountDestine = accountService.findByAccountAgency(transfer.getAccountDestine(), transfer.getAgencyDestine());
		
		previousBalance = accountOrigin.getBalance();
		
		verifyBalanceAccount(previousBalance, transfer.getValueOperation(), TypeOperation.Transfer);
		
		if(accountOrigin.getAgency().equals(accountDestine.getAgency())){
			accountOrigin.setBalance(calculateTransaction(accountOrigin.getBalance(), transfer.getValueOperation(), TypeOperation.Withdraw, Rate.NO_FEE.tax));
			accountDestine.setBalance(calculateTransaction(accountDestine.getBalance(), transfer.getValueOperation(), TypeOperation.Transfer, Rate.NO_FEE.tax));
		}
		else {
			accountOrigin.setBalance(calculateTransaction(accountOrigin.getBalance(), transfer.getValueOperation(), TypeOperation.Withdraw, Rate.RATE_TRANSF.tax));
			accountDestine.setBalance(calculateTransaction(accountDestine.getBalance(), transfer.getValueOperation(), TypeOperation.Transfer, Rate.NO_FEE.tax));
		}
		
		Extratc extractAccountOrigin = Extratc.builder(accountOrigin, accountDestine, TypeOperation.Transfer, ConstantUtil.subtract + transfer.getValueOperation().toString()); 
		Extratc extractAccountDestine = Extratc.builder(accountDestine, accountDestine,TypeOperation.Transfer, ConstantUtil.sum + transfer.getValueOperation().toString()); 
		
		saveExtract(Arrays.asList(extractAccountOrigin, extractAccountDestine));
		
		return  MountTransactionDTO(transfer, previousBalance, TypeOperation.Transfer);
	}
	
	private Double calculateTransaction(Double balancePresent, Double valueOperation, TypeOperation typeOperation, Double rate) {
		
		Double hasRate = rate != 0.0 ? rate : 0.0;
		
		if((typeOperation.compareTo(TypeOperation.Deposit) == 0) || (typeOperation.compareTo(TypeOperation.Transfer) == 0)){
			
			return balancePresent += valueOperation - (hasRate * valueOperation / 100);
		}
		else{
			return balancePresent -= valueOperation - (hasRate * valueOperation / 100);
		}
		
	}
	
	private void verifyBalanceAccount(Double balance, Double valueOperation, TypeOperation typeOperation) {
		
		//if((valueOperation.compareTo(balance) > 0) )
		
		if((valueOperation.compareTo(balance) > 0) && (typeOperation.compareTo(TypeOperation.Deposit) == 0)) {
			throw new RuntimeException("Mensagem");
		}
		if((valueOperation.compareTo(balance) > 0) && (typeOperation.compareTo(TypeOperation.Transfer) == 0)) {
			throw new RuntimeException("Mensagem");
		}
	}
	
	@Transactional
	private void saveExtract(List<Extratc> extratcs) {
		extratcs.forEach(e -> extratcService.saveExtratc(e));
	}
	
	private <T> TransactionResponseDTO MountTransactionDTO(T transaction, Double balance, TypeOperation typeOperacion) {
		TransactionResponseDTO response = ModelConvert.convertDto().map(transaction, TransactionResponseDTO.class);
		response.setPreviousBalance(balance);
		response.setTypeOperacion(typeOperacion);
		response.setValueFormated(response.getValueOperation().toString());
		response.setBalance(balance - response.getValueOperation());
		return response;
		
	}
	
}
