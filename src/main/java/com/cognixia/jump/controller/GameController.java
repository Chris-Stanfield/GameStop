package com.cognixia.jump.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Game;
import com.cognixia.jump.repository.GameRepository;
import com.cognixia.jump.service.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api")
public class GameController {
	
	@Autowired
	GameService service;
	
	@Autowired
	GameRepository repo;
	
	
	@Operation(summary = "Get all the games in the database")
	@GetMapping("/game")
	public List<Game> getGames() {
		return service.getAllGames();
	}
	
	
	@Operation(summary = "Get all the games by platform",
			   description = "returns games by a specified platform. for example can return all games for PS5.")
	@GetMapping("/game/{platform}")
	public List<Game> getGamesByPlatform(@PathVariable String platform){
		
		return repo.gamesWithPlatform(platform);
	}
	
	
	@Operation(summary = "adds games to the database")
	@PostMapping("/add/game")
	public ResponseEntity<Game> addGame(@RequestBody Game game) {
		
		Game added = service.addGame(game);
		
		return new ResponseEntity<>(added, HttpStatus.CREATED);
	}
	
	
	
	@Operation(summary = "Update any game in the database",
			   description = "Able to update any part of the game like price, quantity, etc.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Game has been found", 
						 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Game.class) ) ),
			@ApiResponse(responseCode = "404", description = "Game was not found", 
			 			 content = @Content ) 
		}
	)
	@PutMapping("/update/game")
	public ResponseEntity<Game> updateGame(@RequestBody Game game) throws ResourceNotFoundException {
		
		Game updated = service.updateGame(game);
		
		return new ResponseEntity<>(updated, HttpStatus.OK);	
		
	}
	
	
	@DeleteMapping("/delete/game/{id}")
	public ResponseEntity<Game> removeGame(@PathVariable int id) throws ResourceNotFoundException {
		
		Game deleted = service.deleteGame(id);
		
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	
}
