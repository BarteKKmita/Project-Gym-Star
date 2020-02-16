package com.learning.gym.star.sportsmanbuilder.sportsmandb.database;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanEntity;
import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsEntity;
import com.learning.gym.star.trainer.trainerdb.TrainerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class SportsmanDatabaseOperations {
    private static final String personalTrainerQuery = "setPersonalTrainer";
    private static final String dateTimeStatsProcedure = "sportsmanStats";
    private static final String trainCardioProcedure = "trainCardio";
    private static final String trainPowerProcedure = "trainPower";
    private EntityManager entityManager;

    public SportsmanDatabaseOperations(@Autowired EntityManager entityManager){
        this.entityManager = entityManager;
    }


    public List<TrainingDateStatisticsEntity> getTrainingDateTimeStatistics(SportsmanEntity sportsman){
        int firstQueryParameter = 1;
        StoredProcedureQuery sportsmanStats = entityManager.createNamedStoredProcedureQuery(dateTimeStatsProcedure)
                .registerStoredProcedureParameter(firstQueryParameter, String.class, ParameterMode.IN)
                .setParameter(firstQueryParameter, sportsman.getStatistics().getStatisticsId());
        sportsmanStats.execute();
        return sportsmanStats.getResultList();
    }

    public SportsmanEntity setTrainer(Long trainerPesel, SportsmanEntity sportsman){
        String parameterName = "pesel";
        return sportsman.withTrainer(entityManager.createNamedQuery(personalTrainerQuery, TrainerEntity.class)
                .setParameter(parameterName, trainerPesel).getSingleResult());
    }

    public boolean trainCardio(SportsmanEntity sportsman){
        int firstQueryParameter = 1;
        int secondQueryParameter = 2;
        StatisticsEntity statistics = sportsman.getStatistics();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(trainCardioProcedure)
                .registerStoredProcedureParameter(firstQueryParameter, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(secondQueryParameter, String.class, ParameterMode.IN)
                .setParameter(firstQueryParameter, statistics.getStatisticsId())
                .setParameter(secondQueryParameter, statistics.getCardioTraining().getCardioId());
        return storedProcedureQuery.execute();
    }

    //to discuss
    public boolean trainPower(SportsmanEntity sportsman){
        int firstQueryParameter = 1;
        int secondQueryParameter = 2;
        StatisticsEntity statistics = sportsman.getStatistics();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(trainPowerProcedure)
                .registerStoredProcedureParameter(firstQueryParameter, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(secondQueryParameter, String.class, ParameterMode.IN)
                .setParameter(firstQueryParameter, statistics.getStatisticsId())
                .setParameter(secondQueryParameter, statistics.getPowerTraining().getPowerId());
        return storedProcedureQuery.execute();
    }

}
