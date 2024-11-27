package dev.duncan.controller;

import com.fasterxml.jackson.databind.JsonNode;
import dev.duncan.client.RestClient;
import dev.duncan.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GatewayController {

    @Autowired
    RestClient restClient;

    @RequestMapping(value="/home")
    public String homePage() {
        return "/home.html";
    }

    @PostMapping(value = "/addTicket")
    public JsonNode saveTicket(@RequestBody JsonNode jsonNode) { return restClient.addTicket(jsonNode); }

    @GetMapping(value="/getAllTickets")
    public JsonNode getTickets() {
        JsonNode node = restClient.getAllTickets();
        return node;
    }

    @GetMapping(value="/getTicketById")
    public JsonNode getTicketByid(@RequestParam(value = "ticketId", required = true) int id) {
        JsonNode ticket = restClient.findTicketById(id);
        return ticket;
    }

    @GetMapping(value="/ticketForm.html")
    public String ticketForm() {
        return "ticketForm.html";
    }

    @DeleteMapping(value="/deleteTicketById")
    public void deleteTicket(@RequestParam(value = "ticketId", required = true) int id) {
        restClient.deleteTicketById(id);
    }

    @PutMapping(value="/approveTicketById")
    public void approveTicket(@RequestParam(value = "ticketId", required = true) int id, @RequestParam(value="approvedBy", required=true) String approvedBy) {
        restClient.approveTicketById(id, approvedBy);
    }

    @PutMapping(value="/updateTicketById")
    public void updateTicket(@RequestParam(value = "ticketId", required = true) int id, @RequestBody Ticket ticket) {
        restClient.updateTicketById(id, ticket);
    }

    @PostMapping(value="/sendMail")
    public JsonNode sendMail(@RequestBody JsonNode email) {
        return restClient.sendMail(email);
    }
}
