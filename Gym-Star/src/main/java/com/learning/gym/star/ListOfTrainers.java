package com.learning.gym.star;

import java.util.ArrayList;
import java.util.List;

public class ListOfTrainers {
    private List <Trainer> listOfTrainers = new ArrayList <>();

    public ListOfTrainers () {
        listOfTrainers.add(new Trainer("Łukasz", "Tereszko", 40));
        listOfTrainers.add(new Trainer("Mariusz", "Gawryś", 50));
        listOfTrainers.add(new Trainer("Wiktoria", "Balon", 35));
        listOfTrainers.add(new Trainer("Weronika", "Kosmowska", 45));
    }

    public List <Trainer> getListOfTrainers () {
        return listOfTrainers;
    }
}
