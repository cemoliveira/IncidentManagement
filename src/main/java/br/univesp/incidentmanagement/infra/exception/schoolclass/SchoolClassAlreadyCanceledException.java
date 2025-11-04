package br.univesp.incidentmanagement.infra.exception.schoolclass;

public class SchoolClassAlreadyCanceledException extends RuntimeException {
    public SchoolClassAlreadyCanceledException(String message) {
        super(message);
    }
}