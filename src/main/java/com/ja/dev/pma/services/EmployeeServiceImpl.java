package com.ja.dev.pma.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.dev.pma.dto.EmployeeProject;
import com.ja.dev.pma.entities.Employee;
import com.ja.dev.pma.repos.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<EmployeeProject> getEmployeeProjects() {
		return employeeRepository.employeeProjects();
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}
	
	

}
