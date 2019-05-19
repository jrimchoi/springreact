package com.jrimchoi.springreact.repositories;

import com.jrimchoi.springreact.models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, String> {
    @Override
    void delete(Contact deleted);
}
