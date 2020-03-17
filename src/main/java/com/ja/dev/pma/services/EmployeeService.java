package com.ja.dev.pma.services;

import java.util.List;

import com.ja.dev.pma.dto.EmployeeProject;
import com.ja.dev.pma.entities.Employee;

public interface EmployeeService {
	
	Employee save(Employee employee);
	
	List<EmployeeProject> getEmployeeProjects();
	
	List<Employee> findAllEmployees();

}
