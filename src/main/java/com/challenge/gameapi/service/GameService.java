package com.challenge.gameapi.service;

import com.challenge.gameapi.model.Game;

import java.util.List;

public interface GameService {
    void saveAll(List<Game> games);
    List<Game> findAll();
    List<Game> findAllByTitle(String title);
}
