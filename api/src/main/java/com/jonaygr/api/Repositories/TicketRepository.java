package com.jonaygr.api.Repositories;

import com.jonaygr.api.Models.Registros.Ticket;
import com.jonaygr.api.Models.Users.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {


    Optional<List<Ticket>>findAllByTrabajadores_trabajadorId(Long id);

}
