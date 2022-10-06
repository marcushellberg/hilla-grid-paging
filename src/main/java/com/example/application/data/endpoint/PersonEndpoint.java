package com.example.application.data.endpoint;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;

@Endpoint
@AnonymousAllowed
public class PersonEndpoint {
  private PersonRepository repo;

  public PersonEndpoint(PersonRepository repo) {
    this.repo = repo;
  }

  static class PageResponse {
    @Nonnull
    public List<@Nonnull Person> content;
    @Nonnull
    public long size;

    PageResponse(List<Person> content, long size) {
      this.content = content;
      this.size = size;
    }
  }

  public PageResponse getPage(int page, int size) {
    var dbPage = repo.findAll(PageRequest.of(page, size));

    return new PageResponse(dbPage.getContent(), dbPage.getTotalElements());
  }

  public long count() {
    return repo.count();
  }

}
