package com.department.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.department.dto.DepartmentResponse;
import com.department.dto.EmployeeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MasterData {

	// Existing methods...

	public static DepartmentResponse getDepartmentResponse() {
		DepartmentResponse response = new DepartmentResponse();
		response.setDepartmentName("Cardiology");
		response.setEmployee(getEmployeeDTO());
		return response;
	}

	public static List<DepartmentResponse> getDepartmentResponseList() {
		List<DepartmentResponse> list = new ArrayList<>();

		DepartmentResponse response = new DepartmentResponse();
		response.setDepartmentName("Cardiology");
		response.setEmployee(getEmployeeDTO());

		list.add(response);
		return list;
	}

	public static Page<DepartmentResponse> getDepartmentResponsePage(int page, int size) {
		List<DepartmentResponse> responseList = getDepartmentResponseList();
		PageRequest pageRequest = PageRequest.of(page, size);
		return new PageImpl<>(responseList, pageRequest, responseList.size());
	}

	public static EmployeeDTO getEmployeeDTO() {
		EmployeeDTO employee = new EmployeeDTO();
		employee.setId(101L);
		employee.setName("Alice Johnson");
		employee.setPosition("Senior Nurse");
		return employee;
	}

	public static List<EmployeeDTO> getEmployeeDTOList() {
		List<EmployeeDTO> list = new ArrayList<>();

		EmployeeDTO employee = new EmployeeDTO();
		employee.setId(101L);
		employee.setName("Alice Johnson");
		employee.setPosition("Senior Nurse");

		list.add(employee);
		return list;
	}

	public static Page<EmployeeDTO> getEmployeeDTOPage(int page, int size) {
		List<EmployeeDTO> list = getEmployeeDTOList();
		PageRequest pageRequest = PageRequest.of(page, size);
		return new PageImpl<>(list, pageRequest, list.size());
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append("A");
		}
		return s.toString();
	}
}
