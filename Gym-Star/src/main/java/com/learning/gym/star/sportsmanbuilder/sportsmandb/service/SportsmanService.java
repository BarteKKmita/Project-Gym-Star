package com.learning.gym.star.sportsmanbuilder.sportsmandb.service;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDTO;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanEntity;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanSerializer;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.database.SportsmanDatabaseOperations;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.database.SportsmanRepository;
import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsEntity;
import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.TrainerSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Service("SportsmanService")
public class SportsmanService {
    private static final SportsmanSerializer SPORTSMAN_SERIALIZER = new SportsmanSerializer();
    private static final TrainerSerializer TRAINER_SERIALIZER = new TrainerSerializer();
    private static final Logger LOGGER = LoggerFactory.getLogger(SportsmanService.class);
    private final SportsmanRepository repository;
    private final SportsmanDatabaseOperations databaseOperations;

    public SportsmanService(SportsmanRepository repository, SportsmanDatabaseOperations databaseOperations){
        this.repository = repository;
        this.databaseOperations = databaseOperations;
    }

    public void addSportsman(SportsmanDTO sportsman){
        LOGGER.info("Adding new sportsman with data {}.", sportsman);
        if (repository.findById(sportsman.getSportsmanPesel()).isPresent()) {
            LOGGER.info("Sportsman with specified pesel already exists.");
            throw new EntityExistsException("Sportsman with specified pesel: " + sportsman.getSportsmanPesel() + " already exists.");
        } else {
            repository.saveAndFlush(SPORTSMAN_SERIALIZER.buildSportsmanFromSportsmanDTO(sportsman));
        }
    }

    public List<TrainingDateStatisticsEntity> getSportsmanStatistics(Long sportsmanPesel){
        LOGGER.info("Getting sportsman statistics by sportsman pesel {}", sportsmanPesel);
        SportsmanEntity sportsman = repository.findById(sportsmanPesel)
                .orElseThrow(() -> handleNonExistingSportsman(sportsmanPesel));
        return databaseOperations.getTrainingDateTimeStatistics(sportsman);
    }

    public void chooseTrainer(Long sportsmanPesel, Long trainerPesel){
        LOGGER.info("Choosing sportsman's trainer with sportsman pesel {} and trainer pesel {}.", sportsmanPesel, trainerPesel);
        SportsmanEntity sportsman = repository.findById(sportsmanPesel)
                .orElseThrow(() -> handleNonExistingSportsman(sportsmanPesel));
        repository.saveAndFlush(databaseOperations.setTrainer(trainerPesel, sportsman));
    }

    public TrainerDTO getMyTrainerData(Long sportsmanPesel){
        LOGGER.info("Getting personal trainer data. Sportsman pesel: {}", sportsmanPesel);
        SportsmanEntity sportsman = repository.findById(sportsmanPesel)
                .orElseThrow(() -> handleNonExistingSportsman(sportsmanPesel));
        return TRAINER_SERIALIZER.getTrainerDTOFromTrainer(sportsman.getTrainer());
    }

    public void trainCardio(Long sportsmanPesel){
        LOGGER.info("Doing cardio training. Sportsman pesel: {}", sportsmanPesel);
        SportsmanEntity sportsman = repository.findById(sportsmanPesel)
                .orElseThrow(() -> handleNonExistingSportsman(sportsmanPesel));
        databaseOperations.trainCardio(sportsman);
    }

    public void trainPower(Long sportsmanPesel){
        LOGGER.info("Doing power training. Sportsman pesel: {}", sportsmanPesel);
        SportsmanEntity sportsman = repository.findById(sportsmanPesel)
                .orElseThrow(() -> handleNonExistingSportsman(sportsmanPesel));
        databaseOperations.trainPower(sportsman);
    }

    private NoSuchElementException handleNonExistingSportsman(Long sportsmanPesel){
        LOGGER.info("Sportsman with given pesel not exists.");
        throw new NoSuchElementException("Sportsman with given pesel not exists." + sportsmanPesel);
    }
}