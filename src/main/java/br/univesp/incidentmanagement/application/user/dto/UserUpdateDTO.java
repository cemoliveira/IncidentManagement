package br.univesp.incidentmanagement.application.user.dto;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(

        @NotNull
        Long id,
        String name,
        String login) {
}