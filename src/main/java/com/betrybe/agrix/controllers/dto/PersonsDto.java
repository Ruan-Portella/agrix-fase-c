package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * PersonDTO.
 */
public record PersonsDto(String username, String password, Role role) {
  
  /**
  * toPerson.
  */
  public Person toPerson() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);
    return person;
  }

  /**
   * ToResponse.
   */
  public static record ToResponse(Long id, String username, Role role) {
    /**
    * FromEntity.
    */
    public static ToResponse fromEntity(Person person) {
      return new ToResponse(
        person.getId(),
        person.getUsername(),
        person.getRole()
      );
    }
  }
}
