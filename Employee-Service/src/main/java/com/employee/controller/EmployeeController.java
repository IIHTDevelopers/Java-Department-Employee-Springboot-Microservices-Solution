package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> create(@RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.createEmployee(employee));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> get(@PathVariable Long id) {
		return employeeService.getEmployee(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
	}
}