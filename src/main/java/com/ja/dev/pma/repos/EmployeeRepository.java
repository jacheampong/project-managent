package com.ja.dev.pma.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ja.dev.pma.dto.EmployeeProject;
import com.ja.dev.pma.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query(nativeQuery=true, value="SELECT first_name as firstName, last_name as lastName, count(pe.EMPLOYEE_ID) as projectCount " + 
			"from EMPLOYEE e left join PROJECT_EMPLOYEE  pe ON pe.employee_id = e.employee_id " + 
			"GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();

}
