package dev.duncan.controller;

import com.fasterxml.jackson.databind.JsonNode;
import dev.duncan.client.TicketClient;
import dev.duncan.domain.EmailDetails;
import dev.duncan.domain.Ticket;
import dev.duncan.service.EmailService;
import dev.duncan.util.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmailController {

    @Autowired
    TicketClient ticketClient;

    @Autowired
    EmailService emailService;

    @PostMapping("/sendMail")
    public ResponseEntity<Map<String, String>> sendMail(@RequestBody EmailDetails details) {
        try {
            emailService.sendSimpleEmail(details);
            Map<String, String> JsonResponse = new HashMap<>();
            JsonResponse.put("message", "Mail Sent Successfully");
            return ResponseEntity.ok(JsonResponse);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

//    @PostMapping("/ticketCreated")
//    public ResponseEntity<String> receiveTicketCreated(@RequestBody TicketCreatedInfo tci) {
//        System.out.println("Ticket id: " + tci.getTicketId() + " userId: " + tci.getUserEmail());
//        return ResponseEntity.ok("Notification Received");
//    }

//    @GetMapping("/getAllTickets")
//    public JsonNode getAllTicket() {
//        JsonNode tickets = ticketClient.getAllTickets();
//        tickets.
//        return ticketClient.getAllTickets();
//    }

//    @PostMapping("/sendReminder")
//    public ResponseEntity<Map<String, String>> sendReminder(EmailDetails details) {
//        Map<String, String> JsonResponse = new HashMap<>();
//        JsonResponse response
//        return ResponseEntity.ok(JsonResponse);
    }