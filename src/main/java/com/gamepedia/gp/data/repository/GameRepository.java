package com.gamepedia.gp.data.repository;

import com.gamepedia.gp.data.model.Game;
import com.gamepedia.gp.utils.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByPlatform(Platform platform);
}
