package br.univesp.incidentmanagement.application.incident.service;

import br.univesp.incidentmanagement.application.incident.dto.*;
import br.univesp.incidentmanagement.application.incident.mapper.IncidentMapper;
import br.univesp.incidentmanagement.domain.incident.enums.Category;
import br.univesp.incidentmanagement.domain.incident.enums.Status;
import br.univesp.incidentmanagement.domain.incident.enums.Type;
import br.univesp.incidentmanagement.domain.incident.model.Incident;
import br.univesp.incidentmanagement.domain.incident.repository.IncidentRepository;
import br.univesp.incidentmanagement.domain.schoolclass.model.SchoolClass;
import br.univesp.incidentmanagement.domain.schoolclass.repository.SchoolClassRepository;
import br.univesp.incidentmanagement.domain.student.model.Student;
import br.univesp.incidentmanagement.domain.student.repository.StudentRepository;
import br.univesp.incidentmanagement.infra.exception.incident.IncidentAlreadyDeletedException;
import br.univesp.incidentmanagement.infra.exception.incident.IncidentNotFoundException;
import br.univesp.incidentmanagement.infra.exception.schoolclass.SchoolClassNotFoundException;
import br.univesp.incidentmanagement.infra.exception.student.StudentNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class IncidentService {
    private final SchoolClassRepository schoolClassRepository;
    private final StudentRepository studentRepository;
    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;

    public IncidentService(
            SchoolClassRepository schoolClassRepository,
            StudentRepository studentRepository,
            IncidentRepository incidentRepository,
            IncidentMapper incidentMapper
    ) {
        this.schoolClassRepository = schoolClassRepository;
        this.studentRepository = studentRepository;
        this.incidentRepository = incidentRepository;
        this.incidentMapper = incidentMapper;
    }

    @Transactional
    public IncidentDetailsDTO create(IncidentCreateDTO data) {
        SchoolClass schoolClass = null;
        if(data.idSchoolClass() != null) {
            schoolClass = schoolClassRepository.findById(data.idSchoolClass())
                    .orElseThrow(() -> new SchoolClassNotFoundException("ID da Turma informado não existe!"));
        }
        Student student = null;
        if(data.idStudent() != null) {
            student = studentRepository.findById(data.idStudent())
                    .orElseThrow(() -> new StudentNotFoundException("ID do aluno informado não existe!"));
        }
        LocalDateTime registerDate = data.registerDate();
        Incident incident = incidentMapper.toEntity(data, schoolClass, student, registerDate);
        incidentRepository.save(incident);
        return incidentMapper.toDetailsDTO(incident);
    }

    public Page<IncidentDetailsDTO> list(Pageable pageable) {
        return incidentRepository.findAll(pageable).map(incidentMapper::toDetailsDTO);
    }

    public IncidentDetailsDTO showDetails(Long id) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException("Ocorrência não encontrada!"));
        return incidentMapper.toDetailsDTO(incident);
    }

    public Page<IncidentIniListDTO> listWaiting(Pageable pageable) {
        return incidentRepository.findAllWaiting(pageable).map(incidentMapper::toIniListDTO);
    }

    public Page<IncidentListDTO> listProgressing(Pageable pageable) {
        return incidentRepository.findAllProgressing(pageable).map(incidentMapper::toListDTO);
    }

    public Page<IncidentListDTO> listActive(Pageable pageable) {
        return incidentRepository.findAllActive(pageable).map(incidentMapper::toListDTO);
    }

    public Page<IncidentListDTO> listSolved(Pageable pageable) {
        return incidentRepository.findAllSolved(pageable).map(incidentMapper::toListDTO);
    }

    public Page<IncidentListDTO> listUnsolved(Pageable pageable) {
        return incidentRepository.findAllUnsolved(pageable).map(incidentMapper::toListDTO);
    }

    public Page<IncidentListDTO> listClosed(Pageable pageable) {
        return incidentRepository.findAllClosed(pageable).map(incidentMapper::toListDTO);
    }

    public List<String> listCategories() {
        return Arrays.stream(Category.values())
                .map(Enum::name)
                .toList();
    }

    public List<Map<String, String>> listAllTypes() {
        return Arrays.stream(Type.values())
                .map(t -> Map.of(
                        "name", t.name(),
                        "label", t.getLabel(),
                        "category", t.getCategory().name()
                ))
                .toList();
    }

    public List<String> listTypesByCategory(Category category) {
        return Type.getByCategory(category)
                .stream()
                .map(Enum::name)
                .toList();
    }

    public List<Map<String, String>> listStatus() {
        return Arrays.stream(Status.values())
                .map(s -> Map.of(
                        "name", s.name(),
                        "label", s.getLabel()
                ))
                .toList();
    }

    @Transactional
    public IncidentDetailsDTO update(IncidentUpdateDTO data) {
        Incident incident = incidentRepository.findById(data.id())
                .orElseThrow(() -> new IncidentNotFoundException("Ocorrência não encontrada!"));
        SchoolClass schoolClass = null;
        if(data.idSchoolClass() != null) {
            schoolClass = schoolClassRepository.findById(data.idSchoolClass())
                    .orElseThrow(() -> new SchoolClassNotFoundException("Turma não encontrada!"));
        }
        Student student = null;
        if(data.idStudent() != null) {
            student = studentRepository.findById(data.idStudent())
                    .orElseThrow(() -> new StudentNotFoundException("Aluno não encontrado!"));
        }
        incidentMapper.updateEntityFromDTO(data, incident, schoolClass, student);
        incidentRepository.save(incident);
        return incidentMapper.toDetailsDTO(incident);
    }

    @Transactional
    public IncidentDetailsDTO changeStatus(@Valid IncidentChangeStatusDTO data) {
        Incident incident = incidentRepository.findById(data.id())
                .orElseThrow(() -> new IncidentNotFoundException("Ocorrência não encontrada!"));
        if (incident.getStatus() == data.status()) {
            return incidentMapper.toDetailsDTO(incident);
        }
        incident.setStatus(data.status());
        incidentRepository.save(incident);
        return incidentMapper.toDetailsDTO(incident);
    }

    @Transactional
    public void delete(Long id) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException("Ocorrência não encontrada!"));
        if(incident.getDeleted()) {
            throw new IncidentAlreadyDeletedException("Ocorrência já excluída anteriormente!");
        }
        incident.setDeleted(true);
        incidentRepository.save(incident);
    }

    public IncidentSummaryDTO getSummary() {
        return new IncidentSummaryDTO(
                incidentRepository.countByStatusIn(List.of("AGUARDANDO_ATENDIMENTO", "EM_ATENDIMENTO")),
                incidentRepository.countByStatus("AGUARDANDO_ATENDIMENTO"),
                incidentRepository.countByStatus("EM_ATENDIMENTO"),
                incidentRepository.countByStatus("SOLUCIONADA"),
                incidentRepository.countByStatus("ENCERRADA_SEM_SOLUÇÃO"),
                incidentRepository.countByStatusIn(List.of("SOLUCIONADA", "ENCERRADA_SEM_SOLUÇÃO")),
                incidentRepository.countGroupedByCategory(),
                incidentRepository.countGroupedByType(),
                incidentRepository.countGroupedBySchoolClass(),
                incidentRepository.countGroupedByStudent()
        );
    }
}