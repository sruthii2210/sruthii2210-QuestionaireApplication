package com.questionaire.exception;

public class ConstraintViolationException extends NotFoundException{

	public ConstraintViolationException(String msg) {
		super(msg);
	}
}
