package br.univesp.incidentmanagement.application.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserChangePasswordDTO(

        @NotNull
        Long id,

        @NotBlank
        String oldPassword,

        @NotBlank
        String newPassword) {
}