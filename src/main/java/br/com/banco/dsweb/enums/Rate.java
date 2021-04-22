package br.com.banco.dsweb.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Rate {
	RATE_MONTH (1.002),
	RATE_TRANSF (1.0001),
	RATE_NEGATIVA_BALANCE (1.005),
	NO_FEE (0.0);
	
	public Double tax; 
}
