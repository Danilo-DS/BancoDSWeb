package br.com.banco.dsweb.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity(name = "TB_CURRENT_ACCOUNT")
@NoArgsConstructor
@Getter
@Setter
public class CurrentAccount extends Account {

	private static final long serialVersionUID = 1L;
	
	public static CurrentAccount builder(Account account) {
		CurrentAccount currentAccount = new CurrentAccount();
		currentAccount.setAccountNumber(account.getAccountNumber());
		currentAccount.setAgency(account.getAgency());
		currentAccount.setBalance(account.getBalance());
		currentAccount.setClient(account.getClient());
		
		return currentAccount;
	}
	
	
}
