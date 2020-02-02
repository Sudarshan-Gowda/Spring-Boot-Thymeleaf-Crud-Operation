/**
 * 
 */
package com.star.sud.SpringBootThymeleafCrudOperation.home;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.star.sud.SpringBootThymeleafCrudOperation.employee.controller.EmployeeController;

/**
 * @author Sudarshan
 *
 */
@Controller
public class HomeController {

	// Static Attributes
	/////////////////////
	private static final Logger log = Logger.getLogger(HomeController.class);

	@GetMapping(value = "/")
	public String getLandingPage() {
		try {

		} catch (Exception e) {
			log.error("getLandingPage", e);
		}
		return EmployeeController.ACTION_REDIRECT + EmployeeController.MODULE_NAME + EmployeeController.REQ_MAP_LIST;
	}

}
