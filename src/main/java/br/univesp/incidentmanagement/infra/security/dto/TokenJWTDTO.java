package br.univesp.incidentmanagement.infra.security.dto;

public record TokenJWTDTO(String tokenJWT, Long id, String login) {
}