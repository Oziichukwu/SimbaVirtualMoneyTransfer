package com.example.simbavirtualmoneytransfer.data.repositories;

import com.example.simbavirtualmoneytransfer.data.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByName(String roleName);
}
