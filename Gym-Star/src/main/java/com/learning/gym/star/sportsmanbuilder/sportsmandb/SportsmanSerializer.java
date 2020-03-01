package com.learning.gym.star.sportsmanbuilder.sportsmandb;

import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import com.learning.gym.star.training.cardio.CardioTrainingEntity;
import com.learning.gym.star.training.power.PowerTrainingEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class SportsmanSerializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SportsmanSerializer.class);

    public SportsmanDTO getSportsmanDTOFromSportsman(SportsmanEntity sportsman){
        LOGGER.info("Attempt to serialize Sportsman Entity to Sportsman DTO for sportsman ID: {}", sportsman.getSportsmanPesel());
        return SportsmanDTO.builder()
                .sportsmanPesel(sportsman.getSportsmanPesel())
                .name(sportsman.getName())
                .surname(sportsman.getSurname())
                .gender(sportsman.getGender())
                .build();
    }

    public SportsmanEntity buildSportsmanFromSportsmanDTO(SportsmanDTO sportsman){
        LOGGER.info("Attempt to serialize Sportsman DTO to Sportsman Entity for sportsman ID: {}", sportsman.getSportsmanPesel());
        return SportsmanEntity.builder()
                .sportsmanPesel(sportsman.getSportsmanPesel())
                .name(sportsman.getName())
                .surname(sportsman.getSurname())
                .gender(sportsman.getGender())
                .statistics(StatisticsEntity.builder()
                        .cardioTrainingEntity(new CardioTrainingEntity())
                        .powerTrainingEntity(new PowerTrainingEntity())
                        .build())
                .gender(GenderChoose.valueOf(sportsman.getGender()))
                .statistics(new StatisticsDB(null, new CardioTrainingDB(), new PowerTrainingDB()))
                .build();
    }
}
