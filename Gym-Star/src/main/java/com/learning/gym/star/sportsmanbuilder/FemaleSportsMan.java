package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsman.GenderChoose;

public class FemaleSportsMan implements SportsManBuilder{
    private ConcreteSportsMan sportsMan;

    public FemaleSportsMan ( String name, String surname){
        sportsMan=new ConcreteSportsMan(name, surname);
        sportsMan.setGender(GenderChoose.M);
    }

    public FemaleSportsMan ( ConcreteSportsMan sportsMan){
        this.sportsMan=sportsMan;
    }

    @Override
    public void chooseTrainer () {
        sportsMan.setTrainer(trainerBuilder());
    }

    @Override
    public void chooseTrainingPlan(){
        sportsMan.setTrainingPlan(trainingPlanBuilder(sportsMan.getMyTrainer(),sportsMan, 3));
    }

    @Override
    public ConcreteSportsMan getSportsMan () {
        return sportsMan;
    }
}
