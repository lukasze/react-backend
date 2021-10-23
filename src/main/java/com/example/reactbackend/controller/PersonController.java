package com.example.reactbackend.controller;

import com.example.reactbackend.service.PersonService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
}
