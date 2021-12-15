package com.rms.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rms.model.Parking;
import com.rms.service.ParkingService;

@RestController
public class ParkingController 
{
	@Autowired
	private ParkingService pservice;
	
	@PostMapping("/parking")
	public Parking addParking(@RequestBody Parking parking)
	{
		return pservice.addParking(parking);

	}
	@PostMapping("/parkings")
	public List<Parking> addParkings( @RequestBody List<Parking> parking)
	{
		return pservice.addParkings(parking);

	}
	@GetMapping("/parking")
	public List<Parking> getAllParkings()
	{
		return pservice.getParking();
	}
	@GetMapping("/parking/{customerId}")
	public Parking findParkingById( @PathVariable int customerId)
	{
		return pservice.getParkingById(customerId);
	}
	@PutMapping("/updateparking")
	public Parking updateParking( @RequestBody Parking parking)
	{
		return pservice.updateParking(parking);
	}
	@DeleteMapping("/deletep/{customerId}")
	public ResponseEntity<Parking> deleteParking( @PathVariable int customerId)
	{
		return pservice.deleteParking(customerId);
	}
	

}
