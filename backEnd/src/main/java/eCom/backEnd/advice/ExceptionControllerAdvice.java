package eCom.backEnd.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import eCom.backEnd.message.ErrorResponse;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> EmployeeExceptionalHandler(Exception exception) {
		ErrorResponse error = new ErrorResponse(exception.getMessage());
		exception.printStackTrace();
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
