package com.learning.gym.star.sportsmanbuilder;

public class SportsManDirector {
    private SportsManBuilder sportsManBuilder;

    public SportsManDirector ( SportsManBuilder sportsManBuilder ) {
        this.sportsManBuilder = sportsManBuilder;
    }

    public void setConcreteSportsMan(){
        sportsManBuilder.chooseTrainer();
        sportsManBuilder.chooseTrainingPlan();
    }

    public ConcreteSportsMan getConcreteSportsMan(){
    return sportsManBuilder.getSportsMan();
    }
}
