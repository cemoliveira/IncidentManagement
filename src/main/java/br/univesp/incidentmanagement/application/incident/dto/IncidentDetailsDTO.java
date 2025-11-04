package br.univesp.incidentmanagement.application.incident.dto;

import br.univesp.incidentmanagement.domain.incident.enums.Category;
import br.univesp.incidentmanagement.domain.incident.enums.Status;
import br.univesp.incidentmanagement.domain.incident.enums.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record IncidentDetailsDTO(
        Long id,
        String nameSchoolClass,
        String nameStudent,

        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime registerDate,
        Category category,
        Type type,
        String description,
        Status status,

        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime updateDate,
        Boolean deleted) {
}