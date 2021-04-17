package br.com.banco.dsweb.resource;

import org.springframework.http.ResponseEntity;

import br.com.banco.dsweb.dto.AccountUpdateDTO;

public interface AccountResource {

	ResponseEntity<?> allAccount();
	ResponseEntity<?> findAccount(Long id);
//	void saveAccount(Account account)
	ResponseEntity<?> updateAccount( Long id, AccountUpdateDTO accountUpdateDTO);
	ResponseEntity<?> deleteAccount(Long id);
}
