package com.ja.dev.pma.services;

import java.util.List;

import com.ja.dev.pma.entities.Project;

public interface ProjectService {
	
	Project save(Project project);
	
	List<Project> findAllProjects();

}
