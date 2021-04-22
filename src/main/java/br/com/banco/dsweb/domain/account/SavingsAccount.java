package br.com.banco.dsweb.domain.account;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.com.banco.dsweb.dto.account.AccountCreateDTO;
import br.com.banco.dsweb.enums.Rate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("SA")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SavingsAccount extends Account {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "RATE_PER_MONTH")
	private Double interestRate;
	
	public static SavingsAccount builder(AccountCreateDTO accountCreateDTO) {
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountNumber(accountCreateDTO.getAccountNumber());
		savingsAccount.setAgency(accountCreateDTO.getAgency());
		savingsAccount.setClient(accountCreateDTO.getClient());
		savingsAccount.setInterestRate(Rate.RATE_MONTH.tax);
		
		return savingsAccount;
	}
	
}
