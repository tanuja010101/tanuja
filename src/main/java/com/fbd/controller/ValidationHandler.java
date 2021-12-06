package com.fbd.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler

{
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String,String> error=new HashMap();
		ex.getBindingResult().getAllErrors().forEach((errors) ->{
			String fieldName=((FieldError)error).getField();
			String message=errors.getDefaultMessage();
			error.put(fieldName,message);


		});
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}



}
