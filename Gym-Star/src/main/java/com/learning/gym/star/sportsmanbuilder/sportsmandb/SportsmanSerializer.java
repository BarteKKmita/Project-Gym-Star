package com.learning.gym.star.sportsmanbuilder.sportsmandb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.gym.star.sportsmanbuilder.gender.GenderChoose;
import com.learning.gym.star.statistics.statisticsdb.StatisticsDB;
import com.learning.gym.star.training.cardio.CardioTrainingDB;
import com.learning.gym.star.training.power.PowerTrainingDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class SportsmanSerializer {
    private static final Logger logger = LoggerFactory.getLogger(SportsmanSerializer.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public SportsmanDTO getSportsmanDTOFromSportsman(SportsmanDB sportsman){
        String sportsmanJson = "";
        try {
            sportsmanJson = objectMapper.writeValueAsString(sportsman);
        } catch (JsonProcessingException e) {
            logger.error("Serialization of Sportsman failure.", e);
            logger.debug("Sportsman data. {}", sportsman);
        }
        SportsmanDTO sportsmanDTO = null;
        try {
            sportsmanDTO = objectMapper.readValue(sportsmanJson, SportsmanDTO.class);
        } catch (JsonProcessingException e) {
            logger.error("Deserialization of Sportsman failure.", e);
            logger.debug("Sportsman data. {}", sportsman);
        }
        return sportsmanDTO;
    }

    public SportsmanDB buildSportsmanFromSportsmanDTO(SportsmanDTO sportsman){
        return SportsmanDB.builder()
                .sportsmanPesel((Long.valueOf(sportsman.getSportsmanPesel().toString())))
                .name(sportsman.getName())
                .surname(sportsman.getSurname())
                .gender(GenderChoose.valueOf(sportsman.getGender()))
                .statistics(new StatisticsDB(null, new CardioTrainingDB(), new PowerTrainingDB()))
                .build();
    }
}
