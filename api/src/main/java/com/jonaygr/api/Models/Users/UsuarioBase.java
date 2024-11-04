package com.jonaygr.api.Models.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioBase {

    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    String Nombre;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    String Apellido1;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    String Apellido2;

    @Basic
    @NotBlank(message = "No se puede dejar vacio la contraseña")
    @NotNull(message = "No puede ser nulo la contraseña")
    @Column(name = "password", nullable = false, length = 512)
    @JsonIgnore
    String Correo;

    @Basic
    @Column(name = "edad", nullable = false)
    @Min(18)
    @Max(100)
    int edad;


}
