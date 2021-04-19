package br.com.banco.dsweb.enums;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum TypeAccount {
	CurrentAccount ("CA"),
	SavingsAccount ("SA");
	
	public String type;
}
