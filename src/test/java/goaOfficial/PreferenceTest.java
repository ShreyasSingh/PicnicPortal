package goaOfficial;

import static org.junit.Assert.*;

import org.junit.Test;

public class PreferenceTest {
	
	@Test
	public void preferenceShouldNotBeNull(){
		
		Preference preference = new Preference();
		boolean comparisonResult = preference.equals(null);
		assertFalse(comparisonResult);
	}
	
	@Test
	public void preferenceShouldBeEqualToItself(){
		
		Employe employee = new Employe(1, "TEST", 570, "7276186835", true, "MALE", "SD");
		Preference preference = new Preference(1, employee, "FIRST_BATCH", "TRAIN", "TRAIN", "EMPLOYEE_DEPENDENT_PARENTS",3);
		boolean comparisonResult = preference.equals(preference);
		assertTrue(comparisonResult);
	}
	
	@Test
	public void preferencesShouldNotBeEqual(){
		Employe employee = new Employe(1, "TEST", 570, "7276186835", true, "MALE", "SD");
		Preference preference1 = new Preference(1, employee, "FIRST_BATCH", "TRAIN", "TRAIN", "EMPLOYEE_DEPENDENT_PARENTS",3);
		Preference preference2 = new Preference(2, employee, "FIRST_BATCH", "TRAIN", "TRAIN", "EMPLOYEE_DEPENDENT_PARENTS",3);
		
		boolean comparisonResult = preference2.equals(preference1);
		assertFalse(comparisonResult);		

	}
	
	@Test
	public void checkPreferencesAreEqual(){
		Employe employee = new Employe(1, "TEST", 570, "7276186835", true, "MALE", "SD");
		Preference preference1 = new Preference(1, employee, "FIRST_BATCH", "TRAIN", "TRAIN", "EMPLOYEE_DEPENDENT_PARENTS",3);
		Preference preference2 = new Preference(1, employee, "FIRST_BATCH", "TRAIN", "TRAIN", "EMPLOYEE_DEPENDENT_PARENTS",3);
		assertEquals(preference1, preference2);
	}

}
