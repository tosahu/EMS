package sks.spring.boot.app.ems.controller;

import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sks.spring.boot.app.ems.model.Employee;
import sks.spring.boot.app.ems.service.EmployeeServiceImpl;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl empService;
	
	 private static final Logger LOG = Logger.getLogger("EmployeeController");
	

	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ResponseEntity<Object> addEmployee(@RequestBody Employee emp) {
		LOG.info("addEmployee method called");
		empService.addEmployee(emp);
		return new ResponseEntity<>("Employee Added Successfully",HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/modify/{empId}" ,method = RequestMethod.PUT)
	public ResponseEntity<Object> modifyEmployee(@PathVariable String empId,@RequestBody Employee emp) {
		LOG.info("modifyEmployee method called");
		empService.updateEmployee(emp);
		
		return new ResponseEntity<>("Employee Modified Successfully",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{empId}" ,method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteEmployee(@PathVariable String empId) {
		LOG.info("deleteEmployee method called");
		empService.removeEmployee(empId);
		
		return new ResponseEntity<>("Employee Deleted Successfully",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all" ,method = RequestMethod.GET)
	public ResponseEntity<Object> displayAllEmployee() {
		LOG.info("displayAllEmployee method called");
		Collection<Employee> employeeList = empService.getAllEmployee();

		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> defaultMethod(HttpServletRequest request,HttpServletResponse response) {
		LOG.info("defaultMethod method called");
		return new ResponseEntity<>("To get List of All Employee use <url>/employee/all. Also new attribute has been added - " + request.getAttribute("extraParameter") ,HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value = "/{empId}", method = RequestMethod.GET)
	public ResponseEntity<Object> displayEmployee(@PathVariable String empId) {
		LOG.info("displayEmployee method called");
		Employee emp = empService.getEmployee(empId);
		
		return new ResponseEntity<>(emp,HttpStatus.OK);
	}
}
