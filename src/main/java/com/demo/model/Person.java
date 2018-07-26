package com.demo.model;

import java.util.List;
import java.util.Objects;

public class Person {

    private String id;
    private Person personAcquainted;

    public Person() {
    }

    public Person(String id, Person personAcquainted) {
        this.id = id;
        this.personAcquainted = personAcquainted;
    }

    public String getId() {
        return id;
    }

    public Person getPersonAcquainted() {
        return personAcquainted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(personAcquainted, person.personAcquainted);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, personAcquainted);
    }


    @Override
    public String   toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", personAcquainted=" + personAcquainted +
                '}';
    }
}
