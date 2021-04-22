package br.com.banco.dsweb.service.operation;

import br.com.banco.dsweb.dto.operation.TransactionResponseDTO;
import br.com.banco.dsweb.dto.operation.TransferDTO;
import br.com.banco.dsweb.dto.operation.WithdrawDepositDTO;

public interface OperationService {
	
	TransactionResponseDTO depositAccount(WithdrawDepositDTO deposit);
	
//	TransactionResponseDTO depositMyAccount(TransferDTO deposit);
	
	TransactionResponseDTO withdrawAccount(WithdrawDepositDTO withdraw);
	
	TransactionResponseDTO trasferAccount(TransferDTO transfer);
}
