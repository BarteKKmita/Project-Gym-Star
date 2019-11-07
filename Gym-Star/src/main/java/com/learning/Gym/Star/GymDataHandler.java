package com.learning.Gym.Star;

import java.util.ArrayList;
import java.util.List;
/**
 * This class was created to simulate getting gym data from SQL dadabase.
 */

class GymDataHandler implements GymRepository <GymDataHandler> {
    private List <String> gymData;

    GymDataHandler () {
        gymData = new ArrayList <>();
        generateGymData();
    }

    private void generateGymData () {
        for (int i = 0; i < 90; i++) {
            gymData.add(String.valueOf(i));
        }
    }

    List <String> getGymData () {
        return gymData;
    }

    @Override
    public void add ( String gym ) {
        gymData.add(gym);
    }

    @Override
    public void update ( String gym, int index ) {
        gymData.set(index, gym);
    }

    @Override
    public void remove ( int index ) {
        gymData.remove(index);
    }
}
