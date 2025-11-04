package br.univesp.incidentmanagement.application.student.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentCreateDTO(

        @NotBlank
        String name,

        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate) {
}