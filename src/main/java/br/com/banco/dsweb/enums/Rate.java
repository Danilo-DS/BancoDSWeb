package br.com.banco.dsweb.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Rate {
	RATE_MONTH (0.002),
	RATE_TRANSF (0.001),
	RATE_NEGATIVA_BALANCE (0.005),
	NO_FEE (0.0);
	
	public Double tax; 
}
