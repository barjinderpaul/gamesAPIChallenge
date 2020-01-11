package com.challenge.gameapi.service;

import com.challenge.gameapi.exceptions.InvalidArgumentException;
import com.challenge.gameapi.model.Game;
import com.challenge.gameapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImplementation  implements GameService {

    @Autowired
    GameRepository gameRepository;

    private void isValidId(String id) {
        Long gameId;
        try {
            gameId  = Long.parseLong(id);
        }
        catch(NumberFormatException e) {
            throw new InvalidArgumentException("Please enter a valid id");
        }

    }
    private Boolean isValidTitle(String title) {
        if(title == null || title.trim().equals("")) {
            throw new InvalidArgumentException("Please enter a valid title");
        }
        return true;
    }

    private Boolean isValidPlatform(String platform){
        if (platform == null || platform.trim().equals("")){
            throw new InvalidArgumentException("Please enter a valid platform");
        }
        return true;
    }

    private Boolean isValidGenre(String genre){
        if (genre == null || genre.trim().equals("")){
            throw new InvalidArgumentException("Please enter a valid genre");
        }
        return true;
    }

    private Boolean isValidScore(String score){
        if (score == null || score.trim().equals("")){
            throw new InvalidArgumentException("Please enter a valid score");
        }
        try {
            Double scoreDouble  = Double.parseDouble(score);
        }
        catch (NumberFormatException e) {
            throw new InvalidArgumentException("Please enter a valid score");
        }
        return true;
    }

    private Boolean isValidEditorsChoice(String editorsChoice){
        if (editorsChoice == null || editorsChoice.equals("") || (!editorsChoice.toLowerCase().equals("y") && !editorsChoice.toLowerCase().equals("n")) ){
            throw new InvalidArgumentException("Please enter a valid editors choice");
        }
        return true;
    }

    private void isValidGame(String title, String platform, String score, String genre, String editorsChoice) {
        isValidTitle(title);
        isValidPlatform(platform);
        isValidScore(score);
        isValidGenre(genre);
        isValidEditorsChoice(editorsChoice);
    }

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
        if(!isValidTitle(title)) { return new ArrayList<>(); }
        return gameRepository.findAllByTitle(title);
    }

    @Override
    public void saveGame(String title, String platform, String score, String genre, String editorsChoice) {

        isValidGame(title,platform,score,genre,editorsChoice);

        Game game = new Game();
        game.setTitle(title);
        game.setPlatform(platform);
        game.setScore(Double.parseDouble(score));
        game.setGenre(genre);
        game.setEditors_choice(editorsChoice.toUpperCase().charAt(0));

        gameRepository.save(game);

    }

    @Override
    public void updateGame(String id, String title, String platform, String score, String genre, String editorsChoice) {
        isValidGame(title,platform,score,genre,editorsChoice);
        isValidId(id);
        Long gameId = Long.parseLong(id);

        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if(!gameOptional.isPresent()){
            throw new InvalidArgumentException("Game with id : " + id + " not found");
        }
        Game game = gameOptional.get();

        game.setTitle(title);
        game.setPlatform(platform);
        game.setGenre(genre);
        game.setScore(Double.parseDouble(score));
        game.setEditors_choice(editorsChoice.toUpperCase().charAt(0));

        gameRepository.save(game);
    }

    @Override
    public void updateGamePatch(String id, String title, String platform, String score, String genre, String editorsChoice) {
        isValidId(id);
        Long gameId = Long.parseLong(id);

        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if(!gameOptional.isPresent()){
            throw new InvalidArgumentException("Game with id : " + id + " not found");
        }

        Game game = gameOptional.get();

        if(isValidTitle(title)){
            game.setTitle(title);
        }
        if(isValidPlatform(platform)){
            game.setPlatform(platform);
        }
        if(isValidGenre(genre)){
            game.setGenre(genre);
        }
        if(isValidScore(score)){
            game.setScore(Double.parseDouble(score));
        }
        if(isValidEditorsChoice(editorsChoice)){
            game.setEditors_choice(editorsChoice.toUpperCase().charAt(0));
        }

        gameRepository.save(game);
    }

    @Override
    public void deleteGame(String id) {
        isValidId(id);
        Long gameId = Long.parseLong(id);
        Optional<Game> gameOptional  = gameRepository.findById(gameId);
        if(!gameOptional.isPresent()){
            throw new InvalidArgumentException("Game with id : " + id + " not found");
        }
        gameRepository.deleteById(gameId);
    }


}
