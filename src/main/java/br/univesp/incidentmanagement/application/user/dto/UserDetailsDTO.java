package br.univesp.incidentmanagement.application.user.dto;

import br.univesp.incidentmanagement.domain.user.enums.Role;

public record UserDetailsDTO(
        Long id,
        String name,
        String login,
        Role role) {
}