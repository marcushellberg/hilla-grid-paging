package com.example.application.data.endpoint;

import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonService;

import java.util.List;
import java.util.Optional;

import com.vaadin.flow.server.connect.Endpoint;

import org.vaadin.artur.helpers.GridSorter;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.PagingUtil;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import javax.annotation.Nullable;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Endpoint
@AnonymousAllowed
public class PersonEndpoint {

    private PersonService service;

    public PersonEndpoint(@Autowired PersonService service) {
        this.service = service;
    }

    public List<Person> list(int offset, int limit, List<GridSorter> sortOrder) {
        Page<Person> page = service
                .list(PagingUtil.offsetLimitTypeScriptSortOrdersToPageable(offset, limit, sortOrder));
        return page.getContent();
    }

    public Optional<Person> get(Integer id) {
        return service.get(id);
    }

    public Person update(Person entity) {
        return service.update(entity);
    }

    public void delete(Integer id) {
        service.delete(id);
    }

    public int count() {
        return service.count();
    }

}
