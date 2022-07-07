package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Game;
import com.cognixia.jump.repository.GameRepository;

@Service
public class GameService {
	
	@Autowired
	GameRepository repo;
	
	
	public List<Game> getAllGames() {
		
		return repo.findAll();
	}
	

	
	
	public Game getGameById(int id) throws ResourceNotFoundException {
		
		Optional<Game> found = repo.findById(id);
 		
		if(!found.isPresent()) {
			throw new ResourceNotFoundException("Game with id = " + id + " could not be found.");
		}
		
		return found.get();
		
		
	}
	
	
	
	public Game addGame(Game game) {
		
		game.setId(null);
		
		Game saved = repo.save(game);
		
		return saved;
		
	}
	
	public Game updateGame(Game game) throws ResourceNotFoundException {
		
		if(!repo.existsById(game.getId())) {
			throw new ResourceNotFoundException("Game with id = " + game.getId() 
			+ " could not be found and cannot be updated.");
		}
		
		
		Game updated = repo.save(game);
		
		return updated;
		
	}
	
	public Game deleteGame(int id) throws ResourceNotFoundException {
		
		Game toDelete = getGameById(id);
		
		repo.deleteById(id);
		
		return toDelete;
		
	}
	
	
	
}
