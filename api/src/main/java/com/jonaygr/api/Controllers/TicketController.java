package com.jonaygr.api.Controllers;

import com.jonaygr.api.Models.Registros.Ticket;
import com.jonaygr.api.Services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins="*", allowCredentials = "false")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getAllTicket();
    }

    @GetMapping("/{id}")
    public List<Ticket> getTicketsByTrabajador(@PathVariable Long id) {
        return ticketService.getTicketByTrabajador(id);
    }
}
