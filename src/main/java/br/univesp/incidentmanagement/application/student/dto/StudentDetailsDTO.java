package br.univesp.incidentmanagement.application.student.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record StudentDetailsDTO(
        Long id,
        String imageUrl,
        String name,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate,
        Boolean active) {
}