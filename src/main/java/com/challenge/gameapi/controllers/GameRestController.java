package com.challenge.gameapi.controllers;


import com.challenge.gameapi.message.SuccessMessage;
import com.challenge.gameapi.model.Game;
import com.challenge.gameapi.model.User;
import com.challenge.gameapi.repository.GameRepository;
import com.challenge.gameapi.service.GameService;
import com.challenge.gameapi.util.CsvUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class GameRestController {

    @Autowired
    GameService gameService;

    @GetMapping(value = "/")
    public ModelAndView renderSwaggerPage(){
        return new ModelAndView("redirect:/swagger-ui.html#/");
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<SuccessMessage> uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {
        gameService.saveAll(CsvUtils.read(Game.class, file.getInputStream()));

        HttpStatus httpStatus = HttpStatus.CREATED;
        SuccessMessage successMessage = new SuccessMessage(200,"File successfully uploaded",httpStatus);
        return new ResponseEntity<>(successMessage,httpStatus);


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

    @GetMapping(value = "/games/{id}")
    public Game getSingleGame(@PathVariable("id")String id){
        return gameService.getSingleGame(id);
    }

    @PostMapping(value = "/games")
    public ResponseEntity<Object> createGame(@RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "score", required = false) String score,
                                     @RequestParam(value = "platform", required = false) String platform,
                                     @RequestParam(value = "genre", required = false) String genre,
                                     @RequestParam(value = "editorsChoice", required = false) String editorsChoice) {

        gameService.saveGame(title, platform, score, genre, editorsChoice);

        HttpStatus httpStatus = HttpStatus.CREATED;
        SuccessMessage successMessage = new SuccessMessage(200,"Post created successfully",httpStatus);
        return new ResponseEntity<>(successMessage,httpStatus);
    }

    @PutMapping(value = "/games/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable("id") String id,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "score", required = false) String score,
                           @RequestParam(value = "platform", required = false) String platform,
                           @RequestParam(value = "genre", required = false) String genre,
                           @RequestParam(value = "editorsChoice", required = false) String editorsChoice){
        gameService.updateGame(id, title, platform, score, genre, editorsChoice);

        HttpStatus httpStatus = HttpStatus.OK;
        SuccessMessage successMessage = new SuccessMessage(200,"Post updated successfully",httpStatus);
        return new ResponseEntity<>(successMessage,httpStatus);
    }

    @PatchMapping(value = "/games/{id}")
    public ResponseEntity<Object> updateGamePatch(@PathVariable("id") String id,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "score", required = false) String score,
                           @RequestParam(value = "platform", required = false) String platform,
                           @RequestParam(value = "genre", required = false) String genre,
                           @RequestParam(value = "editorsChoice", required = false) String editorsChoice){
        gameService.updateGamePatch(id, title, platform, score, genre, editorsChoice);

        HttpStatus httpStatus = HttpStatus.OK;
        SuccessMessage successMessage = new SuccessMessage(200,"Post updated successfully",httpStatus);
        return new ResponseEntity<>(successMessage,httpStatus);
    }

    @DeleteMapping(value = "/games/{id}")
    public ResponseEntity<Object> deleteGame(@PathVariable("id") String id){
        gameService.deleteGame(id);

        HttpStatus httpStatus = HttpStatus.OK;
        SuccessMessage successMessage = new SuccessMessage(200,"Post deleted successfully",httpStatus);
        return new ResponseEntity<>(successMessage,httpStatus);

    }

}
