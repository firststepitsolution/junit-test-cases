package com.firststepitsolution.junittestcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firststepitsolution.junittestcase.model.Employee;

@Repository
	public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	 
	    public Employee findByName(String name);
	 
	}