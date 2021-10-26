package com.example.reactbackend.service;

import com.example.reactbackend.model.Person;
import com.example.reactbackend.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {


    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;

    private String firstName = "Yuval";
    private String lastName = "Harari";
    private Person person = new Person(1l, firstName, lastName);

    @Test
    void find() {

    }
}