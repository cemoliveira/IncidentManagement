package br.univesp.incidentmanagement.infra.exception.incident;

public class IncidentAlreadyDeletedException extends RuntimeException {
    public IncidentAlreadyDeletedException(String message) {
        super(message);
    }
}