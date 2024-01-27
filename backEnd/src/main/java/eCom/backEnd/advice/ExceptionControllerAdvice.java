package eCom.backEnd.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import eCom.backEnd.message.ErrorResponse;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> validationError(MethodArgumentNotValidException ex, BindingResult result) {
		if (result.hasErrors()) {
			List<String> output = result.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.collect(Collectors.toList());
			return new ResponseEntity<Object>(output, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> EmployeeExceptionalHandler(Exception exception) {
		ErrorResponse error = new ErrorResponse(exception.getMessage());
		exception.printStackTrace();
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
