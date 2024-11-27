package dev.duncan.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.duncan.domain.Ticket;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TicketClient {

    private final String ticketMicroserviceUrl = "http://localhost:8383/getAllTickets";
//    public ResponseEntity<List<Ticket>> getAllTickets() {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<List<Ticket>> responseEntity = restTemplate.getForEntity(ticketMicroserviceUrl, Ticket.class);
//        return responseEntity;
//    }

    public ResponseEntity<List<Ticket>> getAllTickets() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Ticket>> responseEntity = restTemplate.exchange(
                ticketMicroserviceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ticket>>() {}
        );
        return responseEntity;
    }
}
