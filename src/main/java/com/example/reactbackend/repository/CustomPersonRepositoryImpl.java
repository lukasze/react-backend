package com.example.reactbackend.repository;

import com.example.reactbackend.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomPersonRepositoryImpl implements CustomPersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> findByFirstNameAndLastNameCustom(String firstName, String lastName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);

        Root<Person> person = cq.from(Person.class);

        Predicate namePredicate = cb.equal(person.get("firstName"), firstName);
        Predicate lastNamePredicate = cb.equal(person.get("lastName"), lastName);

        cq.where(namePredicate, lastNamePredicate);

        TypedQuery<Person> query = entityManager.createQuery(cq);

        return query.getResultList();
    }
}
