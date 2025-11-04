package br.univesp.incidentmanagement.application.user.service;

import br.univesp.incidentmanagement.application.user.dto.*;
import br.univesp.incidentmanagement.application.user.mapper.UserMapper;
import br.univesp.incidentmanagement.domain.user.enums.Role;
import br.univesp.incidentmanagement.domain.user.model.User;
import br.univesp.incidentmanagement.domain.user.repository.UserRepository;
import br.univesp.incidentmanagement.infra.exception.user.InvalidPasswordException;
import br.univesp.incidentmanagement.infra.exception.user.UserAlreadyDeletedException;
import br.univesp.incidentmanagement.infra.exception.user.UserNotFoundException;
import br.univesp.incidentmanagement.infra.security.service.TokenBlackListService;
import br.univesp.incidentmanagement.infra.security.service.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;
    private final TokenBlackListService tokenBlackListService;

    public UserService(
            UserMapper userMapper,
            UserRepository userRepository,
            PasswordEncoder encoder,
            TokenService tokenService, TokenBlackListService tokenBlackListService
    ) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.tokenService = tokenService;
        this.tokenBlackListService = tokenBlackListService;
    }

    @Transactional
    public UserDetailsDTO create(UserCreateDTO data) {
        User user = userMapper.toEntity(data);
        userRepository.save(user);
        return userMapper.toDetailsDTO(user);
    }

    public Page<UserListDTO> list(Pageable pageable) {
        return userRepository.findAllActive(pageable).map(userMapper::toListDTO);
    }

    public UserDetailsDTO showDetails(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        return userMapper.toDetailsDTO(user);
    }

    public List<String> listRoles() {
        return Arrays.stream(Role.values())
                .map(Enum::name)
                .toList();
    }

    @Transactional
    public UserDetailsDTO updateRole(UserUpdateRoleDTO data) {
        User user = userRepository.findById(data.id())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
        userMapper.updateRoleEntityFromDTO(data, user);
        userRepository.save(user);
        return userMapper.toDetailsDTO(user);
    }

    @Transactional
    public void changePassword(UserChangePasswordDTO data) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!encoder.matches(data.oldPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Senha incorreta!");
        }
        userMapper.changePasswordEntityFromDTO(data, user);
        userRepository.save(user);
        String token = extractTokenFromSecurityContext();
        if(token != null) {
            Instant expiration = tokenService.getExpirationDate(token);
            tokenBlackListService.add(token, expiration);
        }
        SecurityContextHolder.clearContext();
    }

    @Transactional
    public UserDetailsDTO update(@Valid UserUpdateDTO data) {
        User user = userRepository.findById(data.id())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
        userMapper.toEntityFromDTO(data, user);
        userRepository.save(user);
        return userMapper.toDetailsDTO(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
        if(!user.getActive()) {
            throw new UserAlreadyDeletedException("Usuário já excluído anteriormente!");
        }
        user.setActive(false);
        userRepository.save(user);
    }

    private String extractTokenFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return null;
        }
        Object details = authentication.getDetails();
        if(details instanceof String token) {
            return token.startsWith("Bearer ") ? token.substring(7) : token;
        }
        return null;
    }
}