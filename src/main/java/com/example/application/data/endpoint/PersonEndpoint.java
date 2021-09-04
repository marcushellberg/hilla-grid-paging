package com.example.application.data.endpoint;

import java.util.List;
import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.server.connect.Endpoint;
import org.springframework.data.domain.PageRequest;

@Endpoint
@AnonymousAllowed
public class PersonEndpoint {
  private PersonRepository repo;

  public PersonEndpoint(PersonRepository repo) {
    this.repo = repo;
  }

  class PageResponse<T> {
    public List<T> content;
    public long size;
  }

  public PageResponse<Person> getPage(int page, int size) {
    var dbPage = repo.findAll(PageRequest.of(page, size));

    var response = new PageResponse<Person>();
    response.content = dbPage.getContent();
    response.size = dbPage.getTotalElements();

    return response;
  }

  public long count() {
    return repo.count();
  }

}
