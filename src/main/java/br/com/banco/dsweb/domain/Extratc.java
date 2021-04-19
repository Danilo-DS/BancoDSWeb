package br.com.banco.dsweb.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.banco.dsweb.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_EXTRATC")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Extratc implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD")
	private Long id;
	
	@Column(name = "DATE_TRANSACTION", nullable = false)
	private LocalDate dateTransaction;
	
	@Column(name = "ORIGEM_AGENCY", length = 10, nullable = false)
	private String originAgency;
	
	@Column(name = "ORIGEM_ACCOUNT", length = 10, nullable = false)
	private String originAccount;
		
	@Column(name = "DESTINATION_AGENCY", length = 10)
	private String destinationAgency;
	
	@Column(name = "DESTINATION_ACCOUNT", length = 10)
	private String destinationAccount;

	@Column(name = "TYPE_TRANSACTION", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TypeOperation typeOperation;
	
	@Column(name = "VALUE_TRANSACTION", nullable = false)
	private String valueTransaction;
	
	@Column(name = "BALANCE_ACCOUNT_ORIGEM", nullable = false)
	private Double balance;
	
	public static Extratc builder(Account accountOrigin, Account accountDestine, TypeOperation operation, String valueTransaction) {
		return new Extratc(null , LocalDate.now(), accountOrigin.getAgency(), accountOrigin.getAccountNumber(),
							accountDestine.getAgency(), accountDestine.getAccountNumber(), operation, valueTransaction, accountOrigin.getBalance());
	}
}
