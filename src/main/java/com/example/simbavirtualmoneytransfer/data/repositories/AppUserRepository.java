package com.example.simbavirtualmoneytransfer.data.repositories;

import com.example.simbavirtualmoneytransfer.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findUserByEmail(String email);

    boolean existsByEmail(String email);
}
