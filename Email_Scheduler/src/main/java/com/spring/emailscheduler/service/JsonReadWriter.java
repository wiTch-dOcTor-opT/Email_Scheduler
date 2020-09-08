package com.spring.emailscheduler.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spring.emailscheduler.constants.Constants;
import com.spring.emailscheduler.model.AppProperties;
import com.spring.emailscheduler.model.Employee;

@Service("jsonfileparser")
public class JsonReadWriter {

	@Autowired
	private Constants constants;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(JsonReadWriter.class);
	
	public List<Employee> readEmployeesJson() 
	{
		Gson gson = new Gson();
		List<Employee> empList = null;
		try {
			Type empListType = new TypeToken<ArrayList<Employee>>() {}.getType();
			empList = gson.fromJson(new FileReader(constants.getSrcJsonFile()), empListType);
		}catch(Exception ex) {
			LOGGER.error("Error while reading employees from json. Reason: " +ExceptionUtils.getStackTrace(ex));
		}
		return empList;
	}
	
	public void writeEmployeesJson(List<Employee> empList) 
	{
		Gson gson = new Gson();
		FileWriter writer = null;
		try {
			Collections.sort(empList);
			String jSon = gson.toJson(empList);
			writer = new FileWriter(constants.getSrcJsonFile());
			writer.write(jSon);
		}catch(Exception ex) {
			LOGGER.error("Error while writing employees to json. Reason: " + ExceptionUtils.getStackTrace(ex));
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				LOGGER.error("Error while closing FileWriter. Reason: " + ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	public AppProperties readProperties() 
	{
		Gson gson = new Gson();
		AppProperties appProperties = null;
		try {
			appProperties = gson.fromJson(new FileReader(constants.getAppProperties()), AppProperties.class);
		}catch(Exception ex) {
			LOGGER.error("Error while reading properties from json. Reason: " + ExceptionUtils.getStackTrace(ex));
		}
		return appProperties;
	}
	
	public void writeAppProperties(AppProperties properties)
	{
		Gson gson = new Gson();
		FileWriter writer = null;
		try {
			String jSon = gson.toJson(properties);
			writer = new FileWriter(constants.getAppProperties());
			writer.write(jSon);
		}catch(Exception ex) {
			LOGGER.error("Error while writing properties to json. Reason: " + ExceptionUtils.getStackTrace(ex));
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				LOGGER.error("Error while closing FileWriter. Reason: " + ExceptionUtils.getStackTrace(e));
			}
		}
	}

}
