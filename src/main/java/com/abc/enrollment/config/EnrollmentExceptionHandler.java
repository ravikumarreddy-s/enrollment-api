package com.abc.enrollment.config;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.abc.enrollment.adapter.apiclient.model.ErrorResponse;

@ControllerAdvice
public class EnrollmentExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorResponse> error(Exception ex, WebRequest request) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().code("500").message("Enrollment API Generic Error").build(),
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<ErrorResponse> error(ConstraintViolationException ex, WebRequest request) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().code("400").message("One or more input values are invalid").build(),
				new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler({ MissingRequestHeaderException.class })
	public ResponseEntity<ErrorResponse> error(MissingRequestHeaderException ex, WebRequest request) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().code("400").message("One or more Header values are invalid").build(),
				new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}