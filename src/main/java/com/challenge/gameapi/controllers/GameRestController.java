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
        log.info(getClass() + " render Home Page");
        return new ModelAndView("redirect:/swagger-ui.html#/");
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<SuccessMessage> uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {
        log.info(getClass() + " file upload started");

        gameService.saveAll(CsvUtils.read(Game.class, file.getInputStream()));

        log.info(getClass() + " file upload done");
        HttpStatus httpStatus = HttpStatus.CREATED;
        SuccessMessage successMessage = new SuccessMessage(200,"File successfully uploaded",httpStatus);
        return new ResponseEntity<>(successMessage,httpStatus);


    }

    @GetMapping(value="/games")
    public List<Game> getAllGames(@RequestParam(value = "title",required = false )String title){
        log.info(getClass() + " fetching all games");
        if(title == null ){
            List<Game> games = gameService.findAll();
            return games;
        }
        else {
            log.info(getClass() + " fetched all games successfully");
            return gameService.findAllByTitle(title);
        }
    }

    @GetMapping(value = "/games/{id}")
    public Game getSingleGame(@PathVariable("id")String id){
        log.info(getClass() + " fetching game with id: "+ id);
        Game game = gameService.getSingleGame(id);
        log.info(getClass() + " fetched game with id: " + id);
        return game;
    }

    @PostMapping(value = "/games")
    public ResponseEntity<Object> createGame(@RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "score", required = false) String score,
                                     @RequestParam(value = "platform", required = false) String platform,
                                     @RequestParam(value = "genre", required = false) String genre,
                                     @RequestParam(value = "editorsChoice", required = false) String editorsChoice) {

        log.info(getClass() + " creating new post ");
        gameService.saveGame(title, platform, score, genre, editorsChoice);
        log.info(getClass() + " new post created");
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
        log.info(getClass() + " PUT /games/"+id);

        gameService.updateGame(id, title, platform, score, genre, editorsChoice);

        log.info(getClass() + " successfully updated game with id: " + id);
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
        log.info(getClass() +  " PATCh /games/"+id);

        gameService.updateGamePatch(id, title, platform, score, genre, editorsChoice);

        log.info(getClass() + " successfully updated game with id : " + id);
        HttpStatus httpStatus = HttpStatus.OK;
        SuccessMessage successMessage = new SuccessMessage(200,"Post updated successfully",httpStatus);
        return new ResponseEntity<>(successMessage,httpStatus);
    }

    @DeleteMapping(value = "/games/{id}")
    public ResponseEntity<Object> deleteGame(@PathVariable("id") String id){
        log.info(getClass() + " deleting game with id :" + id);

        gameService.deleteGame(id);

        log.info(getClass() + " successfully deleted post with id : " + id);

        HttpStatus httpStatus = HttpStatus.OK;
        SuccessMessage successMessage = new SuccessMessage(200,"Post deleted successfully",httpStatus);
        return new ResponseEntity<>(successMessage,httpStatus);

    }

}
