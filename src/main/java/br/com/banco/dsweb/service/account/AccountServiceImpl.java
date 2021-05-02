package br.com.banco.dsweb.service.account;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.banco.dsweb.config.ModelConvert;
import br.com.banco.dsweb.domain.account.Account;
import br.com.banco.dsweb.domain.account.CurrentAccount;
import br.com.banco.dsweb.domain.account.SavingsAccount;
import br.com.banco.dsweb.domain.agency.Agency;
import br.com.banco.dsweb.domain.client.Client;
import br.com.banco.dsweb.dto.account.AccountCreateDTO;
import br.com.banco.dsweb.dto.account.AccountDTO;
import br.com.banco.dsweb.dto.account.AccountUpdateDTO;
import br.com.banco.dsweb.enums.TypeAccount;
import br.com.banco.dsweb.exception.account.AccountException;
import br.com.banco.dsweb.repository.AccountRepository;
import br.com.banco.dsweb.service.agency.AgencyService;
import br.com.banco.dsweb.service.client.ClientService;
import br.com.banco.dsweb.service.extratc.ExtratcService;
import br.com.banco.dsweb.util.ConstantUtil;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AgencyService agencyService;
	
	@Autowired
	private ExtratcService extratcService;
	
	@Override
	@Transactional(readOnly = true)
	public List<AccountDTO> listAll() {
		return toListAccountDTO(accountRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Account findByAccountId(Long id) {
		return accountRepository.findById(id).orElseThrow(() -> new AccountException(ConstantUtil.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND));
	}
	
	@Override
	public void saveAccount(AccountCreateDTO accountCreateDTO) {
		
		Long idClient = accountCreateDTO.getClient().getId();
		Client client = null;
		Account account = null;
		Agency agency = null;
		
		if(accountCreateDTO.getTypeAccount().compareTo(TypeAccount.CURRENT_ACCOUNT) == 0){

			client = clientService.findClientById(idClient);
			agency = agencyService.findAgency(accountCreateDTO.getAgency().getNumberAgency());
			
			accountCreateDTO.setClient(client);
			account = CurrentAccount.builder(accountCreateDTO, agency);
		}
		else if(accountCreateDTO.getTypeAccount().compareTo(TypeAccount.SAVINGS_ACCOUNT) == 0) {
			
			client = clientService.findClientById(idClient);
			agency = agencyService.findAgency(accountCreateDTO.getAgency().getNumberAgency());
			
			accountCreateDTO.setClient(client);
			account = SavingsAccount.builder(accountCreateDTO, agency);
		}
		else {
			throw new AccountException(ConstantUtil.ACCOUNT_TYPE_NONEXISTENT, HttpStatus.BAD_REQUEST);
		}
		
		saveUpdateAccount(account);
	}
	
	@Override
	public Account updateAccount(Long id, AccountUpdateDTO accountUpdateDTO) {
		
		Account accountExisting = accountRepository.findById(id).orElseThrow(() -> new AccountException(ConstantUtil.ACCOUNT_UPDATE_BAD_REQUEST, HttpStatus.BAD_REQUEST));	
		Agency agency = agencyService.findAgency(accountUpdateDTO.getNumberAgency());
		accountExisting.setAgency(agency);
		
		saveUpdateAccount(accountExisting);
		
		return accountExisting;
	}

	@Override
	@Transactional
	public void deleteAccount(Long id) {
		if(!verifyExistAccount(id)) {
			throw new AccountException(ConstantUtil.ACCOUNT_DELETE_BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
		
		Account accountExisting = accountRepository.getOne(id);
		if(extratcService.existExtratc(accountExisting)) {
			extratcService.deleteExtratc(accountExisting);
		}
		else {
			accountRepository.deleteById(id);
		}
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Account findByAccountAgency(String numberAccount, Agency agency) {
		return accountRepository.findByAccountNumberAndAgency(numberAccount, agency).orElseThrow(() -> new AccountException(ConstantUtil.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND));
	}
	
	@Override
	@Transactional
	public void updateBalance(Double newBalance, Long accountId) {
		accountRepository.updateBalanceAccount(newBalance, accountId);
	}
	
	private List<AccountDTO> toListAccountDTO(List<Account> accounts){
		return accounts.stream().map(a -> ModelConvert.convertDto().map(a, AccountDTO.class)).collect(Collectors.toList());
	}
	
	@Transactional
	private void saveUpdateAccount(Account account) {
		accountRepository.save(account);
	}
	
	@Transactional(readOnly = true)
	private boolean verifyExistAccount(Long id) {
		return accountRepository.existsById(id);

	}
		
}
