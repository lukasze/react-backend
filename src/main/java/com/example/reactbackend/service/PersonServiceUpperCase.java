package com.example.reactbackend.service;

import com.example.reactbackend.model.Person;
import com.example.reactbackend.repository.PersonRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

@Profile("upper")
@Service
public class PersonServiceUpperCase implements PersonServiceI {

    private PersonRepository personRepository;

    public PersonServiceUpperCase(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person find(Long id) {
        return personRepository.findById(id).map(person -> {
            person.setFirstName(person.getFirstName().toUpperCase());
            person.setLastName(person.getLastName().toUpperCase());
            return person;
        }).orElseThrow(() -> new NoSuchElementException("No person with id" + id));
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }
}
