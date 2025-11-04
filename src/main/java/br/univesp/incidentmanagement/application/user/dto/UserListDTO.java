package br.univesp.incidentmanagement.application.user.dto;

import br.univesp.incidentmanagement.domain.user.enums.Role;

public record UserListDTO(
        Long id,
        String name,
        Role role) {
}