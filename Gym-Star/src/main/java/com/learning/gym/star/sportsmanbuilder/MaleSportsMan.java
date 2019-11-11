package com.learning.gym.star.sportsmanbuilder;


import com.learning.gym.star.GenderChoose;

public class MaleSportsMan implements SportsManBuilder {
    private ConcreteSportsMan sportsMan;

    public MaleSportsMan(String name, String surname){
        sportsMan=new ConcreteSportsMan(name, surname);
        sportsMan.setGender(GenderChoose.M);
    }

    public MaleSportsMan(ConcreteSportsMan sportsMan){
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
