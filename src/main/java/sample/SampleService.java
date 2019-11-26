package sample;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.Employee;

@Path("/sampleservice")
public class SampleService {
	
	private static Map<String, Employee> employees = new HashMap<String, Employee>();
	
	static {
		
        Employee employee1 = new Employee();
        employee1.setEmployeeId("1");
        employee1.setEmployeeName("Fabrizio");
        employee1.setJob("Software Engineer");
        employees.put(employee1.getEmployeeId(), employee1);
        
        Employee employee2 = new Employee();
        employee2.setEmployeeId("2");
        employee2.setEmployeeName("Justin");
        employee2.setJob("Business Analyst");
        employees.put(employee2.getEmployeeId(), employee2);
        
    }

	@GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello(){
        return "Hello World";    
    }
	
	@GET
    @Path("/echo/{message}")
    @Produces("text/plain")
    public String echo(@PathParam("message")String message){
        return message;  
    }

	
	@GET
    @Path("/employees")
    @Produces("application/xml")
    public List<Employee> listEmployees(){
        return new ArrayList<Employee>(employees.values());
    }
	
	@GET
    @Path("/employee/{employeeid}")
    @Produces("application/xml")
    public Employee getEmployee(@PathParam("employeeid")String employeeId){
		return employees.get(employeeId);		
    }
	
	//employees with json
	@GET
    @Path("/json/employees/")
    @Produces("application/json")
    public List<Employee> listEmployeesJSON(){
		return new ArrayList<Employee>(employees.values());
    }

	@GET
    @Path("/json/employee/{employeeid}")
    @Produces("application/json")
    public Employee getEmployeeJSON(@PathParam("employeeid")String employeeId){
		return employees.get(employeeId);		
    }
	
	@GET
	@Path("/time")
	@Produces("text/plain")
	public String time(){
		String time=null;
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		 LocalDateTime now = LocalDateTime.now();
		 time=dtf.format(now);
		return time;
	}
	
	@GET
	@Path("/user/{user}")
	@Produces("text/plain")
	public String user(@PathParam("user")String user) {
		return "Thanks "+ user;
	}
	
	@POST
	@Path("employees/change")
	@Consumes("application/xml")
	@Produces("application/xml")
	public Employee addEmployee(Employee employee) {
		employees.put(employee.getEmployeeId(), employee);
		return employee;
	}
	

}

