package br.com.banco.dsweb.resource;

import org.springframework.http.ResponseEntity;

import br.com.banco.dsweb.dto.TransferDepositDTO;
import br.com.banco.dsweb.dto.WithdrawDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Operation Account")
public interface OperationAccountResource {
	
	@ApiOperation(value = "Deposit Account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Deposit Really Success"),
			@ApiResponse(code = 500, message = "Unexpected Error"),
	})
	ResponseEntity<?> depositAccount(TransferDepositDTO deposit);
	ResponseEntity<?> depositMyAccount(TransferDepositDTO deposit);
	
	@ApiOperation(value = "Withdraw Account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Withdraw Really Success"),
			@ApiResponse(code = 500, message = "Unexpected Error"),
	})
	ResponseEntity<?> withdrawAccount(WithdrawDTO withdraw);
	
	@ApiOperation(value = "Transfer Account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Transfer Really Success"),
			@ApiResponse(code = 500, message = "Unexpected Error"),
	})
	ResponseEntity<?> transferAccount(TransferDepositDTO transfer);
}
