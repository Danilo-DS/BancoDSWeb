package br.com.banco.dsweb.dto.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDTO {
	
	private String agencyOrigin;
	private String accountOrigin;
	private String agencyDestine;
	private String accountDestine;
	private Double valueOperation;
}
