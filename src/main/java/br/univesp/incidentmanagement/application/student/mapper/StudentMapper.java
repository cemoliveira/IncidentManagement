package br.univesp.incidentmanagement.application.student.mapper;

import br.univesp.incidentmanagement.application.student.dto.StudentCreateDTO;
import br.univesp.incidentmanagement.application.student.dto.StudentDetailsDTO;
import br.univesp.incidentmanagement.application.student.dto.StudentListDTO;
import br.univesp.incidentmanagement.application.student.dto.StudentUpdateDTO;
import br.univesp.incidentmanagement.domain.student.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toEntity(StudentCreateDTO data) {
        return new Student(
                null,
                null,
                data.name(),
                data.birthDate(),
                true
        );
    }

    public StudentDetailsDTO toDetailsDTO(Student student) {
        return new StudentDetailsDTO(
                student.getId(),
                student.getImageUrl(),
                student.getName(),
                student.getBirthDate(),
                student.getActive()
        );
    }

    public StudentListDTO toListDTO(Student student) {
        return new StudentListDTO(
                student.getId(),
                student.getName(),
                student.getImageUrl()
        );
    }

    public void updateEntityFromDTO(StudentUpdateDTO data, Student student) {
        if(data.name() != null && !data.name().isEmpty()) {
            student.setName(data.name());
        }
        if(data.birthDate() != null) {
            student.setBirthDate(data.birthDate());
        }
    }
}