package com.example.test03;

public class InsufficientFundsException extends RuntimeException {
	public InsufficientFundsException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;
}