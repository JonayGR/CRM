package com.jonaygr.api.Models.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@MappedSuperclass
public class UsuarioBase {

    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    String nombre;
    @Basic
    @Column(name = "apellido1", nullable = false, length = 100)
    String apellido1;
    @Basic
    @Column(name = "apellido2", nullable = false, length = 100)
    String apellido2;

    @Basic
    @NotBlank(message = "No se puede dejar vacio la contraseña")
    @NotNull(message = "No puede ser nulo la contraseña")
    @Column(name = "correo", nullable = false, length = 512, unique = true)
    @JsonIgnore
    String correo;

    @Basic
    @Column(name = "edad", nullable = false)
    @Min(18)
    @Max(100)
    int edad;

    @Basic
    @Column(name = "telefono", nullable = false)
    int telefono;



}
