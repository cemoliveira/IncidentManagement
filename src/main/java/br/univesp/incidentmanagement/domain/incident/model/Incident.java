package br.univesp.incidentmanagement.domain.incident.model;

import br.univesp.incidentmanagement.domain.incident.enums.Category;
import br.univesp.incidentmanagement.domain.incident.enums.Status;
import br.univesp.incidentmanagement.domain.incident.enums.Type;
import br.univesp.incidentmanagement.domain.schoolclass.model.SchoolClass;
import br.univesp.incidentmanagement.domain.student.model.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Ocorrencia")
@Table(name = "ocorrencias")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turma")
    private SchoolClass schoolClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aluno")
    private Student student;

    @Column(name = "data_registro")
    private LocalDateTime registerDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private Type type;

    @Column(name = "descricao")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "data_atualizacao")
    private LocalDateTime updateDate;

    @Column(name = "excluida")
    private Boolean deleted;

    public void setStatus(Status status) {
        this.status = status;
        this.updateDate = LocalDateTime.now();
    }

    public void setDeleted() {
        this.deleted = true;
    }

    @PrePersist
    public void prePersist() {
        if (this.registerDate == null) {
            this.registerDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}