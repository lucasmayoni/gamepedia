package com.gamepedia.gp.api.controller;

import com.gamepedia.gp.data.model.GameReview;
import com.gamepedia.gp.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class GameReviewController {

    private final GameService gameService;

    public GameReviewController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games/{id}")
    public List<GameReview> findByGameId(@PathVariable("id")Long id){
        return this.gameService.findByGame(id);
    }

}
