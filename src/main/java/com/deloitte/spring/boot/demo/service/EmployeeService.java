package com.deloitte.spring.boot.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger; // important!
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.spring.boot.demo.exception.EmployeeAlreadyExistsException;
import com.deloitte.spring.boot.demo.exception.EmployeeNotFoundException;
import com.deloitte.spring.boot.demo.model.Employee;
import com.deloitte.spring.boot.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

//	scenarios - 
//	user tries to get non-existing record (employeeId)
//	user tries to add duplicate record (employeeId)
//	user tries to update non-existing record (employeeId)
//	user tries to delete non-existing record (employeeId)

	@Autowired
	private EmployeeRepository empRepository;

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	public List<Employee> getAllEmployees() {

		return empRepository.findAll();
	}

	public Employee getEmployeeById(int employeeId) {
		LOG.info(Integer.toString(employeeId));
		Optional<Employee> empOptional = empRepository.findById(employeeId);
		if (empOptional.isPresent())
			return empOptional.get();
		else {
			String errorMessage = "Employee with eid " + employeeId + " not found";
			LOG.warn(errorMessage);
			throw new EmployeeNotFoundException(errorMessage);
		}
	}

	public List<Employee> getAllEmployeesByFirstName(String firstName) {
		LOG.info(firstName);
		List<Employee> empList = empRepository.findByFirstName(firstName);
		if (!empList.isEmpty())
			return empList;
		else {
			String errorMessage = "Employee with firstName " + firstName + " not found";
			LOG.warn(errorMessage);
			throw new EmployeeNotFoundException(errorMessage);
		}
	}

	public List<Employee> getAllEmployeesBySalaryGreaterThan(double salary) {
		LOG.info(Double.toString(salary));
		List<Employee> empList = empRepository.findBySalaryGreaterThan(salary);
		if (!empList.isEmpty())
			return empList;
		else {
			String errorMessage = "Employee with salary greater than " + salary + " not found";
			LOG.warn(errorMessage);
			throw new EmployeeNotFoundException(errorMessage);
		}
	}

	public Employee addEmployee(Employee employee) {
		LOG.info(employee.toString());
		if (empRepository.existsById(employee.getEmployeeId())) {
			String errorMessage = "Employee with eid " + employee.getEmployeeId() + " already EXISTS!";
			LOG.warn(errorMessage);
			throw new EmployeeAlreadyExistsException(errorMessage);
		} else
			return empRepository.save(employee);

	}

	public Employee updateEmployee(Employee employee) {
		LOG.info(employee.toString());
		Employee emp = this.getEmployeeById(employee.getEmployeeId());
		emp = empRepository.save(employee);
		return emp;
	}

	public Employee deleteEmployee(int employeeId) {
		LOG.info(Integer.toString(employeeId));
		return this.getEmployeeById(employeeId);
	}

}

//package com.deloitte.spring.boot.demo.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.deloitte.spring.boot.demo.model.Employee;
//import com.deloitte.spring.boot.demo.reository.EmployeeRepository;
//
//@Service
//public class EmployeeService {
//
//	@Autowired
//	EmployeeRepository empRepository;
//
//	public List<Employee> getAllEmployees() {
//		return empRepository.findAll();
//	}
//
//	public Employee getEmployeeById(int employeeId) {
//		System.out.println("service getEmployeeById");
//		Optional<Employee> empOptional = empRepository.findById(employeeId);
//		Employee emp = empOptional.get();
//		return emp;
//	}
//
//	public Employee addEmployee(Employee employee) {
//		return empRepository.save(employee);
//	}
//
//	public Employee updateEmployee(Employee employee) {
//		return empRepository.save(employee);
//	}
//
//	public Employee deleteEmployee(int employeeId) {
//		empRepository.deleteById(employeeId);
//		return null;
//	}
//}