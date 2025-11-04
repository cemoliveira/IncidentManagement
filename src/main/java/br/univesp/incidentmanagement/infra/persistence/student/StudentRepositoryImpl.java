package br.univesp.incidentmanagement.infra.persistence.student;

import br.univesp.incidentmanagement.domain.student.model.Student;
import br.univesp.incidentmanagement.domain.student.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentRepositoryImpl implements StudentRepository {
    private final JpaStudentRepository jpaStudentRepository;

    public StudentRepositoryImpl(JpaStudentRepository jpaStudentRepository) {
        this.jpaStudentRepository = jpaStudentRepository;
    }

    @Override
    public Student save(Student student) {
        return jpaStudentRepository.save(student);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return jpaStudentRepository.findById(id);
    }

    @Override
    public Page<Student> findAllIsActive(Pageable pageable) {
        return jpaStudentRepository.findAllIsActive(pageable);
    }
}