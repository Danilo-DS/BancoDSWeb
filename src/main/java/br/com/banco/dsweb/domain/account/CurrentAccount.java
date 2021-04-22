package br.com.banco.dsweb.domain.account;

import java.util.Random;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.com.banco.dsweb.domain.agency.Agency;
import br.com.banco.dsweb.dto.account.AccountCreateDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value =  "CA")
@NoArgsConstructor
@Getter
@Setter
public class CurrentAccount extends Account {

	private static final long serialVersionUID = 1L;
	
	/*
	 * @Column(name = "MAX_NEGATIVE_LIMIT") private Double negativeLimit;
	 */
	
	public static CurrentAccount builder(AccountCreateDTO accountCreateDTO, Agency agency) {
		CurrentAccount currentAccount = new CurrentAccount();
		currentAccount.setAccountNumber(generateNumber());
		currentAccount.setAgency(agency);
		currentAccount.setClient(accountCreateDTO.getClient());
		return currentAccount;
	}
	
	private static String generateNumber() {
		String number = "";
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
	        Integer n = random.nextInt(9);
	        number += n.toString();
	     }
		
	 return number.concat("7");
	}
	
}
