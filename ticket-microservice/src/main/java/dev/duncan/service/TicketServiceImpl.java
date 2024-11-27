package dev.duncan.service;

import dev.duncan.client.NotificationClient;
import dev.duncan.domain.Ticket;
import dev.duncan.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepo;


    @Override
    public Ticket addTicket(Ticket ticket) {
        LocalDate createdNow = LocalDate.now(); // current date-time
        ticket.setCreatedOn(createdNow);
//        RestTemplate restTemplate = new RestTemplate();
//        NotificationClient notificationClient = new NotificationClient(new RestTemplate());
//        notificationClient.notifyTicketCreated(ticket.getId(), ticket.getCreatedBy(), ticket.getCreatedOn()); // notify notification microservice of ticket creation

        return ticketRepo.save(ticket);
    }

    @Override
    public void deleteTicketById(int id) {
        ticketRepo.deleteById(id);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        Optional<Ticket> existsTicket = ticketRepo.findById(ticket.getId());
        // if ticket doesn't exist - throw error
        if (existsTicket.isEmpty()) {
            // TODO throw useful exception
            System.out.println("Ticket with that id doesn't exist");
        } else { // otherwise, assign new values to pass through
            Ticket updatedTicket = existsTicket.get();
            updatedTicket.setAmount(ticket.getAmount());
            updatedTicket.setDate(ticket.getDate());
            updatedTicket.setDepartment(ticket.getDepartment());
            updatedTicket.setDescription(ticket.getDescription());
            updatedTicket.setPriority(ticket.getPriority());
            updatedTicket.setTitle(ticket.getTitle());
            System.out.println("complete updated ticket: " + updatedTicket.toString());
            ticketRepo.save(updatedTicket);
        }


    }

    @Override
    public void approveTicketByid(int id, String approvedBy) {
        ticketRepo.approveTicketById(id, approvedBy);
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepo.findAll();
    }

    @Override
    public Ticket findById(int id) {
        Optional<Ticket> ticket = ticketRepo.findById(id);
        return ticket.get();
    }
}
