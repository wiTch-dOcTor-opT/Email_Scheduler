package com.spring.emailscheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.spring.emailscheduler.model.AppProperties;
import com.spring.emailscheduler.model.Employee;
import com.spring.emailscheduler.service.EmployeeService;
import com.spring.emailscheduler.service.PropertyService;

@Controller
public class IndexController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PropertyService propertyService;
	
	@GetMapping("/home")
	public String getIndex() {
		return "index";
	}
	
	@GetMapping("/getemployees")
	@ResponseBody
	public ResponseEntity<String> getEmployees() {
		String table = employeeService.sendEmployeeTable();
		return new ResponseEntity<String>(table, HttpStatus.OK);
	}
	
	@PostMapping("/saveemployees")
	@ResponseBody
	public ResponseEntity<String> saveEmployees(@RequestBody List<Employee> empListFromWeb) {
		String table = employeeService.writeAndSendEmployee(empListFromWeb);
		return new ResponseEntity<String>(table, HttpStatus.OK);
	}
	
	@GetMapping("/getproperties")
	@ResponseBody
	public ResponseEntity<String> getPropertiesForm()
	{
		String table = propertyService.sendPropertyTable();
		return new ResponseEntity<String>(table, HttpStatus.OK);
	}
	
	@PostMapping("/saveproperties")
	@ResponseBody
	public ResponseEntity<String> saveProperties(@RequestBody AppProperties appPropertiesFromWeb) {
		String table = propertyService.writeAndSendPropertyTable(appPropertiesFromWeb);
		return new ResponseEntity<String>(table, HttpStatus.OK);
	}
	
	@GetMapping("/editemployees")
	public String editEmployees() {
		return "employeeForm";
	}
	
	@GetMapping("/editproperties")
	public String editProperties() {
		return "propertiesForm";
	}	

}
