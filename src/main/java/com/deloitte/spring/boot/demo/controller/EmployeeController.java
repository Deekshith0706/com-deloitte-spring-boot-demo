package com.deloitte.spring.boot.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.spring.boot.demo.model.Employee;
import com.deloitte.spring.boot.demo.service.EmployeeService;

// http://localhost:9999/swagger-ui/index.html

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeService empService;

//	http://localhost:9999/emp/get-all-emps

//	@GetMapping("/get-all-emps")
	@RequestMapping(path = "/get-all-emps", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmps() {
		System.out.println("get-all-emps");
		List<Employee> empList = empService.getAllEmployees();
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "List of all Employees displayed successfully!");
		ResponseEntity<List<Employee>> response = new ResponseEntity<>(empList, headers, status);
		return response;
	}

//	http://localhost:9999/emp/get-emp-by-id/{eid}

//	@GetMapping("/get-emp-by-id/{eid}")
//	public Employee getEmpById(@PathVariable(name = "eid") int employeeId) {
//		System.out.println(employeeId);
//		return empService.getEmployeeById(employeeId);
//	}

	@GetMapping("/get-emp-by-id/{eid}")
	public ResponseEntity<Employee> getEmpById(@PathVariable(name = "eid") int employeeId) {
		System.out.println(employeeId);
		Employee emp = empService.getEmployeeById(employeeId);
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employee with employeeId " + employeeId + " is found successfully!");
		ResponseEntity<Employee> response = new ResponseEntity<>(emp, headers, status);
		return response;
	}

//	http://localhost:9999/emp/add-emp

	@RequestMapping(path = "/add-emp", method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmp(@Valid @RequestBody Employee employee) {
		HttpStatus status = HttpStatus.CREATED;
		Employee emp = empService.addEmployee(employee);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employee with employeeId " + employee.getEmployeeId() + " is added successfully!");
		ResponseEntity<Employee> response = new ResponseEntity<>(emp, headers, status);
		return response;
	}

//	http://localhost:9999/emp/update-emp

	@RequestMapping(path = "/update-emp", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmp(@Valid @RequestBody Employee employee) {

		Employee emp = empService.updateEmployee(employee);
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employee with employeeId " + employee.getEmployeeId() + " is updated successfully!");
		ResponseEntity<Employee> response = new ResponseEntity<>(emp, headers, status);
		return response;
	}

//	http://localhost:9999/emp/delete-emp/{eid}

	@RequestMapping(path = "/delete-emp/{eid}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmp(@PathVariable(name = "eid") int employeeId) {
		Employee emp = empService.deleteEmployee(employeeId);
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employee with employeeId " + employeeId + " is deleted successfully!");
		ResponseEntity<Employee> response = new ResponseEntity<>(emp, headers, status);
		return response;
	}
	
	@GetMapping("/get-emp-by-name/{first-name}")
	public ResponseEntity<List<Employee>> getEmpsByFirstName(@PathVariable(name = "first-name") String firstName){
		List<Employee> empList = empService.getAllEmployeesByFirstName(firstName);
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employees with first name " + firstName + " retrieved successfully!");
		ResponseEntity<List<Employee>> response = new ResponseEntity<>(empList, headers, status);
		return response;
	}
	
	@GetMapping("/get-emp-by-salary/{salary}")
	public ResponseEntity<List<Employee>> getEmpsBySalaryGreaterThan(@PathVariable(name = "salary") double salary){
		List<Employee> empList = empService.getAllEmployeesBySalaryGreaterThan(salary);
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employees with salary greater than " + salary + " retrieved successfully!");
		ResponseEntity<List<Employee>> response = new ResponseEntity<>(empList, headers, status);
		return response;
	}
}

//package com.deloitte.spring.boot.demo.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.deloitte.spring.boot.demo.model.Employee;
//import com.deloitte.spring.boot.demo.service.EmployeeService;
//
//@RestController
//public class EmployeeController {
//
////	HTTP Requests and Spring annotations - 
////	@RequestMapping
////	GET - @GetMapping
////	POST - @PostMapping
////	PUT - @PutMapping
////	DELETE - @DeleteMapping
//
//	@Autowired
//	EmployeeService empService;
//
////	@GetMapping("/get-all-emps")
//	@RequestMapping(path = "/get-all-emps", method = RequestMethod.GET)
//	public List<Employee> getAllEmps() {
//		System.out.println("get-all-emps");
//		return empService.getAllEmployees();
//	}
//
//	@GetMapping("/get-emp-by-id/{eid}")
//	public Employee getEmpById(@PathVariable(name = "eid") int employeeId) {
//		System.out.println(employeeId);
//		return empService.getEmployeeById(employeeId);
//	}
//
//	@RequestMapping(path = "/add-emp", method = RequestMethod.POST)
//	public Employee addEmp(@RequestBody Employee employee) {
//		return empService.addEmployee(employee);
//	}
//
//	@RequestMapping(path = "/update-emp", method = RequestMethod.PUT)
//	public Employee updateEmp(@RequestBody Employee employee) {
//		return empService.updateEmployee(employee);
//	}
//
//	@RequestMapping(path = "/delete-emp/{eid}", method = RequestMethod.DELETE)
//	public Employee deleteEmp(@PathVariable(name = "eid") int employeeId) {
//		return empService.deleteEmployee(employeeId);
//	}
//
//}