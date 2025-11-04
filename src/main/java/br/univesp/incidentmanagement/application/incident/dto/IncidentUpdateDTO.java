package br.univesp.incidentmanagement.application.incident.dto;

import br.univesp.incidentmanagement.domain.incident.enums.Category;
import br.univesp.incidentmanagement.domain.incident.enums.Type;
import jakarta.validation.constraints.NotNull;

public record IncidentUpdateDTO(

        @NotNull
        Long id,
        Long idSchoolClass,
        Long idStudent,
        Category category,
        Type type,
        String description) {
}