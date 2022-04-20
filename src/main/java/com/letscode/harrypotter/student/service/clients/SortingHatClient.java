package com.letscode.harrypotter.student.service.clients;

import com.letscode.harrypotter.student.dto.SortingHatResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class SortingHatClient {

  public SortingHatResponse execute() {
    try {
      RestTemplate restTemplate = new RestTemplate();

      String url = "https://api-harrypotter.herokuapp.com/sortinghat";
      HttpHeaders headers = new HttpHeaders();
      headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
      HttpEntity<?> entity = new HttpEntity<>(headers);

      ResponseEntity<SortingHatResponse> sortingHatResponseEntity = restTemplate.exchange(
          url,
          HttpMethod.GET,
          entity,
          SortingHatResponse.class);
      return sortingHatResponseEntity.getBody();
    } catch (RestClientException clientException) {
      clientException.printStackTrace();
    }
    return null;
  }
}
