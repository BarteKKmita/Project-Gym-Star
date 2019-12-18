package com.learning.gym.star.gym.database;

import com.learning.gym.star.gym.Gym;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GymQueryParameters {

    List <String> getQueryParameters ( int gymId ) {
        List <String> queryParameters = new ArrayList <>();
        queryParameters.add(Integer.toString(gymId));
        return queryParameters;
    }

    List <String> getQueryParameters ( Gym gym ) {
        String[] gymDataArray = {gym.getGymId(), gym.getGymName(), gym.getStreet(), gym.getCity(), gym.getBuildingNumber()};
        return Arrays.asList(gymDataArray);
    }

    List <String> getQueryParameters ( Gym gym, int gymId ) {
        List <String> gymData = getQueryParameters(gym);
        gymData.add(Integer.toString(gymId));
        return gymData;
    }

}
