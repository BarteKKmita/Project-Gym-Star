package com.learning.gym.star.gym;

import java.util.List;

public interface GymRepository {


    void add ( Gym gym );

    void update ( Gym gym, int index );

    void remove ( int index );

    List <String> getGymData ();

    String[] getGymDataById ( int id );
}
