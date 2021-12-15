package com.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rms.model.FeedBack;
import com.rms.repository.FeedbackRepository;

@Service
public class FeedbackService {
	@Autowired
	private FeedbackRepository fbrepository;

	public FeedBack addFeedback(FeedBack feedback) {
		return fbrepository.save(feedback);

	}

	public List<FeedBack> addFeedBacks(List<FeedBack> feedback) {
		return fbrepository.saveAll(feedback);
	}

	public List<FeedBack> getFeedBack() {
		return fbrepository.findAll();
	}

	public FeedBack getFeedBackById(int id) {
		return fbrepository.findById(id).orElse(null);

	}

	public FeedBack updatefeedback(FeedBack feedback) {
		FeedBack existingone = fbrepository.findById(feedback.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("user not found with the id:" + feedback.getCustomerId()));
		existingone.setCustomerName(feedback.getCustomerName());
		existingone.setRating(feedback.getRating());
		return fbrepository.save(existingone);
	}

	public ResponseEntity<FeedBack> deleteFeedBack(int CustomerId) {
		FeedBack existingcustomer = this.fbrepository.findById(CustomerId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with the id:" + CustomerId));
		this.fbrepository.delete(existingcustomer);
		return ResponseEntity.ok().build();
	}
}
