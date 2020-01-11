package com.challenge.gameapi.repository;

import com.challenge.gameapi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    List<Game> findAllByTitle(String name);
    Optional<Game> findById(Long gameId);
    void deleteById(Long id);
}
