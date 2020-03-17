package com.ja.dev.pma.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

import com.ja.dev.pma.ProjectManagementApplication;
import com.ja.dev.pma.entities.Project;

/**
 * 
 * @author jacheampong
 *
 */
@ContextConfiguration(classes=ProjectManagementApplication.class)
@SpringBootTest
@DataJpaTest
@SqlGroup(value = { @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts= {"classpath:schema.sql", "classpath:data.sql"}),
 @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts= "classpath:drop.sql")})
public class ProjectRepositoryIntegrationTest {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Test
	public void ifNewProjectSaved_thenSuccess() {
		Project project = new Project("New Test Project", "COMPLETED", "This is a test project");
		projectRepository.save(project);
		
		assertEquals(5, projectRepository.findAll().size());
		
	}

}
