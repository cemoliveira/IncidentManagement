package br.univesp.incidentmanagement.infra.controller.student;

import br.univesp.incidentmanagement.application.student.dto.StudentCreateDTO;
import br.univesp.incidentmanagement.application.student.dto.StudentDetailsDTO;
import br.univesp.incidentmanagement.application.student.dto.StudentListDTO;
import br.univesp.incidentmanagement.application.student.dto.StudentUpdateDTO;
import br.univesp.incidentmanagement.application.student.service.StudentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/students")
@SecurityRequirement(name = "bearer-key")
public class StudentController {
    private final StudentService studentService;

    public StudentController(
            StudentService studentService
    ) {
        this.studentService = studentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<StudentDetailsDTO> createStudent(
            @RequestBody @Valid StudentCreateDTO data,
            UriComponentsBuilder uriBuilder
    ) {
        StudentDetailsDTO dto = studentService.create(data);
        if(dto.id() == null) {
            return ResponseEntity.internalServerError().body(dto);
        }
        URI uri = uriBuilder.path("/students/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PatchMapping("/{id}/image")
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<StudentDetailsDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image
    ) {
        return ResponseEntity.ok(studentService.uploadImg(id, image));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<Page<StudentListDTO>> listStudents(
            @ParameterObject @PageableDefault(size = 10, sort = {"name"}) Pageable pageable
    ) {
        return ResponseEntity.ok(studentService.list(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA')")
    public ResponseEntity<StudentDetailsDTO> showStudentDetails(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(studentService.showDetails(id));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<StudentDetailsDTO> updateStudent(
            @RequestBody @Valid StudentUpdateDTO data
    ) {
        return ResponseEntity.ok(studentService.update(data));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id
    ) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}