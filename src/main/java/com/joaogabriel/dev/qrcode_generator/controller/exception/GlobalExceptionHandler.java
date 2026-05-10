package com.joaogabriel.dev.qrcode_generator.controller.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail invalidArgument(MethodArgumentNotValidException e) {
		String errors = e.getBindingResult().getFieldErrors().stream()
	            .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
	            .collect(Collectors.joining(", "));
		
		ProblemDetail message = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
		message.setTitle("Erro de validação");
		message.setDetail(errors);
		return message;
	}
}
