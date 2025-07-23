package com.department.boundary;

import static com.department.utils.TestUtils.boundaryTestFile;
import static com.department.utils.TestUtils.currentTest;
import static com.department.utils.TestUtils.testReport;
import static com.department.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.department.entity.Department;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class DepartmentBoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testDepartmentNameNotBlank() throws IOException {
		Department dept = new Department();
		dept.setName("  "); // Blank string
		dept.setEmployeeId(101L);
		Set<ConstraintViolation<Department>> violations = validator.validate(dept);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testDepartmentNameNotNull() throws IOException {
		Department dept = new Department();
		dept.setName(null);
		dept.setEmployeeId(101L);
		Set<ConstraintViolation<Department>> violations = validator.validate(dept);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testDepartmentNameMinLength() throws IOException {
		Department dept = new Department();
		dept.setName("ab"); // too short, assuming min = 3
		dept.setEmployeeId(101L);
		Set<ConstraintViolation<Department>> violations = validator.validate(dept);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testDepartmentNameMaxLength() throws IOException {
		Department dept = new Department();
		dept.setName("a".repeat(256)); // too long, assuming max = 255
		dept.setEmployeeId(101L);
		Set<ConstraintViolation<Department>> violations = validator.validate(dept);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testEmployeeIdNotNull() throws IOException {
		Department dept = new Department();
		dept.setName("Cardiology");
		dept.setEmployeeId(null); // should not be null
		Set<ConstraintViolation<Department>> violations = validator.validate(dept);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}
}
