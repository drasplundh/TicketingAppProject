package dev.duncan.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.duncan.domain.Ticket;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Component
public class RestClient {

    private static final String ticketMicroserviceUrl = "http://localhost:8383/";
    private static final String notificationMicroserviceUrl = "http://localhost:8585/";

    public JsonNode getAllTickets() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(ticketMicroserviceUrl+"getAllTickets", Object.class);
        Object tickets = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode returnTickets = mapper.convertValue(tickets, JsonNode.class);
        return returnTickets;
    }

    public JsonNode findTicketById(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(ticketMicroserviceUrl+"getTicketById?ticketId=" + id, Object.class);
        Object ticket = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode t = mapper.convertValue(ticket, JsonNode.class);
        return t;

    }

    public void deleteTicketById(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> responseEntity = restTemplate.exchange(ticketMicroserviceUrl + "deleteTicketById?ticketId=" + id,
                HttpMethod.DELETE,
                entity,
                Void.class
        );
    }

    public void approveTicketById(int id, String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> responseEntity = restTemplate.exchange(ticketMicroserviceUrl + "approveTicketById?ticketId=" + id + "&approvedBy=" + username,
                HttpMethod.PUT,
                entity,
                Void.class
        );
    }

    public void updateTicketById(int id, Ticket ticket) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Ticket> entity = new HttpEntity<>(ticket, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> responseEntity = restTemplate.exchange(ticketMicroserviceUrl + "updateTicketById?ticketId=" + id,
                HttpMethod.PUT,
                entity,
                Void.class
        );
    }

    public JsonNode addTicket(JsonNode node) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(node.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(ticketMicroserviceUrl+"addTicket", request, Object.class);
//        ResponseEntity<Object> infoToNotificaiton = restTemplate.
        Object object = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = mapper.convertValue(object, JsonNode.class);

        return obj;
    }

    public JsonNode sendMail(JsonNode node) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(node.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(notificationMicroserviceUrl+"sendMail", request, JsonNode.class);

        return responseEntity.getBody();
    }
}
