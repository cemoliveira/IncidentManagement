package br.univesp.incidentmanagement.infra.exception.incident;

public class IncidentNotFoundException extends RuntimeException {
    public IncidentNotFoundException(String message) {
        super(message);
    }
}