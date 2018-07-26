package com.demo.creator.impl;

import com.demo.creator.ITeamCreator;
import com.demo.model.Person;
import com.demo.model.Team;
import com.demo.reader.IDataReader;
import com.demo.reader.impl.DataReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

public class TeamCreator implements ITeamCreator {

    private final IDataReader dataReader;

    public TeamCreator(IDataReader dataReader) {
        this.dataReader = dataReader;
    }

    @Override
    public Team createTeamFromFile(IDataReader dataReader) {
        StringBuilder data = dataReader.readTeamFromFile();

        if (nonNull(data)) {
            // Obtain the information of the File in array of String representing the lines of the File
            String[] lines = data.toString().split("\\n");

            // Get the first line that represent the size  of the Team
            Integer sizeOfTeam = Integer.parseInt(lines[0]);

            // Get the second line that have the information of the Celebrity
            String dataCelebrity = lines[1];

            // It's created the Celebrity
            Person celebrity = createTeamMember(dataCelebrity, null);

            // Create lasting members of the Team
            List<Person> members = createMembers(lines, celebrity);

            // Merge the celebrity and members of the Team
            List<Person> people = Stream.concat(Arrays.asList(celebrity).stream(), members.stream()).collect(Collectors.toList());

            return new Team(people);
        }

        return null;
    }

    /**
     * Create the lasting members of the team using the lines of the File.
     * @param lines Lines of the file
     * @param celebrity Celebrity of the team
     * @return List with the lasting members
     */
    private List<Person> createMembers(String[] lines, Person celebrity) {
        List<Person> members = new LinkedList<>();

        for (int i = 2; i < lines.length; i++) {
            members.add(createTeamMember(lines[i], celebrity));
        }

        return members;
    }

    /**
     * Create a Team member based on a string that represents the person's data.
     * @param line Person's data
     * @param celebrity Celebrity of the team
     * @return Person created with all the data
     */
    private Person createTeamMember(String line, Person celebrity) {
        String[] data = line.split(",");
        return new Person(data[0], celebrity);
    }

}
