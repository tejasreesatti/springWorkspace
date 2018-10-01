package com.capgemini.employeeapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.employeeapp.entities.Employee;
import com.capgemini.employeeapp.servive.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage() {
		return "index";
	}
	
	@RequestMapping(value = "/addEmployeePage", method = RequestMethod.GET)
	public String getAddEmployeePage(Model model) {
		model.addAttribute("employee", new Employee());
		return "addEmployeeForm";
	}
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public String addNewEmployee(@ModelAttribute Employee employee) {
		employeeService.addEmployee(employee);
		return "redirect:/findAllEmployees";
	}
	
	@RequestMapping(value = "/findAllEmployees", method=RequestMethod.GET)
	public String getAllEmployeeDetails(Model model) {
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("allEmployees", employees);
		return "allEmployees";
		
	}
	
	@RequestMapping(value="/deleteEmployee/{employeeId}", method=RequestMethod.GET)
		public String deleteEmployee(@PathVariable int employeeId) {
			employeeService.deleteEmployee(employeeId);
			return "redirect:/findAllEmployees";
		}
	@RequestMapping(value ="/editEmployeePage/{employeeId}", method = RequestMethod.GET)
	public String editEmployeePage(@PathVariable int employeeId, Model model) {
		Employee employee = employeeService.findEmployeeById(employeeId);
		model.addAttribute("employee", employee);
		return "updateEmployeeForm";
		
	}
	
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute Employee employee) {
		employeeService.updateEmployee(employee);
		return "redirect:/findAllEmployees";
	}
	
}
