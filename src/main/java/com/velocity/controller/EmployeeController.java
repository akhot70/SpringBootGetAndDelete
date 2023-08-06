package com.velocity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.velocity.customException.BusinessException;
import com.velocity.customException.ControllerException;
import com.velocity.model.Employee;
import com.velocity.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = "/saveEmployee")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		try {
			Employee emp = this.employeeService.saveEmployee(employee);
			return new ResponseEntity<Employee>(emp,HttpStatus.CREATED);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorMessage(), e.getErrorStatus());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException e1 = new ControllerException("Something went wrong in conbtroller",702);
			return new ResponseEntity<ControllerException>(e1,HttpStatus.BAD_REQUEST);
		}
	
	}

	@GetMapping("/findByCity/{city}")
	public ResponseEntity<?> findByCity(@PathVariable("city") String city){
		try {
			List<Employee> byCity = this.employeeService.findByCity(city);
			return new ResponseEntity<List<Employee>>(byCity,HttpStatus.FOUND);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorMessage(), e.getErrorStatus());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException e1 = new ControllerException("Something went wrong in conbtroller",702);
			return new ResponseEntity<ControllerException>(e1,HttpStatus.BAD_REQUEST);
		}
	

	}

	@GetMapping("/findByCityandStatus/{city}/{status}")
	List<Employee> findByCityandStatus(@PathVariable("city") String city,@PathVariable("status") String status){
		List<Employee> byCityandStatus = this.employeeService.findByCityandStatus(city,status);
		return byCityandStatus;
	}

	@GetMapping("/findByCityandStatusPath")
	List<Employee> findByCityandStatusPath(@RequestParam("city") String city,@RequestParam("status") String status){
		return this.employeeService.findByCityandStatusPath(city, status);
	}

	@GetMapping("/findByCityPath")
	List<Employee> findByCityPath(@RequestParam("city") String city){
		return this.employeeService.findByCityPath(city);
	}


}
