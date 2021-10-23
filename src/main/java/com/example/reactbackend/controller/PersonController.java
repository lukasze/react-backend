package com.example.reactbackend.controller;

import com.example.reactbackend.model.Person;
import com.example.reactbackend.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PersonController {

    public static final String ID_NOT_FOUND_ERROR_MSG = "Person Not Found, id: ";

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/people")
    List<Person> findAll() {
        return personService.findAll();
    }

    @PostMapping("/people")
    Person save(@RequestBody Person person) {
        return personService.save(person);
    }

    @DeleteMapping("/people/{id}")
    @Transactional
    void delete(@PathVariable Long id) {
        Person personToDelete;
        try {
            personToDelete = personService.find(id);
        } catch (NoSuchElementException nse) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ID_NOT_FOUND_ERROR_MSG + id, nse);
        }
        personService.delete(personToDelete);
    }
}
