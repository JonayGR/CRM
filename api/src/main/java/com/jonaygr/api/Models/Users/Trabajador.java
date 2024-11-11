package com.jonaygr.api.Models.Users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jonaygr.api.Models.Registros.Ticket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@Entity
public class Trabajador extends UsuarioBase{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "trabajador_id", nullable = false)
    private Long trabajadorId;
    @Basic
    @NotBlank(message = "No se puede dejar vacio la contraseña")
    @NotNull(message = "No puede ser nulo la contraseña")
    @Column(name = "password", nullable = false, length = 512)
    @JsonIgnore
    private String password;
    @ManyToMany(mappedBy = "trabajadores", cascade = CascadeType.ALL)
    List<Ticket> tickets;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    @JsonInclude
    @JoinTable(
            name = "trabajador_roles",
            joinColumns = @JoinColumn(name = "trabajador_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Roles> roles = new HashSet<>();

}
