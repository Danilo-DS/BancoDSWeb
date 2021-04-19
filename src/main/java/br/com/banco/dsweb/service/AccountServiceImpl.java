package br.com.banco.dsweb.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.banco.dsweb.config.ModelConvert;
import br.com.banco.dsweb.domain.Account;
import br.com.banco.dsweb.domain.Client;
import br.com.banco.dsweb.domain.CurrentAccount;
import br.com.banco.dsweb.domain.SavingsAccount;
import br.com.banco.dsweb.dto.AccountDTO;
import br.com.banco.dsweb.dto.AccountUpdateDTO;
import br.com.banco.dsweb.enums.TypeAccount;
import br.com.banco.dsweb.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ClientService clientService;
	
	@Override
	@Transactional(readOnly = true)
	public List<AccountDTO> listAll() {
		return toListAccountDTO(accountRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Account findByAccountId(Long id) {
		return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Mensagem"));
	}
	
	@Override
	public void saveAccount(Account accountRequest) {
		
		Long idClient = accountRequest.getClient().getId();
		Client client = null;
		Account account = null; 
		
		if(accountRequest.getTypeAccount().compareTo(TypeAccount.CurrentAccount) == 0){

			client = clientService.findClientById(idClient);

			accountRequest.setClient(client);
			account = CurrentAccount.builder(accountRequest);
		}
		else if(accountRequest.getTypeAccount().compareTo(TypeAccount.SavingsAccount) == 0) {
			
			client = clientService.findClientById(idClient);

			accountRequest.setClient(client);
			account = SavingsAccount.builder(accountRequest);
		}
		else {
			throw new RuntimeException("Mensagem");
		}
		
		saveUpdateAccount(account);
	}
	
	@Override
	public Account updateAccount(Long id, AccountUpdateDTO accountUpdateDTO) {
		
		Account accountExisting = findByAccountId(id);
		Client client = clientService.findClientById(accountUpdateDTO.getClient().getId());
		
		accountExisting.setAccountNumber(StringUtils.hasText(accountUpdateDTO.getAccountNumber()) ? accountExisting.getAccountNumber() : accountUpdateDTO.getAccountNumber());
		accountExisting.setAgency(StringUtils.hasText(accountUpdateDTO.getAgency()) ? accountExisting.getAgency() : accountUpdateDTO.getAgency());
		accountExisting.setClient(client);
		
		saveUpdateAccount(accountExisting);
		
		return accountExisting;
	}

	@Override
	@Transactional
	public void deleteAccount(Long id) {
		if(verifyExistAccount(id)) {
			
			Account accountExisting = accountRepository.getOne(id);
			
			if(Optional.ofNullable(clientService.findClientById(accountExisting.getClient().getId())).isPresent()) {
				throw new RuntimeException("Mensagem");
			}
			
			accountRepository.deleteById(id);
		}
		else {
			throw new RuntimeException("Mensagem");
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Account findByAccountAgency(String numberAccount, String numberAgency) {
		return accountRepository.findByAccountAgency(numberAccount, numberAgency).orElseThrow(() -> new RuntimeException("Mensagem"));
	}
	
	@Override
	@Transactional
	public void updateBalance(Account account) {
		Account accountUpdated = findByAccountId(account.getId());
		accountUpdated.setBalance(account.getBalance());
		saveUpdateAccount(accountUpdated);
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
		if(accountRepository.existsById(id)) {
			return true;
		}
		else {
			return false;
		}
	}
	
//	private boolean verifyExistClient(Long id) {
//		if(accountRepository.findByClient(id)) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}

	

	
}
