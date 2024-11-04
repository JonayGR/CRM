package com.jonaygr.api.Services;

import com.jonaygr.api.Models.Registros.Ticket;
import com.jonaygr.api.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public List<Ticket> getAllTicket(){
        return ticketRepository.findAll();
    }
}
