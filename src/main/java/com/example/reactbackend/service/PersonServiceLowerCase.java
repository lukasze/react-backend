package com.example.reactbackend.service;

import com.example.reactbackend.model.Person;
import com.example.reactbackend.repository.PersonRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Profile("lower")
@Service
public class PersonServiceLowerCase implements PersonServiceI {
    private PersonRepository personRepository;

    public PersonServiceLowerCase(PersonRepository personRepository) {
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
            person.setFirstName(person.getFirstName().toLowerCase());
            person.setLastName(person.getLastName().toLowerCase());
            return person;
        }).orElseThrow(() -> new NoSuchElementException("No person with id" + id));
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }
}
