package com.badr.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.badr.pma.dao.EmployeeRepository;
import com.badr.pma.dao.ProjectRepository;
import com.badr.pma.entities.Employee;
import com.badr.pma.entities.Project;

@Controller
public class HomeController {

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	ProjectRepository proReop;

	@GetMapping("/")
	public String displayProjectsInsideHomePage(Model model) {
		List<Project> projectsList = proReop.findAll();
		model.addAttribute("allMyhomeProjects", projectsList);
		List<Employee> employeeList = empRepo.findAll();
		model.addAttribute("allMyEmployees", employeeList);
		return "main/home";
	}

}
