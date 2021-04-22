package br.com.banco.dsweb.dto.account;

import br.com.banco.dsweb.dto.client.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDTO {
	
	private String accountNumber;
	private String agency;
	private Double balance;
	private String typeAccount;
	private ClientDTO client;
}
