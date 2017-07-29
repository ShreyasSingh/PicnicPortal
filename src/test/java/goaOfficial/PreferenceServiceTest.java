package goaOfficial;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PreferenceServiceTest {
	
	private PreferenceRepository preferenceRepository;
	private PreferenceService preferenceService;

	@Before
	public void setUp(){
		 preferenceRepository = Mockito.mock(PreferenceRepository.class);
		 preferenceService = new PreferenceService(preferenceRepository);
		
	}

	@Test
	public void shouldGetAllPreferences(){
		
		Mockito.when(preferenceRepository.findAll()).thenReturn(Arrays.asList(new Preference()));
		
		List<Preference> preferences = preferenceService.getAllPreference();
		assertEquals(1, preferences .size());
	}
	
	@Test
	public void shouldSavePreference(){
		Employe employee = new Employe(1, "TEST", 570, "7276186835", true, "MALE", "SD");
		Preference preference = new Preference(1, employee, "FIRST_BATCH", "TRAIN", "TRAIN","EMPLOYEE_DEPENDENT_PARENTS", 3);
		preferenceService .savePreference(preference);
		Mockito.verify(preferenceRepository).save(preference);
	}
	
	@Test
	public void shouldDeleteAllPreferences(){
		
		
		preferenceService .deleteAllPreferences();
		Mockito.verify(preferenceRepository).deleteAll();
	}
	
	@Test
	public void shouldDeleteSinglePreference(){
		
		int iD = 1;
		preferenceService.deleteSinglePreference(iD);
		Mockito.verify(preferenceRepository).delete(iD );
	}
}
