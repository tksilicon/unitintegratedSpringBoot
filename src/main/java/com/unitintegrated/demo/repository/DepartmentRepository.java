package com.unitintegrated.demo.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.unitintegrated.demo.dto.DepartmentDTO;
import com.unitintegrated.demo.model.Department;




/**
 * Department Repository is for all data access operations for Department.
 * 
 * @author ThankGod Ukachukwu
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Query("select department from Department department where name =:name")
	Optional<Department> findByName(@Param("name") String name);
	
	
	@Query("select new com.unitintegrated.demo.dto.DepartmentDTO(d.departmentId,d.description, d.name) from "
			+ "Department d where d.departmentId = :departmentId")
	Optional<DepartmentDTO> findCustomD(@Param("departmentId") Integer departmentId);
	
	
	@Query("select new com.unitintegrated.demo.dto.DepartmentDTO(d.departmentId,d.description, d.name) from "
			+ "Department d ")
	List<DepartmentDTO> findAllOurDeps();

}
