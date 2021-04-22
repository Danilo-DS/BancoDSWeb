package br.com.banco.dsweb.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.banco.dsweb.exception.account.AccountException;
import br.com.banco.dsweb.exception.agency.AgencyException;
import br.com.banco.dsweb.exception.client.ClientException;
import br.com.banco.dsweb.exception.operation.OperationException;
import br.com.banco.dsweb.util.ConstantUtil;

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
	
	@ExceptionHandler(AgencyException.class)
	public ResponseEntity<?> agencyExpResponse(AgencyException agencyExp, WebRequest request){
		
		return super.handleExceptionInternal(agencyExp, agencyExp.getMessage(), new HttpHeaders(), agencyExp.getHttpStatus(), request);		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> anyExpResponse(Exception exp, WebRequest request){
		
		return super.handleExceptionInternal(exp, ConstantUtil.ERROR, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);		
	}
}
