package com.jonaygr.api.Models.Users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role")
    @NotNull(message = "El rol no puede ser nulo")
    @NotBlank(message = "El rol no puede estar vacio")
    @Size(min = 2, max = 25, message = "El rol no puede ser menos de dos caracteres ni mas de 25 caracteres")
    private String role;

    @ManyToMany(mappedBy = "roles")
    @JsonManagedReference
    private Collection<Trabajador> usuarios = new HashSet<>();

    public Roles(){}
    public Roles(String role) {
        this.role = role;
    }
}
