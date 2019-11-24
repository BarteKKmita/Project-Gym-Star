package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsman.userinput.ConsoleUserInput;
import com.learning.gym.star.sportsman.userinput.UserText;

public class SportsManDirector {
    private SportsManBuilder sportsManBuilder;
    private static int defaultTrainingDays = 12;

    public SportsManDirector ( SportsManBuilder sportsManBuilder ) {
        this.sportsManBuilder = sportsManBuilder;
    }

    public void setConcreteSportsMan () {
        sportsManBuilder.chooseTrainer(new ConsoleUserInput());
        sportsManBuilder.chooseTrainingPlan(defaultTrainingDays);
    }

    public void setConcreteSportsMan ( UserText userInput ) {
        sportsManBuilder.chooseTrainer(userInput);
        sportsManBuilder.chooseTrainingPlan(defaultTrainingDays);
    }

    public void setConcreteSportsMan ( UserText userInput, int trainingDays ) {
        sportsManBuilder.chooseTrainer(userInput);
        sportsManBuilder.chooseTrainingPlan(trainingDays);
    }

    public ConcreteSportsMan getConcreteSportsMan () {
        return sportsManBuilder.getSportsMan();
    }
}
