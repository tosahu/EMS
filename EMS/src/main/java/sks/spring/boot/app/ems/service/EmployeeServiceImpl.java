package sks.spring.boot.app.ems.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import sks.spring.boot.app.ems.config.EmployeeServiceConditionalConfiguration;
import sks.spring.boot.app.ems.exception.EmployeeNotFoundException;
import sks.spring.boot.app.ems.model.Employee;
import sks.spring.boot.app.ems.model.EmployeeAddress;

@Service
// Need to create Instance of EmployeeService by IOC Container based on some conditions
@Conditional(value = EmployeeServiceConditionalConfiguration.class)
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private RestTemplate restTemplate;
	
static HashMap<String,Employee> employeeMap= new HashMap<>();
	
	static {
		
		Employee emp1 = new Employee();
		emp1.setEmpId("1001");
		emp1.setDesignation("Tech Lead");
		emp1.setEmpName("Sanjay");
		emp1.setGender("Male");
		
		Employee emp2 = new Employee();
		emp2.setEmpId("1002");
		emp2.setDesignation("Tech Lead");
		emp2.setEmpName("Suresh");
		emp2.setGender("Male");
		
		Employee emp3 = new Employee();
		emp3.setEmpId("1003");
		emp3.setDesignation("Tech Lead");
		emp3.setEmpName("Rakesh");
		emp3.setGender("Male");
		
		Employee emp4 = new Employee();
		emp4.setEmpId("1004");
		emp4.setDesignation("Tech Lead");
		emp4.setEmpName("Satish");
		emp4.setGender("Male");
		
		Employee emp5 = new Employee();
		emp5.setEmpId("1005");
		emp5.setDesignation("Tech Lead");
		emp5.setEmpName("Mukesh");
		emp5.setGender("Male");
		
		employeeMap.put(emp1.getEmpId(), emp1);
		employeeMap.put(emp2.getEmpId(), emp2);
		employeeMap.put(emp3.getEmpId(), emp3);
		employeeMap.put(emp4.getEmpId(), emp4);
		employeeMap.put(emp5.getEmpId(), emp5);
			
		
	}
	

	EmployeeServiceImpl(){
		
		System.out.println("Instance Created : " + this.getClass().getName());
	}


	@Override
	public void addEmployee(Employee emp) {
		
		// we have to check if employee contains address or not if not then we have to call another webservice to get employee address based on emp id.
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		if(emp.getAddress() == null) {
			System.out.println("Address is null");
			EmployeeAddress empAddress = restTemplate.exchange("http://localhost:9090/employee/address/" + emp.getEmpId() , HttpMethod.GET, httpEntity,EmployeeAddress.class).getBody();
			System.out.println("Setting Address in Employee - " + empAddress.getEmpId()  + "received from EAMS WebService");
			emp.setAddress(empAddress.getAddress());
				
		}
			
		employeeMap.put(emp.getEmpId(), emp);
	}


	@Override
	public Collection<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeMap.values();
	}


	@Override
	public void removeEmployee(String empId) {
		// TODO Auto-generated method stub
		employeeMap.remove(empId);
	}


	@Override
	public Employee getEmployee(String empId) {
		// TODO Auto-generated method stub
		System.out.println("Inside employee get method" + empId);
		Employee emp = employeeMap.get(empId);
		
		if(emp == null) {
			throw new EmployeeNotFoundException("Employee : " + empId + " not exist");
		}
		return emp;
	}


	@Override
	public void updateEmployee(Employee emp) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		
		Employee employee = employeeMap.get(emp.getEmpId());
		employee.setEmpName(emp.getEmpName());
		employee.setDesignation(emp.getDesignation());
		employee.setGender(emp.getGender());
		employee.setSalary(emp.getSalary());
		
		if(emp.getAddress() == null) {
			System.out.println("Address is null");
			EmployeeAddress empAddress = restTemplate.exchange("http://localhost:9090/employee/address/" + emp.getEmpId() , HttpMethod.GET, httpEntity,EmployeeAddress.class).getBody();
			System.out.println("Setting Address in Employee - " + empAddress.getEmpId()  + "received from EAMS WebService");
			if(empAddress !=null)
				employee.setAddress(empAddress.getAddress());
				
		}
		
	}
	
	
	
	
	
}
