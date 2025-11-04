package br.univesp.incidentmanagement.application.schoolclass.dto;

import br.univesp.incidentmanagement.domain.schoolclass.enums.Semester;
import br.univesp.incidentmanagement.domain.schoolclass.enums.Shift;
import jakarta.validation.constraints.NotNull;

public record SchoolClassUpdateDTO(

        @NotNull
        Long id,
        String name,
        Shift shift,
        Semester semester) {
}