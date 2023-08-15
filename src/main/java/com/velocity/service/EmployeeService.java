package com.velocity.service;

import com.velocity.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> findByCity(String city);

    List<Employee> findByCityandStatus(String city, String status);

    List<Employee> findByCityandStatusPath(String city, String status);

    List<Employee> findByCityPath(String city);
    
    
    public Employee findById(Integer id) throws Exception;
    
    public Employee updatePassword(Employee employee);

}
