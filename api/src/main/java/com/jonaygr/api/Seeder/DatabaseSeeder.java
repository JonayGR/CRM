package com.jonaygr.api.Seeder;

import com.jonaygr.api.Models.Users.Roles;
import com.jonaygr.api.Models.Users.Trabajador;
import com.jonaygr.api.Services.TrabajadorService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

@Component
public class DatabaseSeeder {
    private final TrabajadorService trabajadorService;
    @Autowired
    public DatabaseSeeder(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }


    @PostConstruct
    public void seedDatabase() {
        seedRoles();
        seedTrabajadores();
    }
    private void seedTrabajadores() {
        if(trabajadorService.getAllTrabajadores().isEmpty()) {
            Trabajador jefe = new Trabajador();
            jefe.setNombre("Jonay");
            jefe.setApellido1("Guillen");
            jefe.setApellido2("Rodríguez");
            jefe.setCorreo("jonayguillen@gmail.com");
            jefe.setPassword("1234");
            jefe.setEdad(27);
            jefe.setRoles(new HashSet<>(Collections.singletonList(trabajadorService.getOrCreateRole("ROLE_ADMIN"))));
            trabajadorService.saveTrabajador(jefe);
        }
    }
    private void seedRoles() {
        Roles empleado = trabajadorService.getOrCreateRole("ROLE_EMPLEADO");
        if (empleado == null) {
            empleado = new Roles( "ROLE_EMPLEADO");
            trabajadorService.saveRole(empleado);
        }
        Roles trabajador = trabajadorService.getOrCreateRole("ROLE_ADMIN");
        if (trabajador == null) {
            trabajador = new Roles( "ROLE_TRABAJADOR");
            trabajadorService.saveRole(trabajador);
        }
    }
}
