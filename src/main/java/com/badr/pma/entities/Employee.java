package com.badr.pma.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long employeeId;

	private String firstName;
	private String lastName;
	private String email;

	// Multiple employees could be assigned to one project
	// Adding rules to many to one using Cascade Type + Fetch with LAZY
	// pattern(performance)

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE,
			CascadeType.PERSIST }, fetch = FetchType.LAZY)

	@JoinColumn(name = "project_id")
	private Project theProject;

	// For binding this into a form
	public Employee() {

	}

	// Remember not to include employeeID cause it is handled by Hibernate
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public Project getTheProject() {
		return theProject;
	}

	public void setTheProject(Project theProject) {
		this.theProject = theProject;
	}

}
