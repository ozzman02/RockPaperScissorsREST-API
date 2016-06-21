package com.restapi.core;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface ScoreRepository.
 */
public interface ScoreRepository extends JpaRepository<Score, Long> {
	
	/**
	 * Find score by player name.
	 *
	 * @param playerName the player name
	 * @return the score
	 */
	Score findScoreByPlayerName(String playerName);
	
}
