package br.univesp.incidentmanagement.application.schoolclass.dto;

import br.univesp.incidentmanagement.domain.schoolclass.enums.Semester;
import br.univesp.incidentmanagement.domain.schoolclass.enums.Shift;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SchoolClassCreateDTO(

        @NotBlank
        String name,

        @NotNull
        Shift shift,

        @Min(2000)
        @Max(2100)
        @NotNull
        int year,

        @NotNull
        Semester semester) {
}