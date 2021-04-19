package br.com.banco.dsweb.resource;

import org.springframework.http.ResponseEntity;

import br.com.banco.dsweb.dto.TransferDepositDTO;
import br.com.banco.dsweb.dto.WithdrawDTO;

public interface OperationAccountResource {
	
	ResponseEntity<?> depositAccount(TransferDepositDTO deposit);
	ResponseEntity<?> depositMyAccount(TransferDepositDTO deposit);
	ResponseEntity<?> withdrawAccount(WithdrawDTO withdraw);
	ResponseEntity<?> transferAccount(TransferDepositDTO transfer);
}
