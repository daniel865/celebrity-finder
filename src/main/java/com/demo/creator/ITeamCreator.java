package com.demo.creator;

import com.demo.model.Team;
import com.demo.reader.IDataReader;

/**
 * Interface that represents the behavior of the ITeamCreator. The ITeamCreator is in charge of creating a team
 * from different kind of sources.
 */
public interface ITeamCreator {

    /**
     * Create a Team using as source a File.
     * @param dataReader Object that read the data for creating a Team
     * @return Team created with all its data
     */
    Team createTeamFromFile(IDataReader dataReader);

}
