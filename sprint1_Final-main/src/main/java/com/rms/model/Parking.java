package com.rms.model;

import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Parking 
{
	@Id
	private int id;
	private long areaId;
	private String name;
	private long contactno;
	private float chargesPerSlot;
	

}
