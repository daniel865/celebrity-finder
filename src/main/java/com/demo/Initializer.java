package com.demo;

import com.demo.creator.ITeamCreator;
import com.demo.creator.impl.TeamCreator;
import com.demo.model.Person;
import com.demo.model.Team;
import com.demo.reader.IDataReader;
import com.demo.reader.impl.DataReader;
import com.demo.searcher.ICelebritySearcher;
import com.demo.searcher.impl.CelebritySearcher;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.nonNull;

public class Initializer {

    private final static Logger LOGGER = Logger.getLogger(Initializer.class.getName());

    public static void main(String[] args) {
        IDataReader dataReader = new DataReader();
        ITeamCreator teamCreator = new TeamCreator(dataReader);

        ICelebritySearcher celebritySearcher = new CelebritySearcher();

        Team team = teamCreator.createTeamFromFile(dataReader);

        Person celebrity = celebritySearcher.findCelebrity(team);

        if (nonNull(celebrity)) {
            System.out.println("The Celebrity in the team is: " + celebrity.getId());
        } else {
            System.out.println("Error: The team has a wrong configuration");
        }
    }

}
