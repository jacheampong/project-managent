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
import org.springframework.web.bind.annotation.RequestParam;

import com.ja.dev.pma.entities.Employee;
import com.ja.dev.pma.entities.Project;
import com.ja.dev.pma.repos.EmployeeRepository;
import com.ja.dev.pma.repos.ProjectRepository;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/new")
	public String displayNewProject(Model model) {
		
		// bind empty object to the form - need for empty constructor
		Project aProject = new Project();	
		List<Employee> employees = employeeRepository.findAll();
		
		// bind model attribute to html 
		model.addAttribute("projectAttr", aProject); 
		model.addAttribute("allEmployees", employees); 
		
		LOGGER.info("=> displayNewProject() new project = {}", aProject);
		return "projects/new-project";
	}
	
	@PostMapping(value = "/save")
	public String save(Project project, @RequestParam List<Long> employees, Model model) {
		LOGGER.info("=> In save() obj: {}", project);
		
		Project sProject = projectRepository.save(project);
	
		model.addAttribute("sProject", sProject);
		
		// use a redirect to prevent duplicate submission
		return "redirect:/project/new";
	}
	
	@GetMapping(value = {"/", "/list"})
	public String getAllProjects(Model model) {
		LOGGER.info("=> In getAllProjects()");
		
		List<Project> projects = projectRepository.findAll();
		model.addAttribute("projects", projects);
		LOGGER.info("=> In getAllProjects()  project count: {}", projects.size());
		
		return "projects/projects";
	}
	
	

}
