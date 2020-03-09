package com.ja.dev.pma.dto;

public interface EmployeeProject {
	
	// Property name should begin with get
	// Spring Data maps dto to NativeQuery properties
	public String getFirstName();
	public String getLastName();
	public int getProjectCount();

}
