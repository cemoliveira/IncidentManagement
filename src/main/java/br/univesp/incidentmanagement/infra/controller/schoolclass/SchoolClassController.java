package br.univesp.incidentmanagement.infra.controller.schoolclass;

import br.univesp.incidentmanagement.application.schoolclass.dto.SchoolClassCreateDTO;
import br.univesp.incidentmanagement.application.schoolclass.dto.SchoolClassDetailsDTO;
import br.univesp.incidentmanagement.application.schoolclass.dto.SchoolClassListDTO;
import br.univesp.incidentmanagement.application.schoolclass.dto.SchoolClassUpdateDTO;
import br.univesp.incidentmanagement.application.schoolclass.service.SchoolClassService;
import br.univesp.incidentmanagement.application.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/schoolclasses")
@SecurityRequirement(name = "bearer-key")
public class SchoolClassController {
    private final SchoolClassService schoolClassService;
    private final UserService userService;

    public SchoolClassController(
            SchoolClassService schoolClassService,
            UserService userService) {
        this.schoolClassService = schoolClassService;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<SchoolClassDetailsDTO> createSchoolClass(
            @RequestBody @Valid SchoolClassCreateDTO data,
            UriComponentsBuilder uriBuilder
    ) {
        SchoolClassDetailsDTO dto = schoolClassService.create(data);
        if(dto.id() == null) {
            return ResponseEntity.internalServerError().body(dto);
        }
        URI uri = uriBuilder.path("/schoolclasses/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<Page<SchoolClassListDTO>> listSchoolClasses(
            @ParameterObject @PageableDefault(size = 10, sort = {"name"}) Pageable pageable
    ) {
        return ResponseEntity.ok(schoolClassService.list(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<SchoolClassDetailsDTO> showSchoolClassDetails(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(schoolClassService.showDetails(id));
    }

    @GetMapping("/semesters")
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<List<String>> listSemesters() {
        return ResponseEntity.ok(schoolClassService.listSemesters());
    }

    @GetMapping("/shifts")
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<List<String>> listShifts() {
        return ResponseEntity.ok(schoolClassService.listShifts());
    }

    @GetMapping("/canceled")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR')")
    public ResponseEntity<Page<SchoolClassListDTO>> listCanceledSchoolClasses(
            @ParameterObject @PageableDefault(size = 10, sort = {"name"}) Pageable pageable
    ) {
        return ResponseEntity.ok(schoolClassService.listCanceled(pageable));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<SchoolClassDetailsDTO> updateSchoolClass(
            @RequestBody @Valid SchoolClassUpdateDTO data
    ) {
        return ResponseEntity.ok(schoolClassService.update(data));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<Void> cancelSchoolClass(
            @PathVariable Long id
    ) {
        schoolClassService.cancel(id);
        return ResponseEntity.noContent().build();
    }
}