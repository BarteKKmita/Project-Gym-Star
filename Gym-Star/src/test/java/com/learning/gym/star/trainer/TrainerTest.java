package com.learning.gym.star.trainer;

import com.learning.gym.star.sportsmanbuilder.ConcreteSportsMan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainerTest {

    @Test
    void printSportsMenRecursive () {
        //Given
        String name = "Name";
        String surname = "Surname";
        Trainer trainer = new Trainer(name, surname, 3);
        trainer.addSportsMan(new ConcreteSportsMan(name, surname));
        trainer.addSportsMan(new ConcreteSportsMan("Ania", "Wojcik"));
        trainer.addSportsMan(new ConcreteSportsMan("Bartek", "Kmita"));
        String expectedOutput = name + ", " + "Ania" + ", " + "Bartek" + ", ";
        //When
        String output = trainer.printSportsManAuxiliary();
        //Then
        assertEquals(expectedOutput, output);
    }

    @Test
    void printSportsMen () {
        //Given
        String name = "Name";
        String surname = "Surname";
        Trainer trainer = new Trainer(name, surname, 3);
        trainer.addSportsMan(new ConcreteSportsMan(name, surname));
        trainer.addSportsMan(new ConcreteSportsMan("Ania", "Wojcik"));
        trainer.addSportsMan(new ConcreteSportsMan("Bartek", "Kmita"));
        //When
        String output = trainer.printSportsMen();
        //Then
        assertEquals(name + ", " + "Ania" + ", " + "Bartek" + ", ", output);
    }
}