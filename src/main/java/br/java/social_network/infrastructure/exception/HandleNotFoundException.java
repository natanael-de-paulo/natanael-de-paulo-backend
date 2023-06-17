package br.java.social_network.infrastructure.exception;

public class HandleNotFoundException extends RuntimeException {
    public HandleNotFoundException(String message){
        super(message);
    }
}
