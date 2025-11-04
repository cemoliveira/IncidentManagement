package br.univesp.incidentmanagement.application.incident.mapper;

import br.univesp.incidentmanagement.application.incident.dto.*;
import br.univesp.incidentmanagement.domain.incident.enums.Status;
import br.univesp.incidentmanagement.domain.incident.model.Incident;
import br.univesp.incidentmanagement.domain.schoolclass.model.SchoolClass;
import br.univesp.incidentmanagement.domain.student.model.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class IncidentMapper {
    public Incident toEntity(
            IncidentCreateDTO data,
            SchoolClass schoolClass,
            Student student,
            LocalDateTime registerDate
    ) {
        return new Incident(
                null,
                schoolClass,
                student,
                registerDate,
                data.category(),
                data.type(),
                data.description(),
                Status.AGUARDANDO_ATENDIMENTO,
                null,
                false
        );
    }

    public IncidentDetailsDTO toDetailsDTO(Incident incident) {
        String schoolClassName = incident.getSchoolClass() != null ? incident.getSchoolClass().getName() : "";
        String studentName = incident.getStudent() != null ? incident.getStudent().getName() : "";
        return new IncidentDetailsDTO(
                incident.getId(),
                schoolClassName,
                studentName,
                incident.getRegisterDate(),
                incident.getCategory(),
                incident.getType(),
                incident.getDescription(),
                incident.getStatus(),
                incident.getUpdateDate(),
                incident.getDeleted()
        );
    }

    public IncidentIniListDTO toIniListDTO(Incident incident) {
        String schoolClassName = incident.getSchoolClass() != null ? incident.getSchoolClass().getName() : "";
        String studentName = incident.getStudent() != null ? incident.getStudent().getName() : "";
        return new IncidentIniListDTO(
                incident.getId(),
                schoolClassName,
                studentName,
                incident.getRegisterDate(),
                incident.getType(),
                incident.getDescription()
        );
    }

    public IncidentListDTO toListDTO(Incident incident) {
        String schoolClassName = incident.getSchoolClass() != null ? incident.getSchoolClass().getName() : "";
        String studentName = incident.getStudent() != null ? incident.getStudent().getName() : "";
        return new IncidentListDTO(
                incident.getId(),
                schoolClassName,
                studentName,
                incident.getType(),
                incident.getDescription(),
                incident.getStatus(),
                incident.getUpdateDate()
        );
    }

    public void updateEntityFromDTO(
            IncidentUpdateDTO data,
            Incident incident,
            SchoolClass schoolClass,
            Student student
    ) {
        if(data.idSchoolClass() != null) {
            incident.setSchoolClass(schoolClass);
        }
        if(data.idStudent() != null) {
            incident.setStudent(student);
        }
        if(data.category() != null) {
            incident.setCategory(data.category());
        }
        if(data.type() != null) {
            incident.setType(data.type());
        }
        if(data.description() != null && !data.description().isEmpty()) {
            incident.setDescription(data.description());
        }
    }
}