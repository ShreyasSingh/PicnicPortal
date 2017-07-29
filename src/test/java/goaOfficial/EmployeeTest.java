package goaOfficial;

import static org.junit.Assert.*;
import org.junit.Test;

public class EmployeeTest {

	@Test
	public void employeeShouldNotBeNull() {
		// given
		Employee employee = new Employee();

		// when
		boolean comparisonResult = employee.equals(null);

		// then
		assertFalse(comparisonResult);
	}

	@Test
	public void employeeShouldBeEqualToItself() {
		// give
		Employee employee = new Employee();

		// when
		boolean comparisonResult = employee.equals(employee);

		// then
		assertTrue(comparisonResult);
	}

	@Test
	public void EmployeesAreNotBeEqual() {
		//given
		Employee employee1 = new Employee();
		Employee employee2 = new Employee();

		
		//Also check whether both objects are instances of the Employee Class
	
		
		// when
		boolean comparisonResult = employee1.equals(employee2);

		// then
		assertFalse(comparisonResult);
	}
	
	@Test
	public void checkEmployeesAreEqual(){
		Employee employee1 = new Employee("Shreyas", "1");
		Employee employee2 = new Employee("Shreyas", "1");
		
		//Also check whether both objects are instances of the Employee Class
	
		
		// when
		boolean comparisonResult = employee1.equals(employee2);
		
	
		// then
		assertTrue(comparisonResult);
		
		//assertEquals(employee1, employee2);
		
		
	}
	
	

}
