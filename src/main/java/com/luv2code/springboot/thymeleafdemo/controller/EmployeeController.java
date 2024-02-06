package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Controller
@RequestMapping("/employees")
@Validated
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// get the employees from db
		List<Employee> theEmployees = employeeService.findAll();
		System.out.println(theEmployees);
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		// get the employee from the service
		Employee theEmployee = employeeService.findById(theId);
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		// send over to our form
		return "employees/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee theEmployee, Model theModel) {
		// save the employee
		List<Employee> list1 = employeeService.findByEmail(theEmployee.getEmail());
		if(list1.size()>0) {
			theModel.addAttribute("emailerror", true);
			theModel.addAttribute("employee", theEmployee);
			return "employees/employee-form";
		}else {
			employeeService.save(theEmployee);
			return "redirect:/employees/list"; 
		}
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Integer employeeId) {
		employeeService.deleteById(employeeId);
		return "redirect:/employees/list";
	}
	
	@GetMapping("/findByEmail")
	public String findByEmail(@RequestParam String email, Model theModel) {

		// get the employees from db
		List<Employee> theEmployees = employeeService.findByEmail(email);
		System.out.println(theEmployees);
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}
}









