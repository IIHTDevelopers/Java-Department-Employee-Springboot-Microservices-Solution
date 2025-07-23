package com.employee.functional;

import static com.employee.utils.MasterData.asJsonString;
import static com.employee.utils.TestUtils.businessTestFile;
import static com.employee.utils.TestUtils.currentTest;
import static com.employee.utils.TestUtils.testReport;
import static com.employee.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.employee.controller.EmployeeController;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("John Doe");
		employee.setEmail("john@example.com");
		employee.setPosition("Developer");

		when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/employees").content(asJsonString(employee))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().equals(asJsonString(employee)) ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testGetEmployeeById() throws Exception {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("John Doe");
		employee.setEmail("john@example.com");
		employee.setPosition("Developer");

		when(employeeService.getEmployee(1L)).thenReturn(java.util.Optional.of(employee));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().equals(asJsonString(employee)) ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		Employee updatedEmployee = new Employee();
		updatedEmployee.setId(1L);
		updatedEmployee.setName("Jane Doe");
		updatedEmployee.setEmail("jane@example.com");
		updatedEmployee.setPosition("Lead");

		when(employeeService.updateEmployee(eq(1L), any(Employee.class))).thenReturn(updatedEmployee);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/employees/1")
				.content(asJsonString(updatedEmployee)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().equals(asJsonString(updatedEmployee)) ? "true" : "false",
				businessTestFile);
	}
}
