package com.jonaygr.api.Models.Registros;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jonaygr.api.Models.Users.Trabajador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ticket extends RegistroBase{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ticket_id", nullable = false)
    private Long ticketId;
    @Column(name = "completo", nullable = false)
    private boolean completo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    @JsonInclude
    @JoinTable(
            name = "trabajador_ticket",
            joinColumns = @JoinColumn(name = "trabajador_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    List<Trabajador> trabajadores;
}
