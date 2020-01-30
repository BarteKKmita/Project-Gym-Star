package com.learning.gym.star.sportsmanbuilder.sportsmandb.service;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDB;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDTO;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanSerializer;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.database.SportsmanRepository;
import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    public List<Object> getSportsmanStatistics(Long sportsmanPesel){
        SportsmanDB sportsman = repository.findById(sportsmanPesel).orElseThrow();
        Query nativeQuery = entityManager.createNativeQuery(dateTimeStatsQuery + sportsman.getStatistics().getStatisticsId() + ";", TrainingDateStatisticsDB.class);
        return nativeQuery.getResultList();
    }
}
