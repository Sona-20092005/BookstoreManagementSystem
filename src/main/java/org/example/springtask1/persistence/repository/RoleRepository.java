package org.example.springtask1.persistence.repository;

import org.example.springtask1.persistence.entity.Role;
import org.example.springtask1.service.additional.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}
