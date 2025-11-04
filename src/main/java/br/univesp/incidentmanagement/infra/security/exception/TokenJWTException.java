package br.univesp.incidentmanagement.infra.security.exception;

public class TokenJWTException extends RuntimeException {
    public TokenJWTException(String message, Throwable cause) {
        super(message, cause);
    }
}