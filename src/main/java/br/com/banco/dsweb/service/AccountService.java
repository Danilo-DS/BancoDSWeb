package br.com.banco.dsweb.service;

import java.util.List;

import br.com.banco.dsweb.domain.Account;
import br.com.banco.dsweb.dto.AccountDTO;
import br.com.banco.dsweb.dto.AccountUpdateDTO;

public interface AccountService {
	
	List<AccountDTO> listAll();
	
	Account findByAccountId(Long id);
	
	void saveAccount(Account account);
	
	Account updateAccount(Long id, AccountUpdateDTO accountUpdateDTO);
	
	void deleteAccount(Long id);
}
