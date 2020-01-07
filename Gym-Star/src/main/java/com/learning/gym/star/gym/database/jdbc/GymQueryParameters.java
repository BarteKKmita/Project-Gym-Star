package com.learning.gym.star.gym.database.jdbc;

import com.learning.gym.star.gym.Gym;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class GymQueryParameters {

    List <String> getQueryParameters(int gymId){
        List <String> queryParameters = new ArrayList <>();
        queryParameters.add(Integer.toString(gymId));
        return queryParameters;
    }

    List <String> getQueryParameters(Gym gym){
        String[] gymDataArray = {gym.getGymId(), gym.getGymName(), gym.getStreet(), gym.getCity(), gym.getBuildingNumber()};
        List <String> gymData = new ArrayList <>();
        Collections.addAll(gymData, gymDataArray);
        return gymData;
    }

    List <String> getQueryParameters(Gym gym, int gymId){
        List <String> gymData = getQueryParameters(gym);
        String gymIdAsString = Integer.toString(gymId);
        gymData.add(gymIdAsString);
        return gymData;
    }
}
