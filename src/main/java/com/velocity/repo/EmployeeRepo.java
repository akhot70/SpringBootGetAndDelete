package com.velocity.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.velocity.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    @Query(value ="select * from employee e where city=?1" ,nativeQuery = true)
    List<Employee> findByCity(String city);

    @Query(value ="select * from employee e where city=?1 and status=?2" ,nativeQuery = true)
    List<Employee> findByCityandStatus(String city,String status);

    @Query(value = "select * from employee e where city=?1 and status=?2", nativeQuery = true)
    List<Employee> findByCityandStatusPath(String city, String status);

    @Query(value = "select * from employee e where city=?1",nativeQuery = true)
    List<Employee> findByCityPath(String city);


	

}
