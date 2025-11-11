package br.univesp.incidentmanagement.application.incident.dto;

import br.univesp.incidentmanagement.domain.incident.enums.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record IncidentIniListDTO(
        Long id,
        String schoolClassName,
        String studentName,

        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime registerDate,
        Type type,
        String description) {
}