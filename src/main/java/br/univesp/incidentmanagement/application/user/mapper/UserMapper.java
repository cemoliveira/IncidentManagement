package br.univesp.incidentmanagement.application.user.mapper;

import br.univesp.incidentmanagement.application.user.dto.*;
import br.univesp.incidentmanagement.domain.user.model.User;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final PasswordEncoder encoder;

    public UserMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User toEntity(UserCreateDTO data) {
        return new User(
                null,
                data.name(),
                data.login(),
                encoder.encode(data.password()),
                data.role(),
                true
        );
    }

    public UserDetailsDTO toDetailsDTO(User user) {
        return new UserDetailsDTO(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getRole(),
                user.getActive()
        );
    }

    public UserListDTO toListDTO(User user) {
        return new UserListDTO(
                user.getId(),
                user.getName(),
                user.getRole()
        );
    }

    public void updateRoleEntityFromDTO(UserUpdateRoleDTO data, User user) {
        user.setRole(data.role());
    }

    public void changePasswordEntityFromDTO(UserChangePasswordDTO data, User user) {
        user.setPassword(encoder.encode(data.newPassword()));
    }

    public void toEntityFromDTO(@Valid UserUpdateDTO data, User user) {
        if(data.name() != null && !data.name().isEmpty()) {
            user.setName(data.name());
        }
        if(data.login() != null && !data.login().isEmpty()) {
            user.setLogin(data.login());
        }
    }
}