package com.letscode.harrypotter.student.service.clients;

import com.letscode.harrypotter.student.dto.HouseResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class HouseClient {

  public HouseResponse execute(String sortingHatChoice) {
    try {
      RestTemplate restTemplate = new RestTemplate();

      String url = "https://api-harrypotter.herokuapp.com/house/" + sortingHatChoice;
      HttpHeaders headers = new HttpHeaders();
      headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
      HttpEntity<?> entity = new HttpEntity<>(headers);

      ResponseEntity<HouseResponse> response = restTemplate.exchange(
          url,
          HttpMethod.GET,
          entity,
          HouseResponse.class);
      return response.getBody();
    } catch (RestClientException clientException) {
      clientException.printStackTrace();
    }
    return null;
  }

}
