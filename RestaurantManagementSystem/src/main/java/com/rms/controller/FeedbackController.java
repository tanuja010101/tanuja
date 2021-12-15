package com.rms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rms.model.FeedBack;
import com.rms.service.FeedbackService;

@RestController
public class FeedbackController {
	@Autowired
	private FeedbackService fbservice;

	@GetMapping("/feedbackWelcome")
	public String giveFeedback() {
		return ("Welcome to the Feedback portal,We will really appreciate if you give your valuable Feedback to us.");
	}

	@PostMapping("/feedback")
	public FeedBack addFeedBack(@Valid @RequestBody FeedBack feedback) {
		return fbservice.addFeedback(feedback);

	}

	@PostMapping("/feedbacks")
	public List<FeedBack> addFeedBacks(@Valid @RequestBody List<FeedBack> feedback) {
		return fbservice.addFeedBacks(feedback);

	}

	@GetMapping("/feedback")
	public List<FeedBack> getAllFeedBacks() {
		return fbservice.getFeedBack();
	}

	@GetMapping("/feedback/{customerId}")
	public FeedBack findFeedbackById(@Valid @PathVariable int customerId) {
		return fbservice.getFeedBackById(customerId);
	}

	@PutMapping("/updatefb")
	public FeedBack updateFeedback(@Valid @RequestBody FeedBack feedback) {
		return fbservice.updatefeedback(feedback);
	}

	@DeleteMapping("/deletefb/{customerId}")
	public ResponseEntity<FeedBack> deleteFeedBack(@Valid @PathVariable int customerId) {
		return fbservice.deleteFeedBack(customerId);
	}

}
