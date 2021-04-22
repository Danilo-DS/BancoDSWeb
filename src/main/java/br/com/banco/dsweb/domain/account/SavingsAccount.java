package br.com.banco.dsweb.domain.account;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.com.banco.dsweb.domain.agency.Agency;
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
	
	public static SavingsAccount builder(AccountCreateDTO accountCreateDTO, Agency agency) {
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountNumber(generateNumber());
		savingsAccount.setAgency(agency);
		savingsAccount.setClient(accountCreateDTO.getClient());
		savingsAccount.setInterestRate(Rate.RATE_MONTH.tax);
		
		return savingsAccount;
	}
	
	private static String generateNumber() {
		String number = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
	        Integer n = random.nextInt(9);
	        number += n.toString();
	     }
		
	 return number.concat("7");
	}
	
}
