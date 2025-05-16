package org.example.springtask1.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.springtask1.persistence.entity.User;
import org.example.springtask1.service.additional.RoleName;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private RoleName roleName;
    private Long createdAt;
    private Long updatedAt;

    public static UserDto toDto(User user) {
        final UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setRoleName(user.getRole().getName());
        userDto.setCreatedAt(user.getCreatedAt().toEpochMilli());
        userDto.setUpdatedAt(user.getUpdatedAt().toEpochMilli());

        return userDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
