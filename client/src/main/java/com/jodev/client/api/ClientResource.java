package com.jodev.client.api;

import com.jodev.client.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
@EnableCircuitBreaker
public class ClientResource {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private AuthenticationService authenticationService;

  @GetMapping("/test-user-ok")
  private ResponseEntity<String> testUserOk() {

    String token = authenticationService.getToken("user", "user");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_PLAIN);
    headers.setBearerAuth(token);

    HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

    String url = "http://resource-server/resource/read";

    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
    return ResponseEntity.ok(Objects.requireNonNull(response.getBody()));
  }

  @GetMapping("/test-user-error")
  private ResponseEntity<String> testUserError() {

    String token = authenticationService.getToken("user", "user");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_PLAIN);
    headers.setBearerAuth(token);

    HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

    String url = "http://resource-server/resource/write";

    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    return ResponseEntity.ok(Objects.requireNonNull(response.getBody()));
  }

  @GetMapping("/test-admin")
  private ResponseEntity<String> testAdmin() {

    String token = authenticationService.getToken("admin", "admin");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_PLAIN);
    headers.setBearerAuth(token);

    HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

    String url = "http://resource-server/resource/write";

    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    return ResponseEntity.ok(Objects.requireNonNull(response.getBody()));
  }
}
