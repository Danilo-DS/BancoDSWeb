package br.com.banco.dsweb.util;

public class ConstantUtil {
	
	/* ACCOUNT */
	public static final Double NEGATIVE_LIMIT = 1000.0;
	
	/*Type Operation*/
	public static final String SUM = "+ ";
	public static final String SUBTRACT = "- ";
	
	
	/*Exceptions*/
	
	//CLIENT
	public static final String CLIENT_NOT_FOUND = "Sorry, Client Not Found!";
	public static final String CLIENT_DELETE_BAD_REQUEST = "Informed client does not exist, impossible to delete!";
	public static final String CLIENT_UPDATE_BAD_REQUEST = "Could not update client!";
	public static final String CLIENT_TYPE_NONEXISTENT = "Type Account Nonexistent!"; 
	
	//ACCOUNT
	public static final String ACCOUNT_NOT_FOUND = "Sorry, Account Not Found!";
	public static final String ACCOUNT_DELETE_BAD_REQUEST = "Informed account does not exist, impossible to delete!";
	public static final String ACCOUNT_UPDATE_BAD_REQUEST = "We were unable to update your account!";
	public static final String ACCOUNT_TYPE_NONEXISTENT = "Type Account Nonexistent!"; 
	
	//OPERATION
	public static final String OPERATION_TRANSFER = "Transaction value greater than the account balance";
	public static final String OPERATION_NEGATIVE_LIMIT = "Negative withdrawal limit exceeded";
	
	/*Swagger Documentation*/
	public static final String BASE_PACKAGE = "br.com.banco.dsweb.resource";
	public static final String PATHS = "/api/*";
	
	public static final String TITLE = "Bank DS-WEB";
	public static final String DESCRIPTION = "DS low interest bank is here!";
	public static final String LICENSE = "₢copyright";
	public static final String CONTACT_NAME = "Danilo Silva";
	public static final String CONTACT_URL = "https://github.com/Danilo-DS";
	public static final String CONTACT_EMAIL = "danilosyllva.16@gmail.com";
	
}
