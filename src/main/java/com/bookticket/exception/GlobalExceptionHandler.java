package com.bookticket.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> map = new HashMap();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		for (FieldError error : fieldErrors) {
			map.put(error.getField(), error.getDefaultMessage());
		}

		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidOTPException.class)
	public ResponseEntity<?> handleDuplicatePhnoAndEmailException(InvalidOTPException ex) {
	AppError error=new AppError(ex.getMessage(), new Date());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(DuplicateSeatNoException.class)
	public ResponseEntity<?> handleDuplicateSeatNoException(DuplicateSeatNoException ex) {
	AppError error=new AppError(ex.getMessage(), new Date());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<AppError> handleDuplicateEmailException(DuplicateEmailException ex){
		
		AppError error=new AppError(ex.getMessage(), new Date());

		return new ResponseEntity<AppError>(error,HttpStatus.BAD_REQUEST);
	}

}
