package com.learning.gym.star.gym.database.jdbc;

import com.learning.gym.star.gym.Gym;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class GymQueryParameters {

    List<String> getGymIdAsList(int gymId){
        List<String> queryParameters = new ArrayList<>();
        queryParameters.add(Integer.toString(gymId));
        return queryParameters;
    }

    List<String> getGymAsList(Gym gym){
        List<String> gymData = new ArrayList<>();
        Collections.addAll(gymData, gym.toStringArray());
        return gymData;
    }

    List<String> getGymAsList(Gym gym, int gymId){
        List<String> gymData = getGymAsList(gym);
        String gymIdAsString = Integer.toString(gymId);
        gymData.add(gymIdAsString);
        return gymData;
    }
}
