package br.java.social_network.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiErrorMessage {
    private final Integer statusCode;
    private HttpStatus status;
    private String error;
    public ApiErrorMessage(Integer statusCode, HttpStatus status, String error) {
        this.statusCode = statusCode;
        this.status = status;
        this.error = error;
    }
}
