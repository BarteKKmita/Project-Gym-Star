package com.learning.gym.star.sportsmanbuilder.sportsmandb.service;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDB;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDTO;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanSerializer;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.database.SportsmanDatabaseOperations;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.database.SportsmanRepository;
import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsDB;
import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.TrainerSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service("sportsman service rest template")
@Transactional
public class SportsmanService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final SportsmanSerializer serializer = new SportsmanSerializer();
    private static final TrainerSerializer trainerSerializer = new TrainerSerializer();
    private SportsmanRepository repository;
    private SportsmanDatabaseOperations databaseOperations;

    public SportsmanService(SportsmanRepository repository, SportsmanDatabaseOperations databaseOperations){
        this.repository = repository;
        this.databaseOperations = databaseOperations;
    }

    public void addSportsman(SportsmanDTO sportsman){
        logger.debug("Adding new sportsman with data {}.", sportsman);
        if (repository.findById(sportsman.getSportsmanPesel()).isPresent()) {
            throw new EntityExistsException();
        } else {
            repository.saveAndFlush(serializer.buildSportsmanFromSportsmanDTO(sportsman));
        }
    }

    public List<TrainingDateStatisticsDB> getSportsmanStatistics(Long sportsmanPesel){
        logger.debug("Getting sportsman statistics by sportsman pesel {}", sportsmanPesel);
        SportsmanDB sportsman = repository.findById(sportsmanPesel).orElseThrow();
        return databaseOperations.getTrainingDateTimeStatistics(sportsman);
    }

    public void chooseTrainer(Long sportsmanPesel, Long trainerPesel){
        logger.debug("Choosing sportsman's trainer with sportsman pesel {} and trainer pesel {}.", sportsmanPesel, trainerPesel);
        SportsmanDB sportsman = repository.findById(sportsmanPesel).orElseThrow();
        repository.saveAndFlush(databaseOperations.setTrainer(trainerPesel, sportsman));
    }

    public TrainerDTO getMyTrainerData(Long sportsmanPesel){
        SportsmanDB sportsman = repository.findById(sportsmanPesel).orElseThrow();
        return trainerSerializer.getTrainerDTOFromTrainer(sportsman.getTrainer());
    }

    public boolean trainCardio(Long sportsmanPesel){
        SportsmanDB sportsmanDB = repository.findById(sportsmanPesel).orElseThrow();
        return databaseOperations.trainCardio(sportsmanDB);
    }
}
