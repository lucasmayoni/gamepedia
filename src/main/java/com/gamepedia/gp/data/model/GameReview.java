package com.gamepedia.gp.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="game_review")
public class GameReview {

    public GameReview() {

    }

    public GameReview(Long id, Game game, String reviewer, Date reviewDate, String review) {
        this.gameReviewId = id;
        this.game = game;
        this.reviewer = reviewer;
        this.reviewDate = reviewDate;
        this.review = review;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameReviewId;
    @ManyToOne
    @JoinColumn(name="game_id")
    @JsonIgnoreProperties("reviews")
    private Game game;

    @Column(name="reviewer")
    private String reviewer;

    @Column(name="review_date")
    private Date reviewDate;

    @Column(name="review")
    private String review;
    public Long getGameReviewId() {
        return gameReviewId;
    }

    public void setGameReviewId(Long gameReviewId) {
        this.gameReviewId = gameReviewId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
