package com.fbd.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class FeedBack
{

	@Id

	private int customerId;


	private String customerName;
	//@NotEmpty
	//@Size(min=1,max=10,message="feedaback ratings are upto 10")
	private double rating;

}
