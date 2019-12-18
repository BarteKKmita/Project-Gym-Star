package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsmanbuilder.userinput.UserText;

public interface SportsManBuilder {

    void chooseTrainer ( UserText userInput );

    void chooseTrainingPlan ( int trainingDays );

    ConcreteSportsMan getSportsMan ();
}
