package br.com.banco.dsweb.dto;

import br.com.banco.dsweb.domain.Client;
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
