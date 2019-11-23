package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsman.GenderChoose;
import com.learning.gym.star.sportsman.userinput.UserText;

public class FemaleSportsMan extends SportsMan implements SportsManBuilder{
    private ConcreteSportsMan sportsMan;

    public FemaleSportsMan ( String name, String surname){
        sportsMan=new ConcreteSportsMan(name, surname);
        sportsMan.withGender(GenderChoose.M);
    }

    public FemaleSportsMan(ConcreteSportsMan sportsMan){
        this.sportsMan=sportsMan;
    }

    @Override
    public void chooseTrainer (UserText userInput) {
        sportsMan.withMyTrainer(trainerBuilder(userInput));
    }

    @Override
    public void chooseTrainingPlan(){
        sportsMan.withTrainings(trainingPlanBuilder(sportsMan.getMyTrainer(),sportsMan, 3));
    }

    @Override
    public ConcreteSportsMan getSportsMan () {
        return sportsMan;
    }
}
