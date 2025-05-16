package org.example.springtask1.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springtask1.exception.ResourceAlreadyUsedException;
import org.example.springtask1.exception.ResourceNotFoundException;
import org.example.springtask1.persistence.entity.Role;
import org.example.springtask1.persistence.entity.User;
import org.example.springtask1.persistence.repository.RoleRepository;
import org.example.springtask1.persistence.repository.UserRepository;
import org.example.springtask1.service.dto.UserDto;
import org.example.springtask1.service.dto.UserRegistrationDto;
import org.example.springtask1.service.dto.UserUpdateDto;
import org.example.springtask1.service.additional.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public UserDto createUser(UserRegistrationDto registrationDto) {

        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new ResourceAlreadyUsedException("User with this email already exists");
        }

        final Role role = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        final User user = new User();
        user.setFirstname(registrationDto.getFirstname());
        user.setLastname(registrationDto.getLastname());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setEnabled(true);
        user.setRole(role);

        return UserDto.toDto(userRepository.save(user));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::toDto)
                .toList();
    }

    public UserDto getById(Long id) {

        final User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserDto.toDto(user);
    }

    @Transactional
    public UserDto updateUser(Long id, UserUpdateDto updateDto) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setFirstname(updateDto.getFirstname());
        user.setLastname(updateDto.getLastname());

        return UserDto.toDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.deleteById(id);
    }

    @Transactional
    public UserDto changeUserRole(Long id, RoleName newRole) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        final Role role = roleRepository.findByName(newRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        user.setRole(role);

        return UserDto.toDto(userRepository.save(user));
    }
}
