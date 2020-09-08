package com.spring.emailscheduler.model;

public class Employee implements Comparable<Employee>{

	private String name;
	private String email;
	private String birthdate;

	public Employee() {};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public int compareTo(Employee o) {
		return this.getName().compareTo(o.getName());
	}
	
	

}
