package br.com.banco.dsweb.dto.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WithdrawDepositDTO {
	
	private String agencyOrigin;
	private String accountOrigin;
	private Double valueOperation;
	
}
