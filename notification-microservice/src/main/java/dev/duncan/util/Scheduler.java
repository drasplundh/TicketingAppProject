package dev.duncan.util;

import com.fasterxml.jackson.databind.JsonNode;
import dev.duncan.client.TicketClient;
import dev.duncan.client.UserClient;
import dev.duncan.domain.EmailDetails;
import dev.duncan.domain.Ticket;
import dev.duncan.service.EmailService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    TicketClient ticketClient;

    @Autowired
    UserClient userClient;

    @Autowired
    EmailService emailService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //todo try catch for no tickets received
    @Scheduled(fixedRate = 10000)
    public ResponseEntity<List<Ticket>> checkIfPastTime() {
        ResponseEntity<List<Ticket>> responseEntity = ticketClient.getAllTickets();
        List<Ticket> tickets = responseEntity.getBody();
        for (Ticket ticket : tickets) {
            if (!ticket.isApproved()) {
                int userId = ticket.getCreatedBy();
                String userEmail = userClient.getUserEmailById(userId);
                System.out.println("User email: " + userEmail);
                EmailDetails emailDetails = new EmailDetails(userEmail,
                        "Hello " + userEmail + ", your ticket No. " + ticket.getId()
                                + " titled: " + ticket.getTitle()
                                + ", has yet to be approved. Please contact a manager for approval ASAP.",
                        "Ticket No. " + ticket.getId() + "Awaiting Approval");
                emailService.sendSimpleEmail(emailDetails);
//                System.out.println("ticket created: " + ticket.getCreatedOn());
            }

        }
        return responseEntity;
    }
}
