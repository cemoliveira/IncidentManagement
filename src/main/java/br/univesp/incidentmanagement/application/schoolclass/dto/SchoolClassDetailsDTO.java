package br.univesp.incidentmanagement.application.schoolclass.dto;

import br.univesp.incidentmanagement.domain.schoolclass.enums.Semester;
import br.univesp.incidentmanagement.domain.schoolclass.enums.Shift;

public record SchoolClassDetailsDTO(
        Long id,
        String name,
        Shift shift,
        int year,
        Semester semester,
        Boolean canceled) {
}