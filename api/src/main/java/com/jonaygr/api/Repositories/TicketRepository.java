package com.jonaygr.api.Repositories;

import com.jonaygr.api.Models.Registros.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
