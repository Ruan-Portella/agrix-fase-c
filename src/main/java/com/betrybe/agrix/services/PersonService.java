package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.models.repositories.PersonRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * PersonService.
 */
@Service
public class PersonService implements UserDetailsService {
  private final PersonRepository personRepository;
  private final PasswordEncoder passwordEncoder;

  public PersonService(
      PersonRepository personRepository, PasswordEncoder passwordEncoder) {
    this.personRepository = personRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Método insert.
   */
  public Person insert(Person person) {
    String hashedPassword = new BCryptPasswordEncoder().encode(person.getPassword());
    person.setPassword(hashedPassword);
      
    return personRepository.save(person);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return personRepository.findByUsername(username);
  }

  /**
   * Returns a person for a given ID.
   */
  public Person getPersonById(Long id) {
    Optional<Person> person = personRepository.findById(id);

    if (person.isEmpty()) {
      throw new Error("Pessoa não encontrada!");
    }
    return person.get();
  }

  /**
   * Creates a new person.
   */
  public Person createPerson(Person person) {
    person.setPassword(passwordEncoder.encode(person.getPassword()));
    return personRepository.save(person);
  }
}