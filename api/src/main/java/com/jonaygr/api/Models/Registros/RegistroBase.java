package com.jonaygr.api.Models.Registros;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;

import java.util.Date;

public class RegistroBase {
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    String nombre;
    @Basic
    @Column(name = "nombre", nullable = false, length = 700)
    String descripcion;
    Date fechaInicio;
    Date fechafin;

}
