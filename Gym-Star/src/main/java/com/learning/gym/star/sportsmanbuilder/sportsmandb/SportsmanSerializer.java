package com.learning.gym.star.sportsmanbuilder.sportsmandb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

/*
This class will use Gson as soon as I will handle with dependency conflict.
Logger will be added after pull request.
 */
@Configuration
public class SportsmanSerializer {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public SportsmanDTO getSportsmanDTOFromSportsman(SportsmanDB sportsman){
        String sportsmanJson = "";
        try {
            sportsmanJson = objectMapper.writeValueAsString(sportsman);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        SportsmanDTO sportsmanDTO = null;
        try {
            sportsmanDTO = objectMapper.readValue(sportsmanJson, SportsmanDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return sportsmanDTO;
    }

    public SportsmanDB getSportsmanDBFromSportsmanDTO(SportsmanDTO sportsman){
        String sportsmanJson = "";
        try {
            sportsmanJson = objectMapper.writeValueAsString(sportsman);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        SportsmanDB sportsmanDB = null;
        try {
            sportsmanDB = objectMapper.readValue(sportsmanJson, SportsmanDB.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return sportsmanDB;
    }
}
