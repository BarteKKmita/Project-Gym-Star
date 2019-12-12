package com.learning.gym.star.gym;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GymQueryParameters {

    List <String> getQueryParameters ( int gymId ) {
        List <String> queryParameters = new ArrayList <>();
        queryParameters.add(Integer.toString(gymId));
        return queryParameters;
    }

    List <String> getQueryParameters ( Gym gym ) {
        String[] gymDataArray = {gym.getGym_id(), gym.getGym_name(), gym.getStreet(), gym.getCity(), gym.getBuilding_number()};
        List <String> gymData = new ArrayList <>();
        Collections.addAll(gymData, gymDataArray);
        return gymData;
    }

    List <String> getQueryParameters ( Gym gym, int gymId ) {
        List <String> gymData = getQueryParameters(gym);
        gymData.add(Integer.toString(gymId));
        return gymData;
    }

}
