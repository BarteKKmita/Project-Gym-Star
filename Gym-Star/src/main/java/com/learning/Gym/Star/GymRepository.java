package com.learning.Gym.Star;

public interface GymRepository<T extends GymDataHandler> {

    void add ( String gym );

    void update ( String gym, int index );

    void remove ( int index );
}
