package com.rms;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.controller.UserController;
import com.rms.model.UserDetails;
import com.rms.service.UserService;

@WebMvcTest(UserController.class)
@ActiveProfiles("Test")
public class UserServiceTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	UserService userService;

	UserDetails userDetails1 = new UserDetails(1, " sssss", "Shivani", 7247, "Chhatarpur");
	UserDetails userDetails2 = new UserDetails(2, " mmmmm", "Sakshi", 7049, "UP");
	UserDetails userDetails3 = new UserDetails(3, " aaaa", "Priya", 1234, "MP");

	@Test
	public void getAllDetailsTest() throws Exception {
		List<UserDetails> users = new ArrayList<UserDetails>();
		users.add(userDetails1);
		users.add(userDetails2);
		users.add(userDetails3);
		Mockito.when(userService.getAllDetails()).thenReturn(users);

		mockMvc.perform(MockMvcRequestBuilders.get("/user").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].userId", is(1))).andExpect(jsonPath("$[0].customerName", is("Shivani")))
				.andExpect(jsonPath("$[1].userId", is(2))).andExpect(jsonPath("$[1].address", is("UP")));

	}
}
