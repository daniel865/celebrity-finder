package com.demo.searcher.impl;

import com.demo.model.Person;
import com.demo.model.Team;
import com.demo.searcher.ICelebritySearcher;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * This class is an implementation of the interface ICelebritySearcher that uses an Stack to store the members that
 * may be the celebrity.
 */
public class CelebritySearcher implements ICelebritySearcher {

    /**
     * Creates a stack of the people that is part of a team.
     * @param team Team with all its members
     * @return Stack with all of the persons that are part of the team
     */
    private Stack<Person> createStackOfPeople(Team team) {
        final Stack<Person> people = new Stack<>();
        team.getPeople().stream().forEach(person -> people.push(person));
        return people;
    }

    /**
     * Search a celebrity in a team. If the celebrity is found returns a Person, otherwise,
     * return a null value
     * @param team Team that contains all its members
     * @return Value that indicates if a celebrity was found
     */
    public Person findCelebrity(Team team) {
        if (checkValidityTeam(team)) {
            Stack<Person> stackOfPeople = createStackOfPeople(team);

            while (stackOfPeople.size() > 1) {
                Person person1 = stackOfPeople.pop();
                Person person2 = stackOfPeople.pop();

                if (knows(person1, person2)) {
                    stackOfPeople.push(person2);
                } else {
                    stackOfPeople.push(person1);
                }
            }

            Person lastingPerson = stackOfPeople.pop();

            return isCelebrity(lastingPerson) ? lastingPerson : null;
        }
        return null;
    }

    /**
     * Check if a person is a celebrity. If a person doesn't have any relations with anybody return true, false otherwise
     * @param possibleCelebrity Person to check if is a celebrity
     * @return Boolean value that indicates that a person is a celebrity
     */
    private Boolean isCelebrity(Person possibleCelebrity) {
        return possibleCelebrity.getPersonAcquainted() == null ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * Returns true if the person1 knows the person2, false otherwise
     * @param person1 Person 1
     * @param person2 Person 2
     * @return Boolean value that indicates if person1 knows person2
     */
    private Boolean knows(Person person1, Person person2) {
        return person1.getPersonAcquainted() != null && person1.getPersonAcquainted().equals(person2) ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * Check if a Team is valid based on the following rule. A team is valid if have only one celebrity per team and
     * the team has a celebrity
     * @param team Team with its members
     * @return Boolean value that indicates that the team accomplish the previous rules
     */
    private Boolean checkValidityTeam(Team team) {
        List<Person> celebrities = team.getPeople()
                .stream()
                .filter(this::isCelebrity)
                .collect(Collectors.toList());

        return celebrities.size() == 1 ? Boolean.TRUE : Boolean.FALSE;
    }



}
