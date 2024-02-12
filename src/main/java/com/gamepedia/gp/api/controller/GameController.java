package com.gamepedia.gp.api.controller;

import com.gamepedia.gp.data.model.Game;
import com.gamepedia.gp.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping
    public List<Game> getGames(){
        return this.gameService.getAllGames();
    }
    @GetMapping("/{id}")
    public Game getGame(Long id){
        return this.gameService.getGameById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game addNewGame(Game game) {
        return this.gameService.addGame(game);
    }

    @PutMapping("/{id}")
    public Game updateGame(Game game) {
        return this.gameService.updateGame(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteGame(Game game) {
        this.gameService.deleteGame(game);
    }
}
