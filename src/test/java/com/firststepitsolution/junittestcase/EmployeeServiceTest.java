package com.firststepitsolution.junittestcase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.firststepitsolution.junittestcase.model.Employee;
import com.firststepitsolution.junittestcase.repository.EmployeeRepository;
import com.firststepitsolution.junittestcase.service.EmployeeService;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Test
	public void getAllEmployeesTest() {
		when(employeeRepository.findAll()).thenReturn(Stream
				.of(new Employee(1L,"pankaj"),new Employee(1L,"rinku"),new Employee(1L,"ansh")).collect(Collectors.toList()));
		assertEquals(3,employeeService.getAllEmployees().size());
	}
	
	@Test
	public void getEmployeeByNameTest() {
		when(employeeRepository.findByName("pankaj")).thenReturn(new Employee(1L,"pankaj"));
		//assertEquals(Employee, employeeService.getEmployeeByName("pankaj"));
		assertThat(employeeService.getEmployeeByName("pankaj").getName()).isEqualTo("pankaj");
	}
	
	/**
	 * Way to write test cases for private method.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void sumOfTwoNumberTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = EmployeeService.class.getDeclaredMethod("sumOfTwoNumber", Integer.class,Integer.class);
		method.setAccessible(true);
		int sum = (int)method.invoke(employeeService, 10,40);
		assertEquals(50, sum);
		
	}
	
	
	
	/**
	 * Way to write test cases for private method.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void sumOfListOfNumberTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = EmployeeService.class.getDeclaredMethod("sumOfListOfNumber", List.class,Integer.class);
		method.setAccessible(true);
		List<Integer> listOfInteger = new ArrayList<>();
		listOfInteger.add(10);
		listOfInteger.add(20);
		Integer sum = (Integer)method.invoke(employeeService, listOfInteger,40);
		assertEquals(70, sum);
		
	}
	
	/**
	 * Way to write test cases for private method.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void saveEmployee() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = EmployeeService.class.getDeclaredMethod("saveEmployee", Employee.class);
		method.setAccessible(true);
		Employee emp = new Employee();
		emp.setId(1L);
		emp.setName("testName");
		
		when(employeeRepository.save(emp)).thenReturn(emp);
		Employee restultEmp = (Employee)method.invoke(employeeService, emp);
		assertEquals("testName", restultEmp.getName());
		
	}
}
