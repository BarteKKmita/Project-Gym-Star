package com.learning.gym.star.gym.database.jdbc;

import com.learning.gym.star.gym.Gym;

import java.util.List;

public interface GymRepository {


    String add(Gym gym);

    void update ( Gym gym, int index );

    void delete ( int index );

    List <String> getGymData ();

    String[] getGymDataById ( int id );
}
