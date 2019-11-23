package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsman.userinput.UserText;

public interface SportsManBuilder {

    void chooseTrainer ( UserText userInput );

    void chooseTrainingPlan();

    ConcreteSportsMan getSportsMan ();
}
