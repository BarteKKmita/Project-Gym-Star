package com.learning.gym.star.sportsmanbuilder.sportsmandb.service;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDTO;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanSerializer;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.database.SportsmanRepository;
import org.springframework.stereotype.Service;

@Service("sportsman service")
public class SportsmanService {

    private SportsmanRepository repository;
    private SportsmanSerializer serializer;

    public SportsmanService(SportsmanRepository repository, SportsmanSerializer serializer){
        this.repository = repository;
        this.serializer = serializer;
    }

    public void addSportsman(SportsmanDTO sportsman){
        repository.saveAndFlush(serializer.getSportsmanDBFromSportsmanDTO(sportsman));
    }
}
