package com.unitintegrated.demo.service;

import java.util.List;
import java.util.Optional;

import com.unitintegrated.demo.dto.DepartmentDTO;
import com.unitintegrated.demo.model.Department;





/**
 * DepartmentService interface for all services related to Department.
 * @author ThankGod Ukachukwu
 *
 */

public interface DepartmentService {
	
	  
	  Optional<Department> findByName(String name);

	  Optional<DepartmentDTO> findById(Integer departmentId);
	  
	  public List<DepartmentDTO> getAll();
	  
	  
}



