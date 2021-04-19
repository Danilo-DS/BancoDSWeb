package br.com.banco.dsweb.service;

import br.com.banco.dsweb.dto.TransactionResponseDTO;
import br.com.banco.dsweb.dto.TransferDepositDTO;
import br.com.banco.dsweb.dto.WithdrawDTO;

public interface OperationService {
	
	TransactionResponseDTO depositAccount(TransferDepositDTO deposit);
	
	TransactionResponseDTO depositMyAccount(TransferDepositDTO deposit);
	
	TransactionResponseDTO withdrawAccount(WithdrawDTO withdraw);
	
	TransactionResponseDTO trasferAccount(TransferDepositDTO transfer);
}
