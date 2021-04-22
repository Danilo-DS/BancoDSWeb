package br.com.banco.dsweb.exception.account;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	public AccountException(String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
	}

}
