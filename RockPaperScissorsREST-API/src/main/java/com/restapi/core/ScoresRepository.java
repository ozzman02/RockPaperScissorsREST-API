package com.restapi.core;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoresRepository extends JpaRepository<Score, Long> {
	
}
