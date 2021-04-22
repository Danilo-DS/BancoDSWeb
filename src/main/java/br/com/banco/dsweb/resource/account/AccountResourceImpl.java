package br.com.banco.dsweb.resource.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dsweb.dto.account.AccountCreateDTO;
import br.com.banco.dsweb.dto.account.AccountUpdateDTO;
import br.com.banco.dsweb.service.account.AccountService;
import io.swagger.annotations.Api;

@Api("Account")
@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountResourceImpl implements AccountResource{
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping
	@Override
	public ResponseEntity<?> allAccount() {
		return ResponseEntity.ok(accountService.listAll());
	}
	
	@GetMapping(value = "/{id}")
	@Override
	public ResponseEntity<?> findAccount(@PathVariable Long id) {
		return ResponseEntity.ok(accountService.findByAccountId(id));
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@Override
	public void saveAccount(@RequestBody AccountCreateDTO account) {
		accountService.saveAccount(account);
	}

	
	@PutMapping(value = "/{id}")
	@Override
	public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody AccountUpdateDTO accountUpdateDTO) {
		return ResponseEntity.ok(accountService.updateAccount(id, accountUpdateDTO));
	}
	 

	@DeleteMapping(value = "/{id}")
	@Override
	public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return ResponseEntity.noContent().build();
	}

}
