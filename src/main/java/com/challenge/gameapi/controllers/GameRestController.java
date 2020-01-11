package com.challenge.gameapi.controllers;


import com.challenge.gameapi.model.Game;
import com.challenge.gameapi.model.User;
import com.challenge.gameapi.repository.GameRepository;
import com.challenge.gameapi.service.GameService;
import com.challenge.gameapi.util.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class GameRestController {

    @Autowired
    GameService gameService;

    @PostMapping(value = "/upload", consumes = "text/csv")
    public void uploadSimple(@RequestBody InputStream body) throws IOException {
        gameService.saveAll(CsvUtils.read(Game.class, body));
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public void uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {
        gameService.saveAll(CsvUtils.read(Game.class, file.getInputStream()));
    }

    @GetMapping(value="/games")
    public List<Game> getAllGames(@RequestParam(value = "title",required = false )String title){
        if(title == null ){
            List<Game> games = gameService.findAll();
            return games;
        }
        else {
            return gameService.findAllByTitle(title);
        }
    }

    @PostMapping(value = "/games")
    public void createGame(@RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "score", required = false) String score,
                           @RequestParam(value = "platform", required = false) String platform,
                           @RequestParam(value = "genre", required = false) String genre,
                           @RequestParam(value = "editorsChoice", required = false) String editorsChoice) {

        gameService.saveGame(title, platform, score, genre, editorsChoice);
    }

    @PutMapping(value = "/games/{id}")
    public void updateGame(@PathVariable("id") String id,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "score", required = false) String score,
                           @RequestParam(value = "platform", required = false) String platform,
                           @RequestParam(value = "genre", required = false) String genre,
                           @RequestParam(value = "editorsChoice", required = false) String editorsChoice){
        gameService.updateGame(id, title, platform, score, genre, editorsChoice);
    }

    @PatchMapping(value = "/games/{id}")
    public void updateGamePatch(@PathVariable("id") String id,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "score", required = false) String score,
                           @RequestParam(value = "platform", required = false) String platform,
                           @RequestParam(value = "genre", required = false) String genre,
                           @RequestParam(value = "editorsChoice", required = false) String editorsChoice){
        gameService.updateGamePatch(id, title, platform, score, genre, editorsChoice);
    }

    @DeleteMapping(value = "/games/{id}")
    public void deleteGame(@PathVariable("id") String id){
        gameService.deleteGame(id);
    }

}
