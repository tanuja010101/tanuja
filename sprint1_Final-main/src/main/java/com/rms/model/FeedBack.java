package com.rms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message="can not be blank")
	private String customerName;
	private double rating;

}
