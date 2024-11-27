package dev.duncan.client;

import dev.duncan.domain.Ticket;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserClient {

    private final String userMicroserviceUrl = "http://localhost:8282/getUserEmailById?userId=";

    public String getUserEmailById(int id) {
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(userMicroserviceUrl + id, String.class);
//        String email = responseEntity.getBody();
//        return email;

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(userMicroserviceUrl + id, String.class);
            System.out.println("Response Code: " + responseEntity.getStatusCode());
            System.out.println("Response Body: " + responseEntity.getBody());
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error Code: " + e.getStatusCode());
            System.out.println("Error Body: " + e.getResponseBodyAsString());
            throw e;
        }
    }
}
