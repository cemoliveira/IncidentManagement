package br.univesp.incidentmanagement.application.student.service;

import br.univesp.incidentmanagement.application.student.dto.StudentCreateDTO;
import br.univesp.incidentmanagement.application.student.dto.StudentDetailsDTO;
import br.univesp.incidentmanagement.application.student.dto.StudentListDTO;
import br.univesp.incidentmanagement.application.student.dto.StudentUpdateDTO;
import br.univesp.incidentmanagement.application.student.mapper.StudentMapper;
import br.univesp.incidentmanagement.domain.student.model.Student;
import br.univesp.incidentmanagement.domain.student.repository.StudentRepository;
import br.univesp.incidentmanagement.infra.exception.student.StudentAlreadyDeletedException;
import br.univesp.incidentmanagement.infra.exception.student.StudentNotFoundException;
import br.univesp.incidentmanagement.shared.service.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final ImageService imageService;

    public StudentService(
            StudentRepository studentRepository,
            StudentMapper studentMapper,
            ImageService imageService
    ) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.imageService = imageService;
    }

    @Transactional
    public StudentDetailsDTO create(StudentCreateDTO data) {
        Student student = studentMapper.toEntity(data);
        studentRepository.save(student);
        return studentMapper.toDetailsDTO(student);
    }

    @Transactional
    public StudentDetailsDTO uploadImg(Long id, MultipartFile image) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Aluno não encontrado!"));
        String relativePath = imageService.uploadImage(id, image, student.getImageUrl());
        student.setImageUrl(relativePath);
        studentRepository.save(student);
        return studentMapper.toDetailsDTO(student);
    }

    public Page<StudentListDTO> list(Pageable pageable) {
        return studentRepository.findAllIsActive(pageable).map(studentMapper::toListDTO);
    }

    public StudentDetailsDTO showDetails(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Aluno não encontrado!"));
        return studentMapper.toDetailsDTO(student);
    }

    @Transactional
    public StudentDetailsDTO update(StudentUpdateDTO data) {
        Student student = studentRepository.findById(data.id())
                .orElseThrow(() -> new StudentNotFoundException("Aluno não encontrado!"));
        studentMapper.updateEntityFromDTO(data, student);
        studentRepository.save(student);
        return studentMapper.toDetailsDTO(student);
    }

    @Transactional
    public void delete(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Aluno não encontrado!"));
        if(!student.getActive()) {
            throw new StudentAlreadyDeletedException("Aluno já excluído anteriormente!");
        }
        student.setActive(false);
        studentRepository.save(student);
    }
}