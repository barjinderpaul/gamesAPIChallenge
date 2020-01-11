package com.challenge.gameapi.service;

import com.challenge.gameapi.model.Game;
import com.challenge.gameapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImplementation  implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Override
    public void saveAll(List<Game> games) {
        gameRepository.saveAll(games);
    }

    @Override
    public List<Game> findAll() {
       return gameRepository.findAll();
    }

    @Override
    public List<Game> findAllByTitle(String title) {
        return gameRepository.findAllByTitle(title);
    }


}
