package com.beta.exception;

public class VendorMgmtException extends RuntimeException{

	public VendorMgmtException(){
		super();
	}
	
	public VendorMgmtException(String message){
		super(message);
	}
	
	public VendorMgmtException(String message, Throwable t){
		super(message, t);
	}

}
