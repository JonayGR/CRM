package com.jonaygr.api.Controllers;

import com.jonaygr.api.Models.Users.Trabajador;
import com.jonaygr.api.Services.TrabajadorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trabajador")
@CrossOrigin(origins="*", allowCredentials = "false")
//@PreAuthorize("hasRole('ROLE_TRABAJADOR')")
public class TrabajadorController {
    private final TrabajadorService trabajadorService;


    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }
    @GetMapping
    public List<Trabajador> getTrabajadores(Authentication authentication) {
        String username = authentication.getName();

        List<Trabajador> usuarios = (trabajadorService.getAllTrabajadores()).stream()
                .filter(user -> !user.getCorreo().equals(username))
                .toList();

        return usuarios;
    }
    @GetMapping("/{id}")
    public Optional<Trabajador> getTrabajador(@PathVariable int id){
        return trabajadorService.getTrabajadorById(id);
    }


}
