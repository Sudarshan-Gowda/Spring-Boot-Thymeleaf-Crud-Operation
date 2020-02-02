package com.star.sud.SpringBootThymeleafCrudOperation.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.SpringBootThymeleafCrudOperation.constants.Constants;
import com.star.sud.SpringBootThymeleafCrudOperation.employee.dao.EmployeeRepository;
import com.star.sud.SpringBootThymeleafCrudOperation.employee.dto.EmployeeDetails;
import com.star.sud.SpringBootThymeleafCrudOperation.employee.model.TEmployee;
import com.star.sud.SpringBootThymeleafCrudOperation.employee.service.IEmployeeService;
import com.star.sud.SpringBootThymeleafCrudOperation.status.AppServiceStatus;
import com.star.sud.SpringBootThymeleafCrudOperation.status.AppServiceStatus.STATUS;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	// Static Attributes
	//////////////////////
	private static final Logger log = Logger.getLogger(EmployeeServiceImpl.class);

	// Attributes
	///////////////
	@Autowired
	private EmployeeRepository dao;

	@Override
	public AppServiceStatus addEmployee(EmployeeDetails dto) {
		log.debug("addEmployee");
		AppServiceStatus status = new AppServiceStatus();
		try {

			TEmployee entity = new TEmployee();
			dto.copyBeanProperties(entity);
			entity.setEmpId(String.valueOf(System.nanoTime()));
			entity.setEmpStatus(Constants.ACTIVE_STATUS);
			dao.save(entity);

			status.setStatus(STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("addEmployee", e);
			status.setStatus(STATUS.FAILED);
			status.setMessage(e.getMessage());
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.star.sud.employee.service.IEmployeeService#getAllEmployees()
	 */
	@Override
	public List<EmployeeDetails> getAllEmployees() {
		log.debug("getAllEmployees");
		try {
			List<EmployeeDetails> employeeDetails = new ArrayList<>();

			List<TEmployee> tEmployees = dao.findAll();

			tEmployees.stream().forEach(employees -> {
				EmployeeDetails emDetails = new EmployeeDetails();
				employees.copyBeanProperties(emDetails);
				employeeDetails.add(emDetails);
			});

			return employeeDetails;
		} catch (Exception e) {
			log.error("getAllEmployees", e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.star.sud.employee.service.IEmployeeService#modifyEmployee(java.lang.
	 * String)
	 */
	@Override
	public EmployeeDetails modifyEmployee(String radio) {
		log.debug("modifyEmployee");
		try {

			if (null == radio)
				throw new Exception("radio param is null or empty");

			EmployeeDetails dto = new EmployeeDetails();

			Optional<TEmployee> entity = dao.findById(radio);

			if (!entity.isPresent())
				throw new Exception("Employee Record Not Found!! for Id " + radio);

			TEmployee tEntity = entity.get();
			tEntity.copyBeanProperties(dto);
			return dto;

		} catch (Exception e) {
			log.error("modifyEmployee", e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.star.sud.employee.service.IEmployeeService#updateEmployee(com.star.sud.
	 * employeer.dto.EmployeeDetails)
	 */
	@Override
	public AppServiceStatus updateEmployee(EmployeeDetails dto) {
		log.debug("updateEmployee");
		AppServiceStatus status = new AppServiceStatus();
		try {

			if (null == dto)
				throw new Exception("dto param is null or empty");

			if (null == dto.getEmpId())
				throw new Exception("empId param is null or empty");

			Optional<TEmployee> storedEntity = dao.findById(dto.getEmpId());

			if (!storedEntity.isPresent())
				throw new Exception("User Record Not Found! for Id: " + dto.getEmpId());

			TEmployee updated = new TEmployee();
			dto.copyBeanProperties(updated);
			BeanUtils.copyProperties(updated, storedEntity);
			updated.setEmpStatus(Constants.ACTIVE_STATUS);

			dao.save(updated);
			// dao.update(updated);
			status.setStatus(STATUS.SUCCESS);

		} catch (Exception e) {
			log.error("deleteEmployee", e);
			status.setStatus(STATUS.FAILED);
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.star.sud.employee.service.IEmployeeService#deleteEmployee(java.lang.
	 * String)
	 */
	@Override
	public AppServiceStatus deleteEmployee(String radio) {
		log.debug("deleteEmployee");
		AppServiceStatus status = new AppServiceStatus();
		try {

			if (null == radio)
				throw new Exception("radio param is null or empty");

			dao.deleteById(radio);

			status.setStatus(STATUS.SUCCESS);

		} catch (Exception e) {
			log.error("deleteEmployee", e);
			status.setStatus(STATUS.FAILED);
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.star.sud.employee.service.IEmployeeService#viewEmployee(java.lang.String)
	 */
	@Override
	public EmployeeDetails viewEmployee(String radio) {
		log.debug("viewEmployee");
		try {

			if (null == radio)
				throw new Exception("radio param is null or empty");

			TEmployee entity = dao.getOne(radio);
			EmployeeDetails dto = new EmployeeDetails();
			entity.copyBeanProperties(dto);
			return dto;

		} catch (Exception e) {
			log.error("viewEmployee", e);
			return null;
		}
	}

}
