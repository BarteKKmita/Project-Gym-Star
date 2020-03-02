package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsmanbuilder.gender.GenderEnum;
import com.learning.gym.star.sportsmanbuilder.userinput.UserText;

public class FemaleSportsMan extends SportsMan implements SportsManBuilder {

    private ConcreteSportsMan sportsMan;

    public FemaleSportsMan ( ConcreteSportsMan sportsMan ) {
        this.sportsMan = sportsMan;
    }

    public FemaleSportsMan ( String name, String surname ) {
        sportsMan = new ConcreteSportsMan(name, surname);
        sportsMan = sportsMan.withGender(GenderEnum.F);
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
