package br.univesp.incidentmanagement.infra.exception.student;

public class StudentAlreadyDeletedException extends RuntimeException {
    public StudentAlreadyDeletedException(String message) {
        super(message);
    }
}
