package com.learning.Gym.Star.sportsmanbuilder;

public class SportsManDirector {
    private SportsManBuilder maleSportsManBuilder;

    public SportsManDirector ( SportsManBuilder maleSportsManBuilder ) {
        this.maleSportsManBuilder = maleSportsManBuilder;
    }

    public void setConcreteSportsMan(){
        maleSportsManBuilder.chooseTrainer();
        maleSportsManBuilder.chooseTrainingPlan();
    }

    public ConcreteSportsMan getConcreteSportsMan(){
    return maleSportsManBuilder.getSportsMan();
    }
}
