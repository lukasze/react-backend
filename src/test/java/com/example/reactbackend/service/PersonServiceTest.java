package com.example.reactbackend.service;

import com.example.reactbackend.model.Person;
import com.example.reactbackend.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
    void find_upper() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        Person person = personService.find(1L);

        assertAll(
                () -> assertEquals(firstName.toUpperCase(), person.getFirstName()),
                () -> assertEquals(lastName.toUpperCase(), person.getLastName())
        );
    }

    @Test
    void find_lower() {
        when(personRepository.findById(2L)).thenReturn(Optional.of(person));

        Person person = personService.find(2L);

        assertAll(
                () -> assertEquals(firstName.toLowerCase(), person.getFirstName()),
                () -> assertEquals(lastName.toLowerCase(), person.getLastName())
        );
    }

    @Test
    void find_unchanged() {
        when(personRepository.findById(3L)).thenReturn(Optional.of(person));

        Person person = personService.find(3L);

        assertAll(
                () -> assertEquals(firstName, person.getFirstName()),
                () -> assertEquals(lastName, person.getLastName())
        );
    }
}