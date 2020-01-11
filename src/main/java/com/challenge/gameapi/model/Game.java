package com.challenge.gameapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "games")
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", nullable = false)
    public Long id;

    @Column(name = "title")
    public String title;

    public Double score;
    public String genre;
    public String platform;

    @Column(name = "editors_choice")
    public char editors_choice;

}
