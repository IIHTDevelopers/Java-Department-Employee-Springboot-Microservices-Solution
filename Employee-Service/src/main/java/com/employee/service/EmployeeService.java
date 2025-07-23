package com.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.exception.NotFoundException;
import com.employee.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Optional<Employee> getEmployee(Long id) {
		return employeeRepository.findById(id);
	}

	public Employee updateEmployee(Long id, Employee updatedEmployee) {
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Employee with id " + id + " not found"));

		emp.setName(updatedEmployee.getName());
		emp.setEmail(updatedEmployee.getEmail());
		emp.setPosition(updatedEmployee.getPosition());

		return employeeRepository.save(emp);
	}
}
