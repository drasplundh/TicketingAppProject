package dev.duncan.service;

import dev.duncan.domain.Ticket;

import java.util.List;

public interface TicketService {

    public Ticket addTicket(Ticket ticket);
    public void deleteTicketById(int id);
    public void updateTicket(Ticket ticket);

    public void approveTicketByid(int id, String approvedBy);
    public List<Ticket> findAllTickets();

    public Ticket findById(int id);


}
