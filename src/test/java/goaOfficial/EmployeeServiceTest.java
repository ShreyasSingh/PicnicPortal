package goaOfficial;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class EmployeeServiceTest {
	private EmployeeRepository employeeRepository;
	private EmployeeService employeeService;
	
	@Before 
	public void setup(){
		 employeeRepository = Mockito.mock(EmployeeRepository.class);
		 employeeService = new EmployeeService(employeeRepository);
		
	}
	@Test
	public void shouldGetAllEmployee(){
		Mockito.when(employeeRepository.findAll()).thenReturn(Arrays.asList(new Employe()));	
		List<Employe> employees = employeeService.getAllEmployees();
		
		assertEquals(1, employees .size());
	}

	@Test
	public void shouldDeleteAllEmployee(){
		employeeService.deleteAllEmployees();
		
		Mockito.verify(employeeRepository).deleteAllRecords();
	}
	
	@Test
	public void shouldSaveEmployee(){
		Employe employee = new Employe();
		
		employeeService .saveEmployee(employee);
				
		Mockito.verify(employeeRepository).save(employee);
	}
	
	@Test 
	public void shouldDeleteSingleEmployee(){
		int empID = 570 ;
	
		employeeService.deleteSingleEmployee(empID);
		Mockito.verify(employeeRepository).deleteRecord(empID);
	}

}
