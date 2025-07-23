package com.department.functional;

import static com.department.utils.MasterData.asJsonString;
import static com.department.utils.MasterData.getDepartmentResponse;
import static com.department.utils.TestUtils.businessTestFile;
import static com.department.utils.TestUtils.currentTest;
import static com.department.utils.TestUtils.testReport;
import static com.department.utils.TestUtils.yakshaAssert;
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

import com.department.controller.DepartmentController;
import com.department.dto.DepartmentResponse;
import com.department.entity.Department;
import com.department.service.DepartmentService;

@WebMvcTest(DepartmentController.class)
@AutoConfigureMockMvc
public class DepartmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService departmentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateDepartment() throws Exception {
		Department department = new Department();
		department.setId(1L);
		department.setName("Cardiology");
		department.setEmployeeId(101L);

		when(departmentService.save(any(Department.class))).thenReturn(department);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/departments")
				.content(asJsonString(department)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(department)) ? "true" : "false"),
				businessTestFile);
	}

	@Test
	public void testGetDepartmentWithEmployee() throws Exception {
		DepartmentResponse response = getDepartmentResponse();
		Long deptId = 1L;

		when(departmentService.getDepartmentWithEmployee(deptId)).thenReturn(response);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/departments/" + deptId)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(response)) ? "true" : "false"),
				businessTestFile);
	}

	@Test
	public void testUpdateDepartment() throws Exception {
		Department updatedDept = new Department();
		updatedDept.setId(1L);
		updatedDept.setName("Updated Dept");
		updatedDept.setEmployeeId(102L);

		when(departmentService.update(eq(1L), any(Department.class))).thenReturn(updatedDept);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/departments/1")
				.content(asJsonString(updatedDept)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(updatedDept)) ? "true" : "false"),
				businessTestFile);
	}
}
