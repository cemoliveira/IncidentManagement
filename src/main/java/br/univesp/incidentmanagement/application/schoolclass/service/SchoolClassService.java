package br.univesp.incidentmanagement.application.schoolclass.service;

import br.univesp.incidentmanagement.application.schoolclass.dto.SchoolClassCreateDTO;
import br.univesp.incidentmanagement.application.schoolclass.dto.SchoolClassDetailsDTO;
import br.univesp.incidentmanagement.application.schoolclass.dto.SchoolClassListDTO;
import br.univesp.incidentmanagement.application.schoolclass.dto.SchoolClassUpdateDTO;
import br.univesp.incidentmanagement.application.schoolclass.mapper.SchoolClassMapper;
import br.univesp.incidentmanagement.domain.schoolclass.enums.Semester;
import br.univesp.incidentmanagement.domain.schoolclass.enums.Shift;
import br.univesp.incidentmanagement.domain.schoolclass.model.SchoolClass;
import br.univesp.incidentmanagement.domain.schoolclass.repository.SchoolClassRepository;
import br.univesp.incidentmanagement.infra.exception.schoolclass.SchoolClassAlreadyCanceledException;
import br.univesp.incidentmanagement.infra.exception.schoolclass.SchoolClassNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SchoolClassService {
    private final SchoolClassMapper schoolClassMapper;
    private final SchoolClassRepository schoolClassRepository;

    public SchoolClassService(
            SchoolClassMapper schoolClassMapper,
            SchoolClassRepository schoolClassRepository
    ) {
        this.schoolClassRepository = schoolClassRepository;
        this.schoolClassMapper = schoolClassMapper;
    }

    @Transactional
    public SchoolClassDetailsDTO create(SchoolClassCreateDTO data) {
        SchoolClass schoolClass = schoolClassMapper.toEntity(data);
        schoolClassRepository.save(schoolClass);
        return schoolClassMapper.toDeteailsDTO(schoolClass);
    }

    public Page<SchoolClassListDTO> list(Pageable pageable) {
        return schoolClassRepository.findAllNotCanceled(pageable).map(schoolClassMapper::toListDTO);
    }

    public SchoolClassDetailsDTO showDetails(Long id) {
        SchoolClass schoolClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new SchoolClassNotFoundException("Turma não encontrada!"));
        return schoolClassMapper.toDeteailsDTO(schoolClass);
    }

    public List<String> listSemesters() {
        return Arrays.stream(Semester.values())
                .map(Enum::name)
                .toList();
    }

    public List<String> listShifts() {
        return Arrays.stream(Shift.values())
                .map(Enum::name)
                .toList();
    }

    public Page<SchoolClassListDTO> listCanceled(Pageable pageable) {
        return schoolClassRepository.findAllCanceled(pageable).map(schoolClassMapper::toListDTO);
    }

    @Transactional
    public SchoolClassDetailsDTO update(@Valid SchoolClassUpdateDTO data) {
        SchoolClass schoolClass = schoolClassRepository.findById(data.id())
                .orElseThrow(() -> new SchoolClassNotFoundException("Turma não encontrada!"));
        schoolClassMapper.updateEntityFromDTO(data, schoolClass);
        schoolClassRepository.save(schoolClass);
        return schoolClassMapper.toDeteailsDTO(schoolClass);
    }

    @Transactional
    public void cancel(Long id) {
        SchoolClass schoolClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new SchoolClassNotFoundException("Turma não encontrada!"));
        if(schoolClass.getCanceled()) {
            throw new SchoolClassAlreadyCanceledException("Turma já cancelada anteriormente!");
        }
        schoolClass.setCanceled(true);
        schoolClassRepository.save(schoolClass);
    }
}