package br.com.banco.dsweb.dto.account;

import br.com.banco.dsweb.domain.client.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountUpdateDTO {

	private String accountNumber;
	private String agency;
	private Client client;
}
