package br.univesp.incidentmanagement.infra.persistence.student;

import br.univesp.incidentmanagement.domain.student.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStudentRepository extends JpaRepository<Student, Long> {

    @Query("""
        SELECT a
        FROM Aluno a
        WHERE
        a.active = true
    """)
    Page<Student> findAllIsActive(Pageable pageable);
}