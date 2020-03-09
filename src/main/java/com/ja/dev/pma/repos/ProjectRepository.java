package com.ja.dev.pma.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ja.dev.pma.dto.ChartData;
import com.ja.dev.pma.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	@Query(nativeQuery=true, value="SELECT stage as label, count(*) as value FROM project GROUP BY stage;")
	public List<ChartData> projectStages();

}
