package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsman.userinput.UserText;

public class SportsManDirector {
    private SportsManBuilder sportsManBuilder;

    public SportsManDirector ( SportsManBuilder sportsManBuilder ) {
        this.sportsManBuilder = sportsManBuilder;
    }

    public void setConcreteSportsMan( UserText userInput ){
        sportsManBuilder.chooseTrainer(userInput);
        sportsManBuilder.chooseTrainingPlan();
    }

    public ConcreteSportsMan getConcreteSportsMan(){
    return sportsManBuilder.getSportsMan();
    }
}
