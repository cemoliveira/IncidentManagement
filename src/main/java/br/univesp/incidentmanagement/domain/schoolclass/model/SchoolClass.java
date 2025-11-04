package br.univesp.incidentmanagement.domain.schoolclass.model;

import br.univesp.incidentmanagement.domain.schoolclass.enums.Semester;
import br.univesp.incidentmanagement.domain.schoolclass.enums.Shift;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Turma")
@Table(name = "turmas")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "turno")
    private Shift shift;

    @Column(name = "ano")
    private int year;

    @Enumerated(EnumType.STRING)
    @Column(name = "semestre")
    private Semester semester;

    @Column(name = "cancelada")
    private Boolean canceled;
}