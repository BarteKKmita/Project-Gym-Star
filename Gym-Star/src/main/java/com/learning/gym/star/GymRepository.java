package com.learning.gym.star;

public interface GymRepository<GymDataHandler> {

    void add ( String gym );

    void update ( String gym, int index );

    void remove ( int index );
}
