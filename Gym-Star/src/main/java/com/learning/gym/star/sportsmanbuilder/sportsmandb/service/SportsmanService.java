package com.learning.gym.star.sportsmanbuilder.sportsmandb.service;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDB;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDTO;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanSerializer;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.database.SportsmanRepository;
import com.learning.gym.star.statistics.statisticsdb.StatisticsDB;
import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsDB;
import com.learning.gym.star.trainer.trainerdb.TrainerDB;
import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.TrainerSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service("sportsman service rest template")
public class SportsmanService {
    private static final String dateTimeStatsQuery = "SELECT * FROM SportsMenTrainingTimeStatistics WHERE sportsmanstats_id=";
    private EntityManager entityManager;
    private SportsmanRepository repository;
    private SportsmanSerializer serializer;

    public SportsmanService(@Autowired EntityManager entityManager, SportsmanRepository repository, SportsmanSerializer serializer){
        this.entityManager = entityManager;
        this.repository = repository;
        this.serializer = serializer;
    }

    public void addSportsman(SportsmanDTO sportsman){
        if (repository.findById(sportsman.getSportsmanPesel()).isPresent()) {
            throw new EntityExistsException();
        } else {
            repository.saveAndFlush(serializer.buildSportsmanFromSportsmanDTO(sportsman));
        }
    }

    @Transactional
    public List getSportsmanStatistics(Long sportsmanPesel){
        SportsmanDB sportsman = repository.findById(sportsmanPesel).orElseThrow();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("getsportsmanstats")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, Integer.valueOf(sportsman.getStatistics().getStatisticsId()));
        return storedProcedureQuery.getResultList();
    }

    @Transactional
    public List<TrainingDateStatisticsDB> getSportsmanStatistics2(Long sportsmanPesel){
        SportsmanDB sportsman = repository.findById(sportsmanPesel).orElseThrow();
        StoredProcedureQuery getsportsmanstats1 = entityManager.createNamedStoredProcedureQuery("getsportsmanstats");
        List<TrainingDateStatisticsDB> getsportsmanstats = getsportsmanstats1.getResultList();
        return getsportsmanstats;
    }

    @Transactional
    public void chooseTrainer(Long sportsmanPesel, Long trainerPesel){
        SportsmanDB sportsman = repository.findById(sportsmanPesel).orElseThrow();
        repository.saveAndFlush(sportsman.withTrainer(entityManager.createQuery("FROM TrainerDB t WHERE t.pesel=:pes", TrainerDB.class)
                .setParameter("pes", trainerPesel).getSingleResult()));
    }

    public TrainerDTO getMyTrainerData(Long sportsmanPesel){
        SportsmanDB sportsmanDB = repository.findById(sportsmanPesel).orElseThrow();
        TrainerSerializer serializer = new TrainerSerializer();
        return serializer.getTrainerDTOFromTrainer(sportsmanDB.getTrainer());
    }

    @Transactional
    public boolean trainCardio(Long sportsmanPesel){
        SportsmanDB sportsmanDB = repository.findById(sportsmanPesel).orElseThrow();
        StatisticsDB statistics = sportsmanDB.getStatistics();
        Integer statsId = Integer.valueOf(statistics.getStatisticsId());
        Integer cardioId = Integer.valueOf(statistics.getCardioTrainingDB().getCardioId());
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("trainCardio")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, statsId)
                .setParameter(2, cardioId);
        return storedProcedureQuery.execute();
    }
}
