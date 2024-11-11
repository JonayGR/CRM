package com.jonaygr.api.Services;

import com.jonaygr.api.DTOs.TokenAuth;
import com.jonaygr.api.JWT.JwtService;
import com.jonaygr.api.Models.Users.Roles;
import com.jonaygr.api.Models.Users.Trabajador;
import com.jonaygr.api.Repositories.RolesRepository;
import com.jonaygr.api.Repositories.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
public class TrabajadorService  implements UserDetailsService {

    @Lazy
    @Autowired
    private  TrabajadorRepository trabajadorRepository;

    @Lazy
    @Autowired
    private RolesRepository rolesRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    public String createTrabajador(Trabajador newTrabajador){

        newTrabajador.setPassword(passwordEncoder.encode(newTrabajador.getPassword()));

        if(newTrabajador.getRoles()== null|| newTrabajador.getRoles().isEmpty()){
            Set<Roles> defaulRoles= new HashSet<>();
            Roles trabajadorRole=getOrCreateRole("ROLE_TRABAJADOR");
            defaulRoles.add(trabajadorRole);
            newTrabajador.setRoles(defaulRoles);
        }
        else {
            Set<Roles> existenRoles= new HashSet<>();
            newTrabajador.getRoles().forEach(n->{
                existenRoles.add(getOrCreateRole(n.getRole()));
            });
        }
        trabajadorRepository.save(newTrabajador);
        return TokenAuth.builder()
                .token(jwtService.getToken(User.builder()
                        .username(newTrabajador.getCorreo())
                        .password(newTrabajador.getPassword())
                        .authorities(getAuthorities(newTrabajador.getRoles()))
                        .build()))
                .build().getToken();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Roles>roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }
    @Override
    public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException {

        Optional<Trabajador> userOptional = trabajadorRepository.findByCorreo(userMail);
        Trabajador usuarios = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + userMail));

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuarios.getCorreo())
                .password(usuarios.getPassword())
                .authorities(getAuthorities(usuarios.getRoles()))
                .build();

    }


    public TokenAuth authenticateUser(String userMail, String password) {
        Trabajador usuarios = trabajadorRepository.findByCorreo(userMail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + userMail));

        if (!passwordEncoder.matches(password, usuarios.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        return TokenAuth.builder()
                .token(jwtService.getToken(User.builder()
                        .username(usuarios.getCorreo())
                        .password(usuarios.getPassword())
                        .authorities(getAuthorities(usuarios.getRoles()))
                        .build()))
                .build();
    }

    public Trabajador loadUserDefault(String userMail) throws UsernameNotFoundException {
        Optional<Trabajador> userOptional = trabajadorRepository.findByCorreo(userMail);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + userMail));
    }

    public List<Trabajador> getAllTrabajadores(){
        return trabajadorRepository.findAll();
    }
    public void saveRole(Roles role) {
        rolesRepository.save(role);
    }
    public void deleteTrabajadorById(long id){trabajadorRepository.deleteById(id);}
    public Optional<Trabajador> getTrabajadorById(long id){return trabajadorRepository.findById(id);}


    //Roles
    public Roles getOrCreateRole(String roleName) {
        Optional<Roles> existingRole = rolesRepository.findByRole(roleName);
        return existingRole.orElseGet(() -> (Roles) rolesRepository.save(new Roles(roleName)));
    }
    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }
    public void saveTrabajador(Trabajador trabajador) {
        trabajadorRepository.save(trabajador);
    }


}
