package br.com.banco.dsweb.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dsweb.dto.TransferDepositDTO;
import br.com.banco.dsweb.dto.WithdrawDTO;
import br.com.banco.dsweb.service.OperationService;

@RestController
@RequestMapping(value = "/api/v1/operations")
public class OperationAccountResourceImpl implements OperationAccountResource{

	@Autowired
	private OperationService operationService;
		
	@PostMapping("/depositAccount")
	@Override
	public ResponseEntity<?> depositAccount(TransferDepositDTO deposit) {
		return ResponseEntity.ok(operationService.depositAccount(deposit));
	}

	@PostMapping("/deposit")
	@Override
	public ResponseEntity<?> depositMyAccount(TransferDepositDTO deposit) {
		return ResponseEntity.ok(operationService.depositMyAccount(deposit));
	}
	
	@PostMapping("/withdraw")
	@Override
	public ResponseEntity<?> withdrawAccount(WithdrawDTO withdraw) {
		return ResponseEntity.ok(operationService.withdrawAccount(withdraw));
	}

	@PostMapping("/transfer")
	@Override
	public ResponseEntity<?> transferAccount(TransferDepositDTO transfer) {
		return ResponseEntity.ok(operationService.trasferAccount(transfer));
	}
	
}
