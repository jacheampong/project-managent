package com.ja.dev.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ja.dev.pma.dto.ChartData;
import com.ja.dev.pma.repos.ProjectRepository;

@Controller
public class HomeController {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@GetMapping("/")
	public String home(Model model) throws JsonProcessingException {
		
		List<ChartData> projectData = projectRepository.projectStages();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		model.addAttribute("projectStatusCnt", jsonString);
		
		return "home";
	}

}
