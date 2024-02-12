package com.gamepedia.gp.service;

import com.gamepedia.gp.data.model.Game;
import com.gamepedia.gp.data.repository.GameRepository;
import com.gamepedia.gp.service.error.NotFoundException;
import com.gamepedia.gp.utils.Platform;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GamePediaApiServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    GameRepository gameRepository;

    @Test
    void testFindByPlatform() {
        gameService.findByPlatform(Platform.XBOX);
        verify(gameRepository).findByPlatform(Platform.XBOX);
    }


    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10})
    void testGetAllGames(int gameCount){
        Mockito.doReturn(getMockGames(gameCount)).when(gameRepository).findAll();
        List<Game> games = gameService.getAllGames();
        assertEquals(gameCount, games.size());
    }

    @Test
    void testAddGame(){
        Game gameMock = newGameEntity();
        when(gameRepository.save(any(Game.class))).thenReturn(gameMock);
        Game game = new Game(gameMock.getGameId(), gameMock.getGameTitle(), gameMock.getGameDescription(), gameMock.getPlatform(),
                gameMock.getReleaseDate());
        game = this.gameService.addGame(game);
        assertEquals("WatchDogs 2", game.getGameTitle());
    }

    @Test
    void testGetGameById_NotFoundException() {
        Game gameEntity = newGameEntity();
        gameEntity.setGameId(-1L);
        assertThrows(NotFoundException.class, ()->gameService.getGameById(gameEntity.getGameId()));
    }

    @Test
    void testGetGameById(){
        Game expectedGame = newGameEntity();
        when(gameRepository.findById(expectedGame.getGameId())).thenReturn(Optional.of(expectedGame));
        Game actualGame = this.gameService.getGameById(expectedGame.getGameId());
        assertEquals(actualGame, expectedGame);
    }

    @Test
    void testUpdateGame() {
        Game expectedGame = newGameEntity();
        when(gameRepository.findById((expectedGame.getGameId()))).thenReturn(Optional.of((expectedGame)));
        when(gameRepository.save(any(Game.class))).thenReturn(expectedGame);

        Game updatedGame = this.gameService.updateGame(expectedGame);

        assertEquals("WatchDogs 2", updatedGame.getGameTitle());
        assertEquals(expectedGame.getGameDescription(), updatedGame.getGameDescription());
    }

    @Test
    void testDeleteGame() {
        Game gameToDelete = newGameEntity();
        doNothing().when(gameRepository).delete(gameToDelete);
        gameService.deleteGame(gameToDelete);
    }


    private Game newGameEntity() {
        return new Game(2L,"WatchDogs 2",  "Grate Game Description",
                Platform.XBOX, Date.valueOf("2020-10-10"));
    }
    private List<Game> getMockGames(int gameCount) {
        List<Game> games = new ArrayList<>(gameCount);
        for (int idx =0; idx< gameCount; idx++) {
            games.add(new Game(2L,"WatchDogs 2",  "Grate Game Description",
                    Platform.XBOX, Date.valueOf("2020-10-10")));
        }
        return games;
    }
}
