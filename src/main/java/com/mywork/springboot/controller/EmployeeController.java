package com.mywork.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mywork.springboot.exception.ResourceNotFoundException;
import com.mywork.springboot.model.Employee;
import com.mywork.springboot.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins ="*")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	//get all employee rest api
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		
		List<Employee> list = employeeRepository.findAll();
		
		return list;
		
		
	}
	
	//create a rest employee service
	
	@PostMapping("employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		
		return employeeRepository.save(employee);
	}
	
	//get employee details
	
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		
		Employee emp = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No record found "+id));
		
		
		
		return ResponseEntity.ok(emp);
		
		
	}
	
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee, @PathVariable Long id) {
		
		Employee existingEmp = employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(" Record not found "+id));
		
		existingEmp.setFirstName(employee.getFirstName());
		existingEmp.setLastName(employee.getLastName());
		existingEmp.setEmailId(employee.getEmailId());
		
		return ResponseEntity.ok(employeeRepository.save(existingEmp));
	}

}
