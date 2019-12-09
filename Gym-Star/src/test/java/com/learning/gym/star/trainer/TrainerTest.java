package com.learning.gym.star.trainer;

import com.learning.gym.star.sportsman.GenderChoose;
import com.learning.gym.star.sportsman.SportsMan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainerTest {

    @Test
    void printSportsMen () {
        //Given
        String name = "Name";
        String surname = "Surname";
        Trainer trainer = new Trainer(name, surname, 3);
        trainer.addSportsMan(new SportsMan(name, surname, GenderChoose.M));
        trainer.addSportsMan(new SportsMan("Ania", "Wojcik", GenderChoose.W));
        trainer.addSportsMan(new SportsMan("Bartek", "Kmita", GenderChoose.M));
        //When
        String output = trainer.printSportsManAuxiliary();
        //Then
        assertEquals(name + ", " + "Ania" + ", " + "Bartek" + ", ", output);
    }
}