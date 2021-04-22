package br.com.banco.dsweb.dto.extratc;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.banco.dsweb.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExtratcDTO {
	
	private String originAgency;
	private String originAccount;
	private String destinationAgency;
	private String destinationAccount;
	private LocalDate dateTransaction;
	@Enumerated(EnumType.STRING)
	private TypeOperation typeOperation;
	private String valueTransaction;
	private Double previousBalance;
	private Double balance;
}
