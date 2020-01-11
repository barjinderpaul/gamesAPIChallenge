package com.challenge.gameapi.service;

import com.challenge.gameapi.model.Game;
import com.challenge.gameapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void saveGame(String title, String platform, String score, String genre, Character editorsChoice) {
        Game game = new Game();
        game.setTitle(title);
        game.setPlatform(platform);
        game.setScore(Double.parseDouble(score));
        game.setGenre(genre);
        game.setEditors_choice(editorsChoice);

        gameRepository.save(game);

    }

    @Override
    public void updateGame(String id, String title, String platform, String score, String genre, Character editorsChoice) {
        Long gameId = Long.parseLong(id);
        Optional<Game> gameOptional = gameRepository.findById(gameId);

        Game game = gameOptional.get();
        game.setTitle(title);
        game.setPlatform(platform);
        game.setGenre(genre);
        game.setScore(Double.parseDouble(score));
        game.setEditors_choice(editorsChoice);

        gameRepository.save(game);
    }



}
