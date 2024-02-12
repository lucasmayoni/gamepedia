package com.gamepedia.gp.data.repository;

import com.gamepedia.gp.data.model.GameReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameReviewRepository extends JpaRepository<GameReview, Long> {

}
