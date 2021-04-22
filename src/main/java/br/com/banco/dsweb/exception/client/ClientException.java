package br.com.banco.dsweb.exception.client;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClientException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	public ClientException(String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
	}
}
