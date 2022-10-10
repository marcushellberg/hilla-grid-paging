package com.example.application.data.endpoint;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;

@Endpoint
@AnonymousAllowed
public class PersonEndpoint {
  private PersonRepository repo;

  public PersonEndpoint(PersonRepository repo) {
    this.repo = repo;
  }

  static record PageResponse(List<Person> content, long size) {
  }

  public PageResponse getPage(int page, int size) {
    var dbPage = repo.findAll(PageRequest.of(page, size));

    return new PageResponse(dbPage.getContent(), dbPage.getTotalElements());
  }

  public long count() {
    return repo.count();
  }

}
