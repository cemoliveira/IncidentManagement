package br.univesp.incidentmanagement.domain.student.repository;

import br.univesp.incidentmanagement.domain.student.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(Long id);
    Page<Student> findAllIsActive(Pageable pageable);
}