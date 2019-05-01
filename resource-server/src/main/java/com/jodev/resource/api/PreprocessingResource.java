package com.jodev.resource.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class PreprocessingResource {

  @Autowired
  private Environment springEnv;

  @PreAuthorize("hasAuthority('role_user')")
  @GetMapping(value = "/read")
  public ResponseEntity<String> read() {
      return ResponseEntity.ok(springEnv.getProperty("server.port") + " ------- " + "read resource");
  }

  @PreAuthorize("hasAuthority('role_admin')")
  @PostMapping(value = "/write")
  public ResponseEntity<String> write() {
    return ResponseEntity.ok(springEnv.getProperty("server.port") + " ------- " + "write resource");
  }
}
