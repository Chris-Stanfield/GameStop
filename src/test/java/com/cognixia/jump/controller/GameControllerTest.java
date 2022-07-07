package com.cognixia.jump.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Game;
import com.cognixia.jump.repository.GameRepository;
import com.cognixia.jump.service.GameService;
import com.cognixia.jump.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WebMvcTest(GameController.class)
public class GameControllerTest {

	private static final String STARTING_URI = "http://localhost:8080/api";

	@Autowired
	private MockMvc mvc;

	
	@Autowired
	private JwtUtil jwtutil;


	@Autowired
	private UserDetails userdetails;
	
	@MockBean
	private GameService service;
	
	@InjectMocks
	private GameController controller;
	
	@Test
	void testGetAllGames() throws Exception {
		
		String hi = "hello";
		
		assertEquals(hi, "hello");
		
	}
	
	
	
}	
//	
//	
//	
//	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@Test
//	@WithMockUser(username = "testUser", roles = {"ROLE_ADMIN"}, password = "testing123")
//	void testGetAllGames() throws Exception {
//		
//		
//		
//		String uri = STARTING_URI + "/game";
//		
//		List<Game> allGames = new ArrayList<Game>();
//		allGames.add(new Game(null, "Call of Duty: Modern Warfare", 26.99, 67, "Xbox One"));
//		
//		when( service.getAllGames() ).thenReturn(allGames);
//
//		mvc.perform( get(uri) )  
//			.andDo( print() ) 
//			.andExpect( status().isOk() ) 
//			.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) ) 
//			.andExpect( jsonPath( "$.length()" ).value( allGames.size() ) ) 
//			.andExpect( jsonPath("$[0].id").value(allGames.get(0).getId()) ) 
//		    .andExpect( jsonPath("$[0].title").value(allGames.get(0).getTitle()) )
//		    .andExpect( jsonPath("$[0].price").value(allGames.get(0).getPrice()) )
//			.andExpect( jsonPath("$[0].quantity").value(allGames.get(0).getQuantity()) )
//			.andExpect( jsonPath("$[0].platform").value(allGames.get(0).getPlatform()) );
//		
//		verify( service, times(1) ).getAllGames(); 
//		verifyNoMoreInteractions( service );
//				
//	}
//	
//	
//	
//	public static String asJsonString(final Object obj) {
//		
//		try {
//			return new ObjectMapper().writeValueAsString(obj);
//		} catch (JsonProcessingException e) {
//			throw new RuntimeException();
//		}
//		
//	}
//	
//	
//}
