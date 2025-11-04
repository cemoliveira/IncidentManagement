package br.univesp.incidentmanagement.application.user.dto;

import br.univesp.incidentmanagement.domain.user.enums.Role;
import jakarta.validation.constraints.NotNull;

public record UserUpdateRoleDTO(

        @NotNull
        Long id,

        @NotNull
        Role role) {
}