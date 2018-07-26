package com.demo;

import com.demo.builders.TeamBuilder;
import com.demo.model.Person;
import com.demo.model.Team;
import com.demo.searcher.impl.CelebritySearcher;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CelebritySearcherTest {

    @Test
    public void whenValidTeamIsGivenExpectedReturnIdOfCelebrity() {
        Team teamWithCelebrity = TeamBuilder.createTeamWithCelebrity();

        CelebritySearcher celebritySearcher = new CelebritySearcher();

        Person celebrity = celebritySearcher.findCelebrity(teamWithCelebrity);

        assertThat(celebrity.getId()).isEqualToIgnoringCase("A");
    }

    @Test
    public void whenTeamWithoutCelebrityIsGivenExpectedReturnNull() {
        Team teamWithoutCelebrity = TeamBuilder.createTeamWithoutCelebrity();

        CelebritySearcher celebritySearcher = new CelebritySearcher();

        Person celebrity = celebritySearcher.findCelebrity(teamWithoutCelebrity);

        assertThat(celebrity).isNull();
    }

    @Test
    public void whenTeamHasMoreThanOneCelebrityIsGivenExpectedReturnNull() {
        Team teamWithMoreThanOneCelebrity = TeamBuilder.createTeamWithMoreThanOneCelebrity();

        CelebritySearcher celebritySearcher = new CelebritySearcher();

        Person celebrity = celebritySearcher.findCelebrity(teamWithMoreThanOneCelebrity);

        assertThat(celebrity).isNull();
    }


}
