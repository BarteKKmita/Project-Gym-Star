package com.learning.gym.star.gym;

import com.learning.gym.star.gym.database.jdbc.GymRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class was created to simulate getting gym data from SQL dadabase.
 */

public class GymDataHandler implements GymRepository {
    private final List<String> gymData;

    public GymDataHandler(){
        gymData = new ArrayList<>();
        generateGymData();
    }

    private void generateGymData(){
        for(int i = 0; i < 90; i++) {
            gymData.add(String.valueOf(i));
        }
    }

    @Override
    public List<String> getGymData(){
        return gymData;
    }

    @Override
    public String[] getGymDataById(int id){
        return new String[0];
    }

    @Override
    public String add(Gym gym){
        gymData.add(gym.toString());
        return "";
    }

    @Override
    public void update(Gym gym, int index){
        gymData.set(index, gym.toString());
    }

    @Override
    public void delete(int index){
        gymData.remove(index);
    }
}
