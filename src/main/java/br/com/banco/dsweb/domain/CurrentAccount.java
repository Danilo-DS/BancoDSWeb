package br.com.banco.dsweb.domain;

import br.com.banco.dsweb.enums.TypeAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CurrentAccount extends Account {

	private static final long serialVersionUID = 1L;
	
//	@Column(name = "MAX_NEGATIVE_LIMIT")
//	private Double negativeLimit = -1000.0;
	
	public static CurrentAccount builder(Account account) {
		CurrentAccount currentAccount = new CurrentAccount();
		currentAccount.setAccountNumber(account.getAccountNumber());
		currentAccount.setAgency(account.getAgency());
		currentAccount.setBalance(account.getBalance());
		currentAccount.setTypeAccount(TypeAccount.CurrentAccount);
		currentAccount.setClient(account.getClient());
		
		return currentAccount;
	}
	
	
}
