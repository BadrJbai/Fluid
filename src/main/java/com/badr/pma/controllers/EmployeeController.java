package com.badr.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.badr.pma.dao.EmployeeRepository;
import com.badr.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository empReop;

	@GetMapping
	public String displayAllEmployees(Model model) {
		List<Employee> listAllEmployees = empReop.findAll();
		model.addAttribute("AllEmployeeslist", listAllEmployees);
		return "employees/list-employees";
	}

	@GetMapping("/new")
	public String displayEmployee(Model model) {
		Employee emp = new Employee();
		model.addAttribute("employee", emp);
		return "employees/new-employee";
	}

	@PostMapping("/save")
	public String createEmployee(Employee emp, Model model) {
		empReop.save(emp);
		return "redirect:/employees/new";
	}

}
