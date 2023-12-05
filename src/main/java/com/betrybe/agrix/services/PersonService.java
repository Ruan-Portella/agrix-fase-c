package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.models.repositories.PersonRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * PersonService.
 */
@Service
public class PersonService {
  private final PersonRepository personRepository;

  public PersonService(
      PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /**
   * getPersonById.
   */
  public Person getPersonById(Long id) {
    Optional<Person> personOptional = personRepository.findById(id);
    if (personOptional.isEmpty()) {
      throw new Error("Pessoa não encontrada!");
    }
    return personOptional.get();
  }

  /**
   * getPersonByUsername.
   */
  public Person getPersonByUsername(String username) {
    Optional<Person> personOptional = personRepository.findByUsername(username);
    if (personOptional.isEmpty()) {
      throw new Error("Pessoa não encontrada!");
    }
    return personOptional.get();
  }
  
  /**
   * createPerson.
   */
  public Person createPerson(Person person) {
    return personRepository.save(person);
  }
}