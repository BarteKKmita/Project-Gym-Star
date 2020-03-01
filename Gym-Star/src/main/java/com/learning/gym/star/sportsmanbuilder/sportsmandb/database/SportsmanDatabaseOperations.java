package com.learning.gym.star.sportsmanbuilder.sportsmandb.database;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanEntity;
import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsEntity;
import com.learning.gym.star.trainer.trainerdb.TrainerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import java.util.List;

@Component
public final class SportsmanDatabaseOperations {
    private static final String PERSONAL_TRAINER_QUERY = "setPersonalTrainer";
    private static final String DATE_TIME_STATS_PROCEDURE = "sportsmanStats";
    private static final String TRAIN_CARDIO_PROCEDURE = "trainCardio";
    private static final String TRAIN_POWER_PROCEDURE = "trainPower";
    private EntityManager entityManager;

    public SportsmanDatabaseOperations(@Autowired EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<TrainingDateStatisticsEntity> getTrainingDateTimeStatistics(SportsmanEntity sportsman){
        var sportsmanStatsProcedure = entityManager.createNamedStoredProcedureQuery(DATE_TIME_STATS_PROCEDURE)
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .setParameter(1, sportsman.getStatistics().getStatisticsId());
        sportsmanStatsProcedure.execute();
        return sportsmanStatsProcedure.getResultList();
    }

    public SportsmanEntity setTrainer(Long trainerPesel, SportsmanEntity sportsman){
        var parameterName = "pesel";
        return sportsman.withTrainer(entityManager.createNamedQuery(PERSONAL_TRAINER_QUERY, TrainerEntity.class)
                .setParameter(parameterName, trainerPesel).getSingleResult());
    }

    public boolean trainCardio(SportsmanEntity sportsman){
        var statistics = sportsman.getStatistics();
        var trainCardioProcedure = entityManager.createStoredProcedureQuery(TRAIN_CARDIO_PROCEDURE)
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, statistics.getStatisticsId())
                .setParameter(2, statistics.getCardioTrainingEntity().getCardioId());
        return trainCardioProcedure.execute();
    }

    public boolean trainPower(SportsmanEntity sportsman){
        var statistics = sportsman.getStatistics();
        var trainPowerProcedure = entityManager.createStoredProcedureQuery(TRAIN_POWER_PROCEDURE)
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, statistics.getStatisticsId())
                .setParameter(2, statistics.getPowerTrainingEntity().getPowerId());
        return trainPowerProcedure.execute();
    }
}