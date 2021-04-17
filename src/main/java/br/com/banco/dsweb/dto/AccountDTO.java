package br.com.banco.dsweb.dto;

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
}
