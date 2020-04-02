package br.com.uniliva.ucontas.handlers;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	List<ValidationErrorMessage> errors = new ArrayList<ValidationErrorMessage>();

	public ValidationError(Integer code, String msg) {
		super(code, msg);
	}

	public List<ValidationErrorMessage> getErrors() {
		return errors;
	}

	public void addErros(String field, String mensage) {
		this.errors.add(new ValidationErrorMessage(field, mensage));
	}

}