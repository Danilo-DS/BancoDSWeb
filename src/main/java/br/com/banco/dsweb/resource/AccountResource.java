package br.com.banco.dsweb.resource;

import org.springframework.http.ResponseEntity;

import br.com.banco.dsweb.domain.Account;
import br.com.banco.dsweb.dto.AccountUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Account")
public interface AccountResource {
	
	@ApiOperation(value = "Return List Account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List Account Success"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> allAccount();
	
	@ApiOperation(value = "Return Account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Account Found Success"),
			@ApiResponse(code = 404, message = "Account Not Found Error"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> findAccount(Long id);
	
	@ApiOperation(value = "Save Account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Save Account Success"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	void saveAccount(Account account);
	
	@ApiOperation(value = "Update Account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Update Account Success"),
			@ApiResponse(code = 400, message = "Sorry, Bad Request Error"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> updateAccount( Long id, AccountUpdateDTO accountUpdateDTO);
	
	@ApiOperation(value = "Delete Account")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Delete Account Success"),
			@ApiResponse(code = 400, message = "Sorry, Bad Request Error"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> deleteAccount(Long id);
}
