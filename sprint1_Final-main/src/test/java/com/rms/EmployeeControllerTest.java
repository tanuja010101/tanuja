package com.rms;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.controller.EmployeeController;
import com.rms.model.Employee;
import com.rms.repository.EmployeeRepository;
import com.rms.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
@ActiveProfiles("Test")
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	EmployeeRepository employeeRepository;
	
	Employee employee1 = new Employee("Sanket", "Sanket@123", "Evening","Pune");
	Employee employee2 = new Employee("Rohit", "Rohit@123", "Evening","Mumbai");
	Employee employee3 = new Employee("Rahul", "Rahul@123", "Evening","Punjab");
	
	@Test
	public void getAllEmployeeTest() throws Exception {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee2);
		employees.add(employee3);
		Mockito.when(employeeRepository.findAll()).thenReturn(employees);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees").contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$[0].name", is("Sanket"))).andExpect(jsonPath("$[1].contact", is("Rohit@123")))
		.andExpect(jsonPath("$[2].address", is("Punjab")));

}

}
