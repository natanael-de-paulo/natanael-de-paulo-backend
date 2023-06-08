package br.java.social_network.infrastructure.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(HandleNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(HandleNotFoundException handleNotFoundException) {
        ApiErrorMessage errorResponse = new ApiErrorMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, handleNotFoundException.getMessage());
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(HandleEntityAlreadyExistsException.class)
    public ResponseEntity<Object> HandleEntityAlreadyExistsException(HandleEntityAlreadyExistsException handleEntityAlreadyExistsException) {
        ApiErrorMessage errorResponse = new ApiErrorMessage(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, handleEntityAlreadyExistsException.getMessage());
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }
}
