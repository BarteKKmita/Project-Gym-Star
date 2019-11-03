package com.learning.Gym.Star;


import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.jupiter.api.Test;
import org.junit.*;
import java.io.ByteArrayInputStream;


import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.junit.jupiter.api.Assertions.*;


public class SportsManTest {

//    @Before
//    void initListOfTrainers () {
//        ListOfTrainers listOfTrainers = new ListOfTrainers();
//        SportsMan sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M ) ;
//        String gender = "Male";
//    }

    @Test
    void shouldReturnMaleWhenM () {
        //Given
        ConcreteSportsMan sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M);
        String expectedGender = "Male";
        //When
        String outPutGender = sportsMan.getGender();
        //Then
        assertEquals(expectedGender, outPutGender);
    }



    @Test
    void shouldSelectTrainerMariuszFromList () {
        //Given
        ByteArrayInputStream testIn;
        String data = "Mariusz";
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        ConcreteSportsMan sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M);
        //When
        sportsMan.chooseTrainer();
        //Then
        assertEquals(data, sportsMan.getMyTrainer().getName());
    }

    @Test
    void shouldSelectTrainerMariuszFromList2 (){
        //Given

        ByteArrayInputStream testInName;
        String name = "Mariusz";
        testInName = new ByteArrayInputStream(name.getBytes());
        System.setIn(testInName);
        ByteArrayInputStream testInSurname;
        ConcreteSportsMan sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M);
        //When
        String surname = "Gawryś";
        testInSurname = new ByteArrayInputStream(surname.getBytes());
        System.setIn(testInSurname);
        sportsMan.chooseTrainer();
        //Then

        assertEquals(name, sportsMan.getMyTrainer().getName());
        //assertEquals(surname, sportsMan.getMyTrainer().getSurname());
    }

    @Test
    void shouldSelectTrainerMariuszFromList3 (){
        //Given
        final TextFromStandardInputStream systemInMock
                = emptyStandardInputStream();
        String name = "Mariusz";
        ConcreteSportsMan sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M);
        systemInMock.provideLines("Mariusz", "Gawryś");
        sportsMan.chooseTrainer();
        //assertEquals(name,sportsMan.getMyTrainer().getName());
    }

    @Test
    void shouldReturnStringWithCurrentDate(){
        //Given
        ConcreteSportsMan sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M);
        //When
        sportsMan.train();

    }

}
