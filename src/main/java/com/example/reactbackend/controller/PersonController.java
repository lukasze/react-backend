package com.example.reactbackend.controller;

import com.example.reactbackend.model.Person;
import com.example.reactbackend.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

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
}
