package br.com.banco.dsweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WithdrawDTO {
	
	private String agencyOrigin;
	private String accountOrigin;
	private Double valueOperation;
	//private Double balance;
	
}
