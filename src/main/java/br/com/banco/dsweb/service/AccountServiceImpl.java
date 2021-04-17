package br.com.banco.dsweb.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	private ClientService clientService;
	
	@Override
	public List<AccountDTO> listAll() {
		return toListAccountDTO(accountRepository.findAll());
	}

	@Override
	public Account findByAccountId(Long id) {
		return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Mensagem"));
	}

	@Override
	public void saveAccount(Account accountRequest) {
		
		Long idClient = accountRequest.getClient().getId();
		Client client = null;
		Account account = null; 
		
		if(accountRequest.getTypeAccount().compareTo(TypeAccount.CurrentAccount) > 0){

			client = clientService.findClientById(idClient);

			accountRequest.setClient(client);
			account = CurrentAccount.builder(accountRequest);
		}
		else if(accountRequest.getTypeAccount().compareTo(TypeAccount.SavingsAccount) > 0) {
			
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
		
		accountExisting.setAccountNumber(accountUpdateDTO.getAccountNumber().isBlank() ? accountExisting.getAccountNumber() : accountUpdateDTO.getAccountNumber());
		accountExisting.setAgency(accountUpdateDTO.getAgency().isBlank() ? accountExisting.getAgency() : accountUpdateDTO.getAgency());
		accountExisting.setClient(client);
		
		saveUpdateAccount(accountExisting);
		
		return accountExisting;
	}

	@Override
	public void deleteAccount(Long id) {
		if(verifyExistAccount(id)) {
			
			Account accountExisting = accountRepository.getOne(id);
			
			if(!verifyExistClient(accountExisting.getClient().getId())) {
				throw new RuntimeException("Mensagem");
			}
			
			accountRepository.deleteById(id);
		}
		else {
			throw new RuntimeException("Mensagem");
		}
	}

	private List<AccountDTO> toListAccountDTO(List<Account> accounts){
		return accounts.stream().map(a -> ModelConvert.convertDto().map(a, AccountDTO.class)).collect(Collectors.toList());
	}
	
	private void saveUpdateAccount(Account account) {
		accountRepository.save(account);
	}
	
	private boolean verifyExistAccount(Long id) {
		if(accountRepository.existsById(id)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean verifyExistClient(Long id) {
		if(accountRepository.findByClient(id)) {
			return true;
		}
		else {
			return false;
		}
	}

	
}
