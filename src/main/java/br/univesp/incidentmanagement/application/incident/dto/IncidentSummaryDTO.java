package br.univesp.incidentmanagement.application.incident.dto;

import java.util.List;
import java.util.Map;

public record IncidentSummaryDTO(
        long waiting,
        long progressing,
        long active,
        long solved,
        long unsolved,
        long closed,
        List<Map<String, Object>> byCategory,
        List<Map<String, Object>> byType,
        List<Map<String, Object>> bySchooclass,
        List<Map<String, Object>> byStudent) {
}