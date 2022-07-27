package com.deloitte.spring.boot.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.deloitte.spring.boot.demo.model.Department;
import com.deloitte.spring.boot.demo.model.Employee;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Employee> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", e.getMessage());
		ResponseEntity<Employee> response = new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
		return response;
	}
	
	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<Employee> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException e) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", e.getMessage());
		ResponseEntity<Employee> response = new ResponseEntity<>(null, headers, HttpStatus.IM_USED);
		return response;
	}
	
	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<Department> handleDepartmentNotFoundException(DepartmentNotFoundException d){
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", d.getMessage());
		ResponseEntity<Department> response = new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
		return response;
	}

	// more exception handlers

//	@ExceptionHandler(EmployeeNotFoundException.class)
//	public ResponseEntity<Employee> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("message", e.getMessage());
//		ResponseEntity<Employee> response = new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
//		return response;
//	}

}