package com.deloitte.spring.boot.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.spring.boot.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// public abstract Object findByFieldName(Object fieldName)
	public abstract List<Employee> findByFirstName(String firstName);

	public abstract List<Employee> findBySalaryGreaterThan(double salary);
	// repository provides methods for basic CRUD operations -
	// INSERT - save();
	// UPDATE - save();
	// DELETE - deleteById();
	// SELECT one - findById();
	// SELECT all - findAll();
	// No need to declare methods for these operations.

	// for other business specific requirements, methods need to be declared here.
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.core-concepts
}