package com.ohkey.app.Exception;

public class MultiCodeException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public MultiCodeException() {
        super("Exception : Several records with same code");
    }

}
