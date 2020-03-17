package com.ja.dev.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.dev.pma.entities.Project;
import com.ja.dev.pma.repos.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;

	@Override
	public Project save(Project project) {
		Project sProject = projectRepository.save(project);
		return sProject;
	}

	@Override
	public List<Project> findAllProjects() {
		List<Project> projects = projectRepository.findAll();
		return projects;
	}

}
