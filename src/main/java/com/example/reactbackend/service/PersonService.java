package com.example.reactbackend.service;

import com.example.reactbackend.model.Person;
import com.example.reactbackend.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person find(Long id) {
        return personRepository
                .findById(id)
                .map(person -> {
                    if(id == 1) {
                        person.setFirstName(person.getFirstName().toUpperCase());
                        person.setLastName(person.getLastName().toUpperCase());
                        return person;
                    } else if (id == 2) {
                        person.setFirstName(person.getFirstName().toLowerCase());
                        person.setLastName(person.getLastName().toLowerCase());
                        return person;
                    } else {
                        return person;
                    }
                })
                .orElseThrow(() -> new NoSuchElementException("No person with id" + id));
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }
}
