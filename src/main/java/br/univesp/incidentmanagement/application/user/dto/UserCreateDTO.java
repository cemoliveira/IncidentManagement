package br.univesp.incidentmanagement.application.user.dto;

import br.univesp.incidentmanagement.domain.user.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateDTO(

        @NotBlank
        String name,

        @NotBlank
        String login,

        @NotBlank
        String password,

        @NotNull
        Role role) {
}