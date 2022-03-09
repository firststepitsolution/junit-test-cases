package com.firststepitsolution.junittestcase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firststepitsolution.junittestcase.model.Employee;
import com.firststepitsolution.junittestcase.repository.EmployeeRepository;
import com.firststepitsolution.junittestcase.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EmployeeRestControllerTest {
	private MockMvc mockMvc;
	
	ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private WebApplicationContext context;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@MockBean
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepository;

	
	@Test
	public void getEmployeeTest() throws Exception {
		
		when(employeeService.getAllEmployees()).thenReturn(Stream
				.of(new Employee(1L,"pankaj"),new Employee(1L,"rinku"),new Employee(1L,"ansh")).collect(Collectors.toList()));
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees")
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
//		   String content = mvcResult.getResponse().getContentAsString();
//		   //Employee[] empList = super.mapFromJson(content, Product[].class);
//		   Employee[] empList = om.readValue(content, Employee[].class);
//		   assertTrue(empList.length>0);
	}
}
