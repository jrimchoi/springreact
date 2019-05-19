package com.jrimchoi.springreact.controllers;

import com.jrimchoi.springreact.models.Contact;
import com.jrimchoi.springreact.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @RequestMapping(method=RequestMethod.GET, value="/contacts")
    public Iterable<Contact> contact() {
        return contactRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/contacts")
    public Contact save(@RequestBody Contact contact) {
        contactRepository.save(contact);

        return contact;
    }

    @RequestMapping(method=RequestMethod.GET, value="/contacts/{id}")
    public Optional<Contact> show(@PathVariable String id) {
        return contactRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/contacts/{id}")
    public Contact update(@PathVariable String id, @RequestBody Contact contact) {
        Optional<Contact> optcontact = contactRepository.findById(id);
        Contact c = optcontact.get();
        if(contact.getName() != null)
            c.setName(contact.getName());
        if(contact.getAddress() != null)
            c.setAddress(contact.getAddress());
        if(contact.getCity() != null)
            c.setCity(contact.getCity());
        if(contact.getPhone() != null)
            c.setPhone(contact.getPhone());
        if(contact.getEmail() != null)
            c.setEmail(contact.getEmail());
        contactRepository.save(c);
        return c;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/contacts/{id}")
    public String delete(@PathVariable String id) {
        Optional<Contact> optcontact = contactRepository.findById(id);
        Contact contact = optcontact.get();
        contactRepository.delete(contact);

        return "";
    }
}
