package com.example.application.data.endpoint;

import java.util.List;
import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.server.connect.Endpoint;

@Endpoint
@AnonymousAllowed
public class PersonEndpoint {
  private PersonRepository repo;

  public PersonEndpoint(PersonRepository repo) {
    this.repo = repo;
  }

  public List<Person> listAll() {
    return repo.findAll();
  }

  public long count() {
    return repo.count();
  }

}
