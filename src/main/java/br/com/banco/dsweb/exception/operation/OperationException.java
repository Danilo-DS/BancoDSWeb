package br.com.banco.dsweb.exception.operation;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OperationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	public OperationException(String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
	}
	
}
