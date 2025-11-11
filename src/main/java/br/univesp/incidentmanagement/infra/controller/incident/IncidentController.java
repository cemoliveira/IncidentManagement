package br.univesp.incidentmanagement.infra.controller.incident;

import br.univesp.incidentmanagement.application.incident.dto.*;
import br.univesp.incidentmanagement.application.incident.service.IncidentService;
import br.univesp.incidentmanagement.domain.incident.enums.Category;
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
import java.util.Map;

@RestController
@RequestMapping("/incidents")
@SecurityRequirement(name = "bearer-key")
public class IncidentController {
    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<IncidentDetailsDTO> createIncident(
            @RequestBody @Valid IncidentCreateDTO data,
            UriComponentsBuilder uriBuilder
    ) {
        IncidentDetailsDTO dto = incidentService.create(data);
        if(dto.id() == null) {
            return ResponseEntity.internalServerError().body(dto);
        }
        URI uri = uriBuilder.path("/incidents/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<Page<IncidentDetailsDTO>> listIncidents(
            @ParameterObject @PageableDefault(size = 10, sort = {"registerDate"}) Pageable pageable
    ) {
        return ResponseEntity.ok(incidentService.list(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<IncidentDetailsDTO> showIncidentDetails(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(incidentService.showDetails(id));
    }

    @GetMapping("/status/waiting")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<Page<IncidentIniListDTO>> listWaitingIncidents(
            @ParameterObject @PageableDefault(size = 10, sort = {"registerDate"}) Pageable pageable
    ) {
        return ResponseEntity.ok(incidentService.listWaiting(pageable));
    }

    @GetMapping("/status/progressing")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<Page<IncidentListDTO>> listProgressingIncidents(
            @ParameterObject @PageableDefault(size = 10, sort = {"updateDate"}) Pageable pageable
    ) {
        return ResponseEntity.ok(incidentService.listProgressing(pageable));
    }

    @GetMapping("/status/active")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<Page<IncidentListDTO>> listActiveIncidents(
            @ParameterObject @PageableDefault(size = 10, sort = {"updateDate"}) Pageable pageable
    ) {
        return ResponseEntity.ok(incidentService.listActive(pageable));
    }

    @GetMapping("/status/solved")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<Page<IncidentListDTO>> listSolvedIncidents(
            @ParameterObject @PageableDefault(size = 10, sort = {"updateDate"}) Pageable pageable
    ) {
        return ResponseEntity.ok(incidentService.listSolved(pageable));
    }

    @GetMapping("/status/unsolved")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<Page<IncidentListDTO>> listUnsolvedIncidents(
            @ParameterObject @PageableDefault(size = 10, sort = {"updateDate"}) Pageable pageable
    ) {
        return ResponseEntity.ok(incidentService.listUnsolved(pageable));
    }

    @GetMapping("/status/closed")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<Page<IncidentListDTO>> listClosedIncidents(
            @ParameterObject @PageableDefault(size = 10, sort = {"updateDate"}) Pageable pageable
    ) {
        return ResponseEntity.ok(incidentService.listClosed(pageable));
    }

    @GetMapping("/categories")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<List<String>> listCategories() {
        return ResponseEntity.ok(incidentService.listCategories());
    }

    @GetMapping("/types")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<List<Map<String, String>>> listAllTypes() {
        return ResponseEntity.ok(incidentService.listAllTypes());
    }

    @GetMapping("/types/{category}")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<List<String>> listTypesByCategory(
            @PathVariable Category category
    ) {
        return ResponseEntity.ok(incidentService.listTypesByCategory(category));
    }

    @GetMapping("/status")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<List<Map<String, String>>> listStatus() {
        return ResponseEntity.ok(incidentService.listStatus());
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<IncidentDetailsDTO> updateIncident(
            @RequestBody @Valid IncidentUpdateDTO data
    ) {
        return ResponseEntity.ok(incidentService.update(data));
    }

    @PatchMapping("/status")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<IncidentDetailsDTO> changeIncidentStatus(
            @RequestBody @Valid IncidentChangeStatusDTO data
    ) {
        return ResponseEntity.ok(incidentService.changeStatus(data));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATIVO', 'COORDENADOR', 'ANALISTA', 'PROFESSOR')")
    public ResponseEntity<Void> deleteIncident(
            @PathVariable Long id
    ) {
        incidentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}