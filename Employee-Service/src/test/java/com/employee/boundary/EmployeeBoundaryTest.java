package com.employee.boundary;

import static com.employee.utils.TestUtils.boundaryTestFile;
import static com.employee.utils.TestUtils.currentTest;
import static com.employee.utils.TestUtils.testReport;
import static com.employee.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.employee.entity.Employee;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class EmployeeBoundaryTest {

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
	public void testEmployeeNameNotNull() throws IOException {
		Employee emp = new Employee();
		emp.setName(null);
		emp.setEmail("test@example.com");
		emp.setPosition("Manager");

		Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testEmployeeNameMinLength() throws IOException {
		Employee emp = new Employee();
		emp.setName("ab");
		emp.setEmail("test@example.com");
		emp.setPosition("Manager");

		Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testEmployeeNameMaxLength() throws IOException {
		Employee emp = new Employee();
		emp.setName("a".repeat(256));
		emp.setEmail("test@example.com");
		emp.setPosition("Manager");

		Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testEmployeeEmailNotNull() throws IOException {
		Employee emp = new Employee();
		emp.setName("John Doe");
		emp.setEmail(null);
		emp.setPosition("Manager");

		Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testEmployeeEmailInvalidFormat() throws IOException {
		Employee emp = new Employee();
		emp.setName("John Doe");
		emp.setEmail("invalid-email");
		emp.setPosition("Manager");

		Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testEmployeePositionNotNull() throws IOException {
		Employee emp = new Employee();
		emp.setName("John Doe");
		emp.setEmail("john@example.com");
		emp.setPosition(null);

		Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testEmployeePositionMinLength() throws IOException {
		Employee emp = new Employee();
		emp.setName("John Doe");
		emp.setEmail("john@example.com");
		emp.setPosition("A");

		Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testEmployeePositionMaxLength() throws IOException {
		Employee emp = new Employee();
		emp.setName("John Doe");
		emp.setEmail("john@example.com");
		emp.setPosition("A".repeat(101));

		Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}
}
