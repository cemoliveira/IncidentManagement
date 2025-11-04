package br.univesp.incidentmanagement.application.incident.dto;

import br.univesp.incidentmanagement.domain.incident.enums.Category;
import br.univesp.incidentmanagement.domain.incident.enums.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record IncidentCreateDTO(
        Long idSchoolClass,
        Long idStudent,

        @PastOrPresent
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime registerDate,

        @NotNull
        Category category,

        @NotNull
        Type type,

        @NotNull
        String description) {
}