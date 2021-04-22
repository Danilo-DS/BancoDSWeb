package br.com.banco.dsweb.domain.extratc;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.banco.dsweb.domain.account.Account;
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
	
	@Column(name = "DATE_TRANSACTION", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDate dateTransaction;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACCOUNT_ID_ORIGIN", foreignKey = @ForeignKey(name = "ACCOUNT_ID_ORIGIN"))
	private Account account;
	
	@Column(name = "ORIGIN_AGENCY", length = 10, nullable = false)
	private String originAgency;
	
	@Column(name = "ORIGIN_ACCOUNT", length = 10, nullable = false)
	private String originAccount;
		
	@Column(name = "DESTINATION_AGENCY", length = 10)
	private String destinationAgency;
	
	@Column(name = "DESTINATION_ACCOUNT", length = 10)
	private String destinationAccount;

	@Column(name = "TYPE_TRANSACTION", nullable = false)
	@Enumerated(EnumType.STRING)
	private TypeOperation typeOperation;
	
	@Column(name = "VALUE_TRANSACTION", nullable = false)
	private String valueTransaction;
	
	@Column(name = "PREVIOUS_BALANCE", nullable = false)
	private Double previousBalance;
	
	@Column(name = "BALANCE_ACCOUNT_ORIGEM", nullable = false, columnDefinition = "DECIMAL(10,2)")
	private Double balance;
	
	public static Extratc builder(Account accountOrigin, Account accountDestine, TypeOperation operation, Double previousBalance, String valueTransaction) {
		return new Extratc(null , LocalDate.now(), accountOrigin, accountOrigin.getAgency(), accountOrigin.getAccountNumber(),
				accountDestine.getAgency(), accountDestine.getAccountNumber(), operation, valueTransaction, previousBalance, accountOrigin.getBalance());
	}
	
	public static Extratc builder(Account accountOrigin, TypeOperation operation,Double previousBalance, String valueTransaction) {
		return new Extratc(null , LocalDate.now(), accountOrigin, accountOrigin.getAgency(), accountOrigin.getAccountNumber(),
				null, null, operation, valueTransaction, previousBalance, accountOrigin.getBalance());
	}
}
