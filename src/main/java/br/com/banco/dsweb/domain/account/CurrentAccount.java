package br.com.banco.dsweb.domain.account;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

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
	
	public static CurrentAccount builder(AccountCreateDTO accountCreateDTO) {
		CurrentAccount currentAccount = new CurrentAccount();
		currentAccount.setAccountNumber(accountCreateDTO.getAccountNumber());
		currentAccount.setAgency(accountCreateDTO.getAgency());
		currentAccount.setClient(accountCreateDTO.getClient());
		return currentAccount;
	}
	
}
