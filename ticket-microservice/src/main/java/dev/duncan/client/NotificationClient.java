package dev.duncan.client;

import dev.duncan.domain.TicketCreatedInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class NotificationClient {
    private final String noiticationUrl = "http://localhost:8585/ticketCreated";
    private RestTemplate restTemplate;

    public NotificationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void notifyTicketCreated(int ticketId, int userId, LocalDate createdOn) {
        TicketCreatedInfo tci = new TicketCreatedInfo();

        tci.setCreatedOn(createdOn);
        tci.setTicketId(ticketId);
        tci.setUserEmail(userId);
        restTemplate.postForObject(noiticationUrl, tci, String.class);

    }
}
