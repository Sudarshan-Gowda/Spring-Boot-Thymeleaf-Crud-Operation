package com.star.sud.SpringBootThymeleafCrudOperation.employee.service;

import java.util.List;

import com.star.sud.SpringBootThymeleafCrudOperation.employee.dto.EmployeeDetails;
import com.star.sud.SpringBootThymeleafCrudOperation.status.AppServiceStatus;

public interface IEmployeeService {

	AppServiceStatus addEmployee(EmployeeDetails dto);

	/**
	 * @return
	 */
	List<EmployeeDetails> getAllEmployees();

	/**
	 * @param radio
	 * @return
	 */
	EmployeeDetails modifyEmployee(String radio);

	/**
	 * @param dto
	 * @return
	 */
	AppServiceStatus updateEmployee(EmployeeDetails dto);

	/**
	 * @param radio
	 * @return
	 */
	AppServiceStatus deleteEmployee(String radio);

	/**
	 * @param radio
	 * @return
	 */
	EmployeeDetails viewEmployee(String radio);

}
