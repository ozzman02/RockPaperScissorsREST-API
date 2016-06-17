package com.restapi.core;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
	
	Score findScoreByPlayerName(String playerName);
	
}
