package com.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.department.dto.DepartmentResponse;
import com.department.entity.Department;
import com.department.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@PostMapping
	public Department create(@RequestBody Department dept) {
		return departmentService.save(dept);
	}

	@GetMapping("/{id}")
	public DepartmentResponse get(@PathVariable Long id) {
		return departmentService.getDepartmentWithEmployee(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department dept) {
		Department updated = departmentService.update(id, dept);
		return ResponseEntity.ok(updated);
	}
}