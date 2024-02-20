package com.gamepedia.gp.data.repository;

import com.gamepedia.gp.data.model.GameReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameReviewRepository extends JpaRepository<GameReview, Long> {
    List<GameReview> findByGame_Id(Long gameId);
}
