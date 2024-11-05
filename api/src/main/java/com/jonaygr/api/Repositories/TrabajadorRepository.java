package com.jonaygr.api.Repositories;

import com.jonaygr.api.Models.Users.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrabajadorRepository extends JpaRepository {
    Optional<Trabajador> findByEmail(String email);
}