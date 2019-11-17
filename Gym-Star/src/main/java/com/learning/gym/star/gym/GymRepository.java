package com.learning.gym.star.gym;

public interface GymRepository {

    void add ( String gym );

    void update ( String gym, int index );

    void remove ( int index );
}
