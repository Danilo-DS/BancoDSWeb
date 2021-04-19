package br.com.banco.dsweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDepositDTO {
	
	private String agencyOrigin;
	private String accountOrigin;
	private String agencyDestine;
	private String accountDestine;
	private Double valueOperation;
}
