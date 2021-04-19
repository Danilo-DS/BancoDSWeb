package br.com.banco.dsweb.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.banco.dsweb.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponseDTO {
	
	private String agencyOrigin;
	
	private String accountOrigin;
	
	private String agencyDestine;
	
	private String accountDestine;
	
	private Double previousBalance;
	
	@Enumerated(EnumType.STRING)
	private TypeOperation typeOperacion;
	
	@JsonIgnore
	private Double valueOperation;
	
	private String valueFormated;
	
	private Double balance;
	
}
