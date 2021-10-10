package com.firststepitsolution.junittestcase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firststepitsolution.junittestcase.model.Employee;
import com.firststepitsolution.junittestcase.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	
	public Employee getEmployeeByName(String name) {
		return employeeRepository.findByName(name);
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	private int sumOfTwoNumber(Integer num1,Integer num2) {
		return num1+num2;
	}
	
	private Integer sumOfListOfNumber(List<Integer> listOfInteger, Integer num2) {
		Integer num1 = listOfInteger.stream().mapToInt(Integer::intValue).sum();
		return num1+num2;
	}
	
	private Employee saveEmployee(Employee emp) {
		return employeeRepository.save(emp);
	}
	
}