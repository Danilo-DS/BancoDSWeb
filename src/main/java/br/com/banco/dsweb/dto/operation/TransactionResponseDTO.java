package br.com.banco.dsweb.dto.operation;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.banco.dsweb.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(Include.NON_NULL)
public class TransactionResponseDTO {
	
	private String agencyOrigin;
	
	private String accountOrigin;
	
	private String agencyDestine;
	
	private String accountDestine;
	
	private String previousBalance;
	
	@Enumerated(EnumType.STRING)
	private TypeOperation typeOperacion;
	
	private String valueOperation;
	
	private String balance;
	
}
