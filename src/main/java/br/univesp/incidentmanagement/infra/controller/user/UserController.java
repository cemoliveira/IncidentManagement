package br.univesp.incidentmanagement.infra.controller.user;

import br.univesp.incidentmanagement.application.user.dto.*;
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
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<UserDetailsDTO> createUser(
            @RequestBody @Valid UserCreateDTO data,
            UriComponentsBuilder uriBuilder
    ) {
        UserDetailsDTO dto = userService.create(data);
        if(dto.id() == null) {
            return ResponseEntity.internalServerError().body(dto);
        }
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<Page<UserListDTO>> listUsers(
            @ParameterObject @PageableDefault(size = 10, sort = {"name"}) Pageable pageable
    ) {
        return ResponseEntity.ok(userService.list(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<UserDetailsDTO> showUserDetails(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.showDetails(id));
    }

    @GetMapping("/roles")
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<List<String>> listRoles() {
        return ResponseEntity.ok(userService.listRoles());
    }

    @PatchMapping
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<UserDetailsDTO> updateRoleUser(
            @RequestBody @Valid UserUpdateRoleDTO data
    ) {
        return ResponseEntity.ok(userService.updateRole(data));
    }

    @PatchMapping("/password")
    public ResponseEntity<String> changePassword(
            @RequestBody @Valid UserChangePasswordDTO data
    ) {
        userService.changePassword(data);
        return ResponseEntity.ok("Senha alterada com sucesso! Você precisa fazer login novamente.");
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<UserDetailsDTO> updateUser(
            @RequestBody @Valid UserUpdateDTO data
    ) {
        return ResponseEntity.ok(userService.update(data));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATIVO')")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id
    ) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}