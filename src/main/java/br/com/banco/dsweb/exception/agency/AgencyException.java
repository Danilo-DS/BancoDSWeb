package br.com.banco.dsweb.exception.agency;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AgencyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;
	
	public AgencyException(String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
	}

}
