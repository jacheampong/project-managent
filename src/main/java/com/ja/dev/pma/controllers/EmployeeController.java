package com.ja.dev.pma.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.dev.pma.dto.EmployeeProject;
import com.ja.dev.pma.entities.Employee;
import com.ja.dev.pma.repos.EmployeeRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		LOGGER.info("=> In displayEmployeeForm()");
		
		Employee aEmployee = new Employee();
		model.addAttribute("employeeAttr", aEmployee);
		
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String saveEmployee(Employee employee, Model model) {
		LOGGER.info("=> In saveEmployee()");
		Employee sEmployee = employeeRepository.save(employee);

		LOGGER.info("saved emp: " + sEmployee);
		
		// use a redirect to prevent duplicate submission
		return "redirect:/employee/new";
	}
	
	@GetMapping(value = {"/", "/list"})
	public String getAllProjects(Model model) {
		LOGGER.info("=> In getAllProjects()");
		
//		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeProject> employeesProjectCount = employeeRepository.employeeProjects();
		model.addAttribute("employeesProjectCount", employeesProjectCount);
		LOGGER.info("=> In getAllProjects()  employee count: {}", employeesProjectCount.size());
		
		return "employees/employees";
	}

}
