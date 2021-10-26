package com.example.reactbackend.service;

import com.example.reactbackend.model.Person;
import com.example.reactbackend.repository.PersonRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Profile("lower")
@Service
public class PersonServiceImplLowercase implements PersonService{
    private PersonRepository personRepository;

    public PersonServiceImplLowercase(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person find(Long id) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setFirstName(person.getFirstName().toLowerCase());
                    person.setLastName(person.getLastName().toLowerCase());
                    return person;
                })
                .orElseThrow(() -> new NoSuchElementException("No person with id" + id));
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }
}
