package com.challenge.gameapi.service;

import com.challenge.gameapi.model.Game;

import java.util.List;

public interface GameService {
    void saveAll(List<Game> games);
    List<Game> findAll();
    List<Game> findAllByTitle(String title);
    void saveGame(String title, String platform, String score, String genre, Character editorsChoice);
    void updateGame(String id, String title,  String platform, String score, String genre, Character editorsChoice);

    void updateGamePatch(String id, String title, String platform, String score, String genre, Character editorsChoice);

    void deleteGame(String id);
}

