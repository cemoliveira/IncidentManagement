package br.univesp.incidentmanagement.application.user.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(

        @NotBlank
        String login,

        @NotBlank
        String password) {
}