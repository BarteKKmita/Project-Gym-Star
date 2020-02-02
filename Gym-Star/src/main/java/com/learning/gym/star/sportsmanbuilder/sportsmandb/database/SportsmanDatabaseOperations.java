package com.learning.gym.star.sportsmanbuilder.sportsmandb.database;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDB;
import com.learning.gym.star.statistics.statisticsdb.StatisticsDB;
import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsDB;
import com.learning.gym.star.trainer.trainerdb.TrainerDB;
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


    public List<TrainingDateStatisticsDB> getTrainingDateTimeStatistics(SportsmanDB sportsman){
        int firstQueryParameter = 1;
        StoredProcedureQuery sportsmanStats = entityManager.createNamedStoredProcedureQuery(dateTimeStatsProcedure)
                .registerStoredProcedureParameter(firstQueryParameter, String.class, ParameterMode.IN)
                .setParameter(firstQueryParameter, sportsman.getStatistics().getStatisticsId());
        sportsmanStats.execute();
        return sportsmanStats.getResultList();
    }

    public SportsmanDB setTrainer(Long trainerPesel, SportsmanDB sportsman){
        String parameterName = "pesel";
        return sportsman.withTrainer(entityManager.createNamedQuery(personalTrainerQuery, TrainerDB.class)
                .setParameter(parameterName, trainerPesel).getSingleResult());
    }

    public boolean trainCardio(SportsmanDB sportsmanDB){
        int firstQueryParameter = 1;
        int secondQueryParameter = 2;
        StatisticsDB statistics = sportsmanDB.getStatistics();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(trainCardioProcedure)
                .registerStoredProcedureParameter(firstQueryParameter, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(secondQueryParameter, String.class, ParameterMode.IN)
                .setParameter(firstQueryParameter, statistics.getStatisticsId())
                .setParameter(secondQueryParameter, statistics.getCardioTrainingDB().getCardioId());
        return storedProcedureQuery.execute();
    }

    //to discuss
    public boolean trainPower(SportsmanDB sportsmanDB){
        int firstQueryParameter = 1;
        int secondQueryParameter = 2;
        StatisticsDB statistics = sportsmanDB.getStatistics();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(trainPowerProcedure)
                .registerStoredProcedureParameter(firstQueryParameter, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(secondQueryParameter, String.class, ParameterMode.IN)
                .setParameter(firstQueryParameter, statistics.getStatisticsId())
                .setParameter(secondQueryParameter, statistics.getPowerTrainingDB().getPowerId());
        return storedProcedureQuery.execute();
    }

}
