package com.jonaygr.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository {

    Optional findByRole(String roleName);
}
