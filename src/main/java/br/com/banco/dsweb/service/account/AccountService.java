package br.com.banco.dsweb.service.account;

import java.util.List;

import br.com.banco.dsweb.domain.account.Account;
import br.com.banco.dsweb.dto.account.AccountCreateDTO;
import br.com.banco.dsweb.dto.account.AccountDTO;
import br.com.banco.dsweb.dto.account.AccountUpdateDTO;

public interface AccountService {
	
	List<AccountDTO> listAll();
	
	Account findByAccountId(Long id);
	
	void saveAccount(AccountCreateDTO accountCreateDTO);
	
	Account updateAccount(Long id, AccountUpdateDTO accountUpdateDTO);
	
	void deleteAccount(Long id);
	
	Account findByAccountAgency(String numberAccount, String numberAgency);
	
	void updateBalance(Double newBalance, Long accountId);
	
}
