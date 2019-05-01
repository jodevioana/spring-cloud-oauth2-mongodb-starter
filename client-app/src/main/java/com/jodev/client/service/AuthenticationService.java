package com.jodev.client.service;

import com.jodev.client.model.OauthTokenResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class AuthenticationService {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${security.oauth2.client.client-id}")
  private String clientId;

  @Value("${security.oauth2.client.client-secret}")
  private String clientSecret;

  @HystrixCommand(fallbackMethod = "defaultToken")
  public String getToken(String username, String password) {
    String credentials = clientId + ":" + clientSecret;
    String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.add("Authorization", "Basic " + encodedCredentials);

    HttpEntity<String> request = new HttpEntity<>(headers);

    String url = "http://authorization-server/oauth/token?grant_type=password&username=" + username + "&password=" + password;
    ResponseEntity<OauthTokenResponse> response = restTemplate.exchange(url, HttpMethod.POST, request, OauthTokenResponse.class);

    return response.getBody().getAccess_token();
  }

  //just to show how a circuit breaker would work
  private String defaultToken(String username, String password) {
    return "";
  }
}
