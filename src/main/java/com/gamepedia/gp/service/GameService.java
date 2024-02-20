package com.gamepedia.gp.service;

import com.gamepedia.gp.data.model.Game;
import com.gamepedia.gp.data.model.GameReview;
import com.gamepedia.gp.data.repository.GameRepository;
import com.gamepedia.gp.data.repository.GameReviewRepository;
import com.gamepedia.gp.service.error.NotFoundException;
import com.gamepedia.gp.utils.Platform;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameReviewRepository gameReviewRepository;

    public GameService(GameRepository gameRepository, GameReviewRepository gameReviewRepository) {
        this.gameRepository = gameRepository;
        this.gameReviewRepository = gameReviewRepository;
    }
    public List<Game> findByPlatform(Platform platform) {
        return this.gameRepository.findByPlatform(platform);
    }

    public List<Game> getAllGames() {
        return this.gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        if (id <= 0) {
            throw new NotFoundException("Id is not valid");
        }
        Optional<Game> game = this.gameRepository.findById(id);
        return game.orElse(null);

    }
    public Game addGame(Game game){
        // Validate if game exists. Maybe by title and platform.
        return this.gameRepository.save(game);
    }

    public Game updateGame(Game game) {
        return this.gameRepository.save(game);
    }

    public void deleteGame(Game game) {
        this.gameRepository.delete(game);
    }


    // Game Review Stuff
    public Game addGameReview(Game game, GameReview gameReview) {
        Optional<Game> optionalGame = this.gameRepository.findById(game.getGameId());
        if (optionalGame.isEmpty()) {
            throw new NotFoundException("Game ID is not present");
        }

        Game existingGame = optionalGame.get();

        // Get the existing reviews or create a new list if null
        List<GameReview> reviews = existingGame.getReviews();

        reviews = (reviews != null) ? new ArrayList<>(reviews) : new ArrayList<>();
        reviews.add(gameReview);

        // Update the reviews for the existing game
        existingGame.setReviews(reviews);

        // Save the updated game entity
        return this.gameRepository.save(existingGame);
    }

    public List<GameReview> findByGame(Long gameId) {
        return this.gameReviewRepository.findByGame_Id(gameId);
    }

    private Game translateToModel(Game game) {
        return new Game(game.getGameId(), game.getGameTitle(), game.getGameDescription(), game.getPlatform(), game.getReleaseDate());
    }
}
