package com.velocity.controller;

import org.springframework.core.env.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.velocity.customException.BusinessException;
import com.velocity.customException.ControllerException;
import com.velocity.model.Employee;
import com.velocity.service.EmployeeService;

import dto.DetailsOfDatabase;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private Environment environment;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.driver-class-name}")
	private String driverName;

	@GetMapping("/getUrl")
	public String getUrl() {
		return url;
	}

	@GetMapping("/getDriverName")
	public String getDriverName() {
		return driverName;
	}

	@PostMapping(value = "/saveEmployee")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		try {
			Employee emp = this.employeeService.saveEmployee(employee);
			return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorMessage(), e.getErrorStatus());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException e1 = new ControllerException("Something went wrong in conbtroller", 702);
			return new ResponseEntity<ControllerException>(e1, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/findByCity/{city}")
	public ResponseEntity<?> findByCity(@PathVariable("city") String city) {
		try {
			List<Employee> byCity = this.employeeService.findByCity(city);
			return new ResponseEntity<List<Employee>>(byCity, HttpStatus.FOUND);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorMessage(), e.getErrorStatus());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException e1 = new ControllerException("Something went wrong in conbtroller", 702);
			return new ResponseEntity<ControllerException>(e1, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/findByCityandStatus/{city}/{status}")
	List<Employee> findByCityandStatus(@PathVariable("city") String city, @PathVariable("status") String status) {
		List<Employee> byCityandStatus = this.employeeService.findByCityandStatus(city, status);
		return byCityandStatus;
	}

	@GetMapping("/findByCityandStatusPath")
	List<Employee> findByCityandStatusPath(@RequestParam("city") String city, @RequestParam("status") String status) {
		return this.employeeService.findByCityandStatusPath(city, status);
	}

	@GetMapping("/findByCityPath")
	List<Employee> findByCityPath(@RequestParam("city") String city) {
		return this.employeeService.findByCityPath(city);
	}

	@GetMapping("/getDatabaseDetails")
	public DetailsOfDatabase getDBDetails() {

		String driverClass = environment.getProperty("spring.datasource.driver-class-name");
		String url = environment.getProperty("spring.datasource.url");
		String userName = environment.getProperty("spring.datasource.username");
		String password = environment.getProperty("spring.datasource.username");

		DetailsOfDatabase database = new DetailsOfDatabase();
		database.setDriverClass(driverClass);
		database.setUrl(url);
		database.setUserName(userName);
		database.setPassword(password);
		return database;
	}

	@GetMapping("/updatePassword/{id}")
	public Boolean updatePassword(@PathVariable("id") Integer id, @RequestParam("password") String password)
			throws Exception {

		Employee emp = this.employeeService.findById(id);
		if (emp != null) {
			emp.setPassword(password);
			this.employeeService.updatePassword(emp);
			return true;
		}
		return false;

	}

}
