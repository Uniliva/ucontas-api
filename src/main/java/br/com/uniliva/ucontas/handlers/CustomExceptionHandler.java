package br.com.uniliva.ucontas.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.uniliva.ucontas.exception.BusinessException;
import br.com.uniliva.ucontas.exception.NotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler({NotFoundException.class, BusinessException.class})
	ResponseEntity<StandardError> notFound(Exception e, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ValidationError> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation error");
		e.getBindingResult().getFieldErrors().stream().forEach(err -> error.addErros(err.getField(), err.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}