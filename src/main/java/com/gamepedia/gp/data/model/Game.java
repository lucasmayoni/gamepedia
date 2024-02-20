package com.gamepedia.gp.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gamepedia.gp.utils.Platform;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="game", schema = "gp")
public class Game {
    public Game(){

    }
    public Game(Long gameId, String gameTitle, String gameDescription, Platform platform, Date releaseDate){
        this.id = gameId;
        this.gameTitle = gameTitle;
        this.gameDescription = gameDescription;
        this.platform = platform;
        this.releaseDate = releaseDate;
    }
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String gameTitle;
    @Column(name="description")
    private String gameDescription;
    @Enumerated(EnumType.STRING)
    @Column(name="platform")
    private Platform platform;

    @Column(name="release_date")
    private Date releaseDate;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("game")
    private List<GameReview> reviews;


    public Long getGameId() {
        return id;
    }

    public void setGameId(Long gameId) {
        this.id = gameId;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<GameReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<GameReview> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Game { id:"+id+", title:"+gameTitle+", description:"+gameDescription+
                ", platform:"+platform+", releaseDate:"+releaseDate;
    }
}

