package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.AuthenticationDto;
import com.betrybe.agrix.controllers.dto.AuthenticationResponseDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class AuthController.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;
  
  /**
   * AuthenticationController.
   */
  public AuthenticationController(AuthenticationManager authenticationManager, 
      TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Método login.
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationDto authenticationDto) {
    UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(
        authenticationDto.username(), authenticationDto.password());
    Authentication auth = authenticationManager.authenticate(user);

    Person person = (Person) auth.getPrincipal();

    String token = tokenService.generateToken(person);

    return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponseDto(token));
  }
}