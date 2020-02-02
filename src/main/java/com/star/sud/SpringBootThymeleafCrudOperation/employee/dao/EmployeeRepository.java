/**
 * 
 */
package com.star.sud.SpringBootThymeleafCrudOperation.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.star.sud.SpringBootThymeleafCrudOperation.employee.model.TEmployee;

/**
 * @author Sudarshan
 *
 */
public interface EmployeeRepository extends JpaRepository<TEmployee, String> {

}
