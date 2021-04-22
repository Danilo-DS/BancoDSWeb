package br.com.banco.dsweb.resource.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dsweb.dto.operation.TransferDTO;
import br.com.banco.dsweb.dto.operation.WithdrawDepositDTO;
import br.com.banco.dsweb.service.operation.OperationService;
import io.swagger.annotations.Api;

@Api("Operation Account")
@RestController
@RequestMapping(value = "/api/v1/operations")
public class OperationAccountResourceImpl implements OperationAccountResource{

	@Autowired
	private OperationService operationService;
		
	@PostMapping("/deposit")
	@Override
	public ResponseEntity<?> depositAccount(@RequestBody WithdrawDepositDTO deposit) {
		return ResponseEntity.ok(operationService.depositAccount(deposit));
	}

	@Override
	public ResponseEntity<?> depositMyAccount(TransferDTO deposit) {
//		operationService.depositMyAccount(deposit);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/withdraw")
	@Override
	public ResponseEntity<?> withdrawAccount(@RequestBody WithdrawDepositDTO withdraw) {
		return ResponseEntity.ok(operationService.withdrawAccount(withdraw));
	}

	@PostMapping("/transfer")
	@Override
	public ResponseEntity<?> transferAccount(@RequestBody TransferDTO transfer) {
		return ResponseEntity.ok(operationService.trasferAccount(transfer));
	}
	
}
