package com.learning.Gym.Star;

import java.util.ArrayList;
import java.util.List;

/**
 * This class was created to simulate getting gym data from SQL dadabase.
 */

public class Gym {
    private List <String> gymData;

    Gym(){
    gymData=new ArrayList <>();
    generateGymData();
    }

    private void generateGymData(){
        for (int i = 0; i < 90; i++) {
            gymData.add(String.valueOf(i));
        }
    }
    public List <String> getGymData () {
        return gymData;
    }

}
