package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Game;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.GameRepository;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	GameRepository gameRepo;
	
	@Autowired
	GameService gameSer;
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	
	public User getUserById(int id) throws ResourceNotFoundException {
		
		Optional<User> found = userRepo.findById(id);
 		
		if(!found.isPresent()) {
			throw new ResourceNotFoundException("User with id = " + id + " could not be found.");
		}
		
		return found.get();
		
	}
	
	public User updateUser(User user) throws ResourceNotFoundException {
		
		if(!userRepo.existsById(user.getId())) {
			throw new ResourceNotFoundException("User with id = " + user.getId() 
			+ " could not be found and cannot be updated.");
		}
		
		User updated = userRepo.save(user);
		
		return updated;
	}
	
	public User purchase(int userid, int gameid) throws ResourceNotFoundException {
		
		User user = getUserById(userid);
		Game game = gameSer.getGameById(gameid);
		
		if(!userRepo.existsById(userid)) {
			throw new ResourceNotFoundException("User with id = " + user.getId() 
			+ " could not be found and cannot be added to purchased.");
		}
		
		else if(!gameRepo.existsById(gameid)) {
			throw new ResourceNotFoundException("Game with id = " + game.getId() 
			+ " could not be found and cannot be added to purchased.");
		}
		
		// When purchased subtract from quantity
		int qty = game.getQuantity();
		if(qty > 0) {
			game.setQuantity(qty-1);
			user.purchase(game);
			gameRepo.save(game);
			return userRepo.save(user);
		}
		
		return user; 
		
		
		

//		else {
//			return "Sorry sold out of " + game.getTitle();
//		}
//		
//		
//		return "User " + user.getUsername() + " purchased " + game.getTitle() + " for " + game.getPrice();
	}
	
	
	
	
	
	
}
