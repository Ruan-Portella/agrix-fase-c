package com.betrybe.agrix.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.models.entities.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Class Jwt.
 */
@Service
public class TokenService {

  @Value("${agrix.security.token.secret}")
  private String secret;

  /**
   * Método generateToken.
   */
  public String generateToken(Person person) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
      .withIssuer("agrix")
      .withSubject(person.getUsername())
      .withExpiresAt(generateExpirationDate())
      .sign(algorithm);
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }

  /**
   * Método validateToken.
   */
  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
              .withIssuer("agrix")
              .build()
              .verify(token)
              .getSubject();
  }
}