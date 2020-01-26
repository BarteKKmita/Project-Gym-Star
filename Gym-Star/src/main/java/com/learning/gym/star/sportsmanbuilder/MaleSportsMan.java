package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsmanbuilder.gender.GenderChoose;
import com.learning.gym.star.sportsmanbuilder.userinput.UserText;

public class MaleSportsMan extends SportsMan implements SportsManBuilder {
    private ConcreteSportsMan sportsMan;

    public MaleSportsMan ( String name, String surname ) {
        sportsMan = new ConcreteSportsMan(name, surname);
        sportsMan = sportsMan.withGender(GenderChoose.M);
    }

    public MaleSportsMan ( ConcreteSportsMan sportsMan ) {
        this.sportsMan = sportsMan;
    }

    @Override
    public void chooseTrainer ( UserText userInput ) {
        sportsMan = sportsMan.withMyTrainer(trainerBuilder(userInput));
    }

    @Override
    public void chooseTrainingPlan ( int trainingDays ) {
        sportsMan = sportsMan.withTrainings(trainingPlanBuilder(sportsMan.getMyTrainer(), sportsMan, trainingDays));
    }

    @Override
    public ConcreteSportsMan getSportsMan () {
        return sportsMan;
    }
}
