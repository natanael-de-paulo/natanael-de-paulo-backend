package br.java.social_network.infrastructure.exception;

public class HandleEntityAlreadyExistsException extends RuntimeException {
    public HandleEntityAlreadyExistsException(String message) {
        super(message);
    }
}
