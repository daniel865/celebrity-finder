package com.demo.model;

import java.util.List;

public class Team {

    private List<Person> people;

    public Team() {
    }

    public Team(List<Person> people) {
        this.people = people;
    }

    public List<Person> getPeople() {
        return people;
    }

}
