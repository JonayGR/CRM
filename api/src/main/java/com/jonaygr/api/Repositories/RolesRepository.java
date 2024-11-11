package com.jonaygr.api.Repositories;


import com.jonaygr.api.Models.Users.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles,Long> {

    Optional findByRole(String roleName);
}
