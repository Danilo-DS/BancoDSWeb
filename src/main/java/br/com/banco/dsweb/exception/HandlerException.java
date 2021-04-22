package br.com.banco.dsweb.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.banco.dsweb.exception.account.AccountException;
import br.com.banco.dsweb.exception.client.ClientException;
import br.com.banco.dsweb.exception.operation.OperationException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<?> clientExpResponse(ClientException clientExp, WebRequest request){
		
		return super.handleExceptionInternal(clientExp, clientExp.getMessage(), new HttpHeaders(), clientExp.getHttpStatus(), request);		
	}
	
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<?> accountExpResponse(AccountException accountExp, WebRequest request){
		
		return super.handleExceptionInternal(accountExp, accountExp.getMessage(), new HttpHeaders(), accountExp.getHttpStatus(), request);		
	}
	
	@ExceptionHandler(OperationException.class)
	public ResponseEntity<?> operationExpResponse(OperationException operationExp, WebRequest request){
		
		return super.handleExceptionInternal(operationExp, operationExp.getMessage(), new HttpHeaders(), operationExp.getHttpStatus(), request);		
	}
}
