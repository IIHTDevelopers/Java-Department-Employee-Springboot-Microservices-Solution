package com.department.dto;

public class DepartmentResponse {

	private String departmentName;
	private EmployeeDTO employee;

	public DepartmentResponse() {
		super();
	}

	public DepartmentResponse(String departmentName, EmployeeDTO employee) {
		super();
		this.departmentName = departmentName;
		this.employee = employee;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
}