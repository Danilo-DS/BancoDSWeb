package br.com.banco.dsweb.resource.operation;

import org.springframework.http.ResponseEntity;

import br.com.banco.dsweb.dto.operation.TransferDTO;
import br.com.banco.dsweb.dto.operation.WithdrawDepositDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


public interface OperationAccountResource {
	
	@ApiOperation(value = "Deposit Account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Deposit Really Success"),
			@ApiResponse(code = 500, message = "Unexpected Error"),
	})
	ResponseEntity<?> depositAccount(WithdrawDepositDTO deposit);
	
	ResponseEntity<?> depositMyAccount(TransferDTO deposit);
	
	@ApiOperation(value = "Withdraw Account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Withdraw Really Success"),
			@ApiResponse(code = 500, message = "Unexpected Error"),
	})
	ResponseEntity<?> withdrawAccount(WithdrawDepositDTO withdraw);
	
	@ApiOperation(value = "Transfer Account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Transfer Really Success"),
			@ApiResponse(code = 500, message = "Unexpected Error"),
	})
	ResponseEntity<?> transferAccount(TransferDTO transfer);
}
