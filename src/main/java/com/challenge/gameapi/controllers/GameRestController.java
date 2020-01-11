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
    public List<Game> getAllGames(){
        List<Game> games = gameService.findAll();
        return games;
    }

}
