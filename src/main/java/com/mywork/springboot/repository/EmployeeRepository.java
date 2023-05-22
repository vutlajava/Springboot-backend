package com.mywork.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mywork.springboot.model.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long>{

}
