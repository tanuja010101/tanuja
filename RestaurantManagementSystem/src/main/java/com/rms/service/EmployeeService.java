package com.rms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.model.Employee;
import com.rms.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> getEmployeeById(long employeeId) {
		return employeeRepository.findById(employeeId);
	}
	
	public void deleteEmployee(long employee) {
		employeeRepository.deleteById(employee);		
	}
	
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
}

