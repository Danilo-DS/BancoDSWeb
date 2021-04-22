package br.com.banco.dsweb.enums;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum TypeAccount {
	CURRENT_ACCOUNT ("CA"),
	SAVINGS_ACCOUNT ("SA");
	
	public String type;

}
