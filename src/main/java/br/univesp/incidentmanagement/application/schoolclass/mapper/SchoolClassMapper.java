package br.univesp.incidentmanagement.application.schoolclass.mapper;

import br.univesp.incidentmanagement.application.schoolclass.dto.SchoolClassCreateDTO;
import br.univesp.incidentmanagement.application.schoolclass.dto.SchoolClassDetailsDTO;
import br.univesp.incidentmanagement.application.schoolclass.dto.SchoolClassListDTO;
import br.univesp.incidentmanagement.application.schoolclass.dto.SchoolClassUpdateDTO;
import br.univesp.incidentmanagement.domain.schoolclass.model.SchoolClass;
import org.springframework.stereotype.Component;

@Component
public class SchoolClassMapper {
    public SchoolClass toEntity(SchoolClassCreateDTO data) {
        return new SchoolClass(
                null,
                data.name(),
                data.shift(),
                data.year(),
                data.semester(),
                false
        );
    }

    public SchoolClassDetailsDTO toDeteailsDTO(SchoolClass schoolClass) {
        return new SchoolClassDetailsDTO(
                schoolClass.getId(),
                schoolClass.getName(),
                schoolClass.getShift(),
                schoolClass.getYear(),
                schoolClass.getSemester(),
                schoolClass.getCanceled()
        );
    }

    public SchoolClassListDTO toListDTO(SchoolClass schoolClass) {
        return new SchoolClassListDTO(
                schoolClass.getId(),
                schoolClass.getName(),
                schoolClass.getYear()
        );
    }

    public void updateEntityFromDTO(SchoolClassUpdateDTO data, SchoolClass schoolClass) {
        if(data.name() != null && !data.name().isEmpty()) {
            schoolClass.setName(data.name());
        }
        if(data.shift() != null) {
            schoolClass.setShift(data.shift());
        }
        if(data.semester() != null) {
            schoolClass.setSemester(data.semester());
        }
    }
}