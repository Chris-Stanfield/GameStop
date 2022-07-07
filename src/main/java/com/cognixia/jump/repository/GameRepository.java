package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Game;


@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{

	@Query("SELECT g FROM Game g WHERE g.platform = ?1")
	List<Game> gamesWithPlatform(String platform);
}
