package com.learning.gym.star.sportsmanbuilder;


import com.learning.gym.star.sportsman.GenderChoose;

public class MaleSportsMan extends SportsMan implements SportsManBuilder {
    private ConcreteSportsMan sportsMan;

    public MaleSportsMan(String name, String surname){
        sportsMan=new ConcreteSportsMan(name, surname);
        sportsMan.withGender(GenderChoose.M);
    }

    public MaleSportsMan(ConcreteSportsMan sportsMan){
        this.sportsMan=sportsMan;
    }

    @Override
    public void chooseTrainer () {
        sportsMan.withMyTrainer(trainerBuilder());
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
