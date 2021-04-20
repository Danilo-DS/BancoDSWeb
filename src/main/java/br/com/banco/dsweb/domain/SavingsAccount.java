package br.com.banco.dsweb.domain;

import java.io.Serializable;

import javax.persistence.Column;

import br.com.banco.dsweb.enums.Rate;
import br.com.banco.dsweb.enums.TypeAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SavingsAccount extends Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "RATE_PER_MONTH")
	private Double interestRate;

	
	public static SavingsAccount builder(Account account) {
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountNumber(account.getAccountNumber());
		savingsAccount.setAgency(account.getAgency());
		savingsAccount.setBalance(account.getBalance());
		savingsAccount.setClient(account.getClient());
		savingsAccount.setTypeAccount(TypeAccount.SavingsAccount);
		savingsAccount.setInterestRate(Rate.RATE_MONTH.tax);
		
		return savingsAccount;
	}
	
	
}
