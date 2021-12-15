package com.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rms.model.Parking;
import com.rms.repository.ParkingRepository;
@Service
public class ParkingService 
{
	@Autowired
	private ParkingRepository prepository;
	
	public Parking addParking(Parking parking)
	{
		return prepository.save(parking);

	}
	public List<Parking> addParkings(List<Parking> parking)
	{
		return prepository.saveAll(parking);
	}
	public List<Parking> getParking()
	{
		return prepository.findAll();
	}
	public Parking getParkingById(int id)
	{
		return prepository.findById(id).orElse(null);

	}
	public Parking updateParking(Parking parking)
	{
		Parking existingone=prepository.findById(parking.getId())
				.orElseThrow(()->new ResourceNotFoundException("user not found with the id:"+parking.getId()));
		existingone.setAreaId(parking.getAreaId());
		existingone.setName(parking.getName());
		existingone.setContactno(parking.getContactno());
		existingone.setChargesPerSlot(parking.getChargesPerSlot());
		return prepository.save(existingone);
	}
	public ResponseEntity<Parking> deleteParking(int CustomerId)
    {
    	Parking existingcustomer=this.prepository.findById(CustomerId)
    			.orElseThrow(()-> new ResourceNotFoundException("user not found with the id:"+CustomerId));
    	this.prepository.delete(existingcustomer);
    	return ResponseEntity.ok().build();
    }

}
