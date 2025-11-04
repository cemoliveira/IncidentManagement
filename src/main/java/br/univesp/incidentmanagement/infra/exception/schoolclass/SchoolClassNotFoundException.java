package br.univesp.incidentmanagement.infra.exception.schoolclass;

public class SchoolClassNotFoundException extends RuntimeException {
    public SchoolClassNotFoundException(String message) {
        super(message);
    }
}