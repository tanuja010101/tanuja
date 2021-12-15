package com.rms;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.controller.FeedbackController;
import com.rms.model.FeedBack;
import com.rms.service.FeedbackService;

@WebMvcTest(FeedbackController.class)
public class FeedbackControllerTest {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;

	@MockBean
	FeedbackService feedbackService;

	FeedBack RECORD_1 = new FeedBack(1, "tanuja", 6);
	FeedBack RECORD_2 = new FeedBack(2, "megha", 4);
	FeedBack RECORD_3 = new FeedBack(3, "rita", 6);

	@Test
	public void getAllRecords_success() throws Exception {

		List<FeedBack> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
		Mockito.when(feedbackService.getFeedBack()).thenReturn(records);

		mockMvc.perform(MockMvcRequestBuilders.get("/feedback").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[2].customerName", is("rita")));

	}

}
