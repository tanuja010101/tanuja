package com.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rms.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
