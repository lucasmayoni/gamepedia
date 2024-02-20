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
    public Game getGame(@PathVariable("id")Long id){
        return this.gameService.getGameById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game addNewGame(@RequestBody Game game) {
        return this.gameService.addGame(game);
    }

    @PutMapping("/{id}")
    public Game updateGame(@PathVariable("id") Long id, @RequestBody Game game) throws Exception {
        if(!id.equals(game.getGameId())){
            throw new Exception("path variable must match incoming request id");
        }
        return this.gameService.updateGame(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteGame(@PathVariable("id") Long id, Game game) {
        this.gameService.deleteGame(game);
    }
}
