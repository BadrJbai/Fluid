package com.badr.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.badr.pma.dao.EmployeeRepository;
import com.badr.pma.dao.ProjectRepository;
import com.badr.pma.entities.Employee;
import com.badr.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectRepository proRepo;

	@Autowired
	EmployeeRepository empRepo;

	@GetMapping
	public String displayAllprojects(Model model) {
		List<Project> listAllProjects = proRepo.findAll();
		model.addAttribute("ListOfAllProjects", listAllProjects);

		return "projects/list-projects";
	}

	@GetMapping("/new")
	// using model to bind form between view and controller
	public String displayProjectFrom(Model model) {
		Project aproject = new Project();
		List<Employee> myEmployees = empRepo.findAll();
		model.addAttribute("listAllEmployees", myEmployees);

		model.addAttribute("project", aproject);
		return "projects/new-projects";
	}

	// Added bindingResult into the save
	@PostMapping("/save")
	public String createProject(Project project, BindingResult bindingResult, @RequestParam List<Long> employees,
			Model model) {
		// This should handle saving into the database;
		// use a redirect to prevent duplicate submission.
		proRepo.save(project);
		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
		for (Employee emp : chosenEmployees) {
			emp.setTheProject(project);
			empRepo.save(emp);
		}
		return "redirect:/projects/new";
	}

}
