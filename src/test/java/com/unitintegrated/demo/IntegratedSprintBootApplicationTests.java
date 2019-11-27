package com.unitintegrated.demo;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.unitintegrated.demo.dto.DepartmentDTO;
import com.unitintegrated.demo.service.DepartmentService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class IntegratedSprintBootApplicationTests {

	private final static String URI = "/api/departments";

	@MockBean
	private DepartmentService departmentService;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

	}

	@Test
	public void testGetDepartment() throws Exception {

		// given

		DepartmentDTO department = new DepartmentDTO();

		department.setDepartmentId(1);

		department.setName("Regional");

		Optional<DepartmentDTO> optionalDepartment = Optional.of(department);

		given(departmentService.findById(department.getDepartmentId())).willReturn(optionalDepartment);

		// Get Department

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/api/departments/{department_id}", 1)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.departmentId").value(1));

	}

	@Test
	void testGetAllDepartments() throws Exception {

		// given

		DepartmentDTO department = new DepartmentDTO();

		department.setDepartmentId(1);
		department.setDescription("our department");

		department.setName("Africa");

		List<DepartmentDTO> departments = Arrays.asList(department);
		given(departmentService.getAll()).willReturn(departments);

		// when + then

		mockMvc.perform(get(URI)).andExpect(status().isOk())
				.andExpect(content().json("[{'departmentId':1,'description':'our department', 'name':'Africa'}]"));

	}

}
