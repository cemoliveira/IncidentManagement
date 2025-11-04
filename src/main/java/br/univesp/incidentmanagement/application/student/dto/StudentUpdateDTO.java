package br.univesp.incidentmanagement.application.student.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentUpdateDTO(

        @NotNull
        Long id,
        String name,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate) {
}