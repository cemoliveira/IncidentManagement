package br.univesp.incidentmanagement.application.incident.dto;

import br.univesp.incidentmanagement.domain.incident.enums.Status;
import br.univesp.incidentmanagement.domain.incident.enums.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record IncidentListDTO(
        Long id,
        String schoolClassName,
        String studentName,
        Type type,
        String description,
        Status status,

        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime updateDate) {
}