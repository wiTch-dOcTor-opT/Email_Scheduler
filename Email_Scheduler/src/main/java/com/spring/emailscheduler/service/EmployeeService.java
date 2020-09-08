package com.spring.emailscheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.emailscheduler.model.Employee;

@Service("employeeService")
public class EmployeeService {
	
	@Autowired
	private JsonReadWriter jsonReaderWriter;
	
	public String sendEmployeeTable() {
		String table;
		int count = 1;
		List<Employee> empList = jsonReaderWriter.readEmployeesJson();
		table = "<tbody><th width='5%'>First Name</th><th width='5%'>Email</th><th width='5%'>Birthdate</th><th width='5%'>Delete</th>";
		if (empList != null) {
			for (Employee emp : empList) {
				table += "<tr id='row" + count + "'><td contenteditable='true' class='employee_name'>" + emp.getName()
						+ "</td>";
				table += "<td contenteditable='true' class='employee_email'>" + emp.getEmail() + "</td>";
				table += "<td><input type='date' class='employee_birthdate' value='" + emp.getBirthdate() + "'></input></td>";
				table += "<td><img src='/images/delete.png' data-row='row" + count + "' class='remove'></img></td></tr>";
				count++;
			}
		}
		return table + "</tbody>";

	}
	
	public String writeAndSendEmployee(List<Employee> empListFromWeb)
	{
		String table;
		int count = 1;
		table = "<tbody><th width='5%'>First Name</th><th width='5%'>Email</th><th width='5%'>Birthdate</th><th width='5%'>Delete</th>";
		jsonReaderWriter.writeEmployeesJson(empListFromWeb);
		List<Employee> empListFromJson = jsonReaderWriter.readEmployeesJson();
		if (empListFromJson != null) 
		{
			for(Employee emp : empListFromJson)
			{
				table += "<tr id='row" + count + "'><td contenteditable='true' class='employee_name'>" + emp.getName() + "</td>";
				table += "<td contenteditable='true' class='employee_email'>" + emp.getEmail() + "</td>";
				table += "<td><input type='date' class='employee_birthdate' value='" + emp.getBirthdate() +"'></input></td>";
				table += "<td><img src='/images/delete.png' data-row='row" + count + "' class='remove'></img></td></tr>";
				count++;
			}
		}
		return table + "</tbody>";
	}

}
