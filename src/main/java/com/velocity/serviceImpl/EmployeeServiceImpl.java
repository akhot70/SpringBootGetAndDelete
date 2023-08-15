package com.velocity.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velocity.customException.BusinessException;
import com.velocity.model.Employee;
import com.velocity.repo.EmployeeRepo;
import com.velocity.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		try {
		if(employee.getFirstName().isEmpty() || employee.getFirstName().length()==0) {
			
			throw new BusinessException("Please send proper name, its blank!!!",601);
		}
			Employee emp =this.employeeRepo.save(employee);
			return emp;
		}catch (IllegalArgumentException i){
			throw new BusinessException("Given employee is null !!!"+i.getMessage(),602);
		}catch (Exception e) {
			throw new BusinessException("something went wrong in service layer !!!"+e.getMessage(),603);
		}
		
	}

	@Override
	public List<Employee> findByCity(String city) {
		try {
			if(city.isEmpty() || city.isBlank()) {
				throw new BusinessException("Please send proper city name, its blank!!!", 604);
			}
			List<Employee> byCity = this.employeeRepo.findByCity(city);
			return byCity;
		}catch (Exception e) {
			throw new BusinessException("something went wrong in service layer !!!"+e.getMessage(),605);
		}
		
	}

	@Override
	public List<Employee> findByCityandStatus(String city, String status) {
		try {
			if((city.isBlank() && status.isBlank()) || (city.isEmpty() && status.isEmpty())) {
				throw new BusinessException("Please send proper city name and status, its blank!!!", 606);
			}
			List<Employee> byCityandStatus = this.employeeRepo.findByCityandStatus(city, status);
			return byCityandStatus;
		}catch (Exception e) {
			throw new BusinessException("something went wrong in service layer !!!"+e.getMessage(),607);
		}
		
	}

	@Override
	public List<Employee> findByCityandStatusPath(String city, String status) {
		return this.employeeRepo.findByCityandStatusPath(city, status);
	}

	@Override
	public List<Employee> findByCityPath(String city) {
		return this.employeeRepo.findByCityPath(city);
	}

	@Override
	public Employee findById(Integer id) throws Exception {
	Employee employee	= this.employeeRepo.findById(id);
	try {
		if (employee == null)
			throw new Exception("User not found.");
	} catch (Exception e) {
		e.printStackTrace();
	}
		return employee;
	
		
	}

	@Override
	public Employee updatePassword(Employee employee) {
		Employee emp =this.employeeRepo.save(employee);
		return emp;
	}


	
	
}

	


