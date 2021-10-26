package com.example.reactbackend.service;

import com.example.reactbackend.model.Person;
import com.example.reactbackend.repository.PersonRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Profile("upper")
public class PersonServiceImplUppercase implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImplUppercase(PersonRepository personRepository) {
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
                .map( person -> {
                        person.setFirstName(person.getFirstName().toUpperCase());
                        person.setLastName(person.getLastName().toUpperCase());
                        return person;
                    }
                )
                .orElseThrow(() -> new NoSuchElementException("No person with id" + id));
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }
}
