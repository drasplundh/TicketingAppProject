package dev.duncan.controller;

import dev.duncan.domain.Ticket;
import dev.duncan.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping(value="/addTicket")
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    @GetMapping(value="/getAllTickets")
    public List<Ticket> getAllTickets() {
        return ticketService.findAllTickets();
    }

    @GetMapping(value="/getTicketById")
    public Ticket findById(@RequestParam(value = "ticketId", required = true) int id) {
        return ticketService.findById(id);
    }

    @DeleteMapping(value="/deleteTicketById")
    public void deleteById(@RequestParam(value="ticketId", required = true) int id) {
        ticketService.deleteTicketById(id);
    }

    @PutMapping(value="/approveTicketById")
    public void approveTicketById(@RequestParam(value="ticketId", required = true) int id, @RequestParam(value="approvedBy", required = true) String approvedBy) {
        ticketService.approveTicketByid(id, approvedBy);
    }

    @PutMapping(value="/updateTicketById")
    public void updateTicketById(@RequestParam(value="ticketId", required = true) int id, @RequestBody Ticket ticket) {
        Ticket ticketExists;
        ticketExists = ticketService.findById(id);
        if (ticketExists != null) {
            ticket.setId(id);
            ticketService.updateTicket(ticket);
        }
    }
}
