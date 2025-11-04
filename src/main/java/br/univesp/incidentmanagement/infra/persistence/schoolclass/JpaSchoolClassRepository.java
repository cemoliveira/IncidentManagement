package br.univesp.incidentmanagement.infra.persistence.schoolclass;

import br.univesp.incidentmanagement.domain.schoolclass.model.SchoolClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSchoolClassRepository extends JpaRepository<SchoolClass, Long> {

    @Query("""
        SELECT t
        FROM Turma t
        WHERE
        t.canceled = false
    """)
    Page<SchoolClass> findAllNotCanceled(Pageable pageable);

    @Query("""
        SELECT t
        FROM Turma t
        WHERE
        t.canceled = true
    """)
    Page<SchoolClass> findAllCanceled(Pageable pageable);
}