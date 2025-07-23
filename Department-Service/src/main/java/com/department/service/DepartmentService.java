package com.department.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.department.dto.DepartmentResponse;
import com.department.dto.EmployeeDTO;
import com.department.entity.Department;
import com.department.exception.NotFoundException;
import com.department.repo.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private RestTemplate restTemplate;

	public Department save(Department dept) {
		return departmentRepository.save(dept);
	}

	public Optional<Department> get(Long id) {
		return departmentRepository.findById(id);
	}

	public Department update(Long id, Department dept) {
		if (!departmentRepository.existsById(id)) {
			throw new NotFoundException("Department with id " + id + " not found");
		}
		dept.setId(id);
		return departmentRepository.save(dept);
	}

	public DepartmentResponse getDepartmentWithEmployee(Long id) {
		Department dept = departmentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Department with id " + id + " not found"));

		EmployeeDTO employee;
		try {
			employee = restTemplate.getForObject("http://EMPLOYEE-SERVICE/employees/" + dept.getEmployeeId(),
					EmployeeDTO.class);
		} catch (Exception e) {
			throw new NotFoundException("Employee with id " + dept.getEmployeeId() + " not found");
		}

		DepartmentResponse response = new DepartmentResponse();
		response.setDepartmentName(dept.getName());
		response.setEmployee(employee);
		return response;
	}
}
