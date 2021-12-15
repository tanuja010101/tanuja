package com.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rms.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking,Integer> 
{
	

}
