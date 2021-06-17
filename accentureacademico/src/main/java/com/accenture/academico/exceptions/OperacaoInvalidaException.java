package com.accenture.academico.exceptions;

public class OperacaoInvalidaException extends RuntimeException{
	
	public OperacaoInvalidaException() { 
		super(); 
	}
	
	public OperacaoInvalidaException(String message) { 
		super(message);
	}; 
}
