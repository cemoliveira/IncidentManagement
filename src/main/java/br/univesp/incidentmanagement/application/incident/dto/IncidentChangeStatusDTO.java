package br.univesp.incidentmanagement.application.incident.dto;

import br.univesp.incidentmanagement.domain.incident.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record IncidentChangeStatusDTO(
        @NotNull
        Long id,
        Status status,

        @PastOrPresent
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime updateDate) {
}