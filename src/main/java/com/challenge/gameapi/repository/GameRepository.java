package com.challenge.gameapi.repository;

import com.challenge.gameapi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
//    List<Game> findAllBy(String name);
}
