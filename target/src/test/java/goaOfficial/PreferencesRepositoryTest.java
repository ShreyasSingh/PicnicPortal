package goaOfficial;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PreferencesRepositoryTest {

	private static PreferenceRepository preferenceRepository;
	private static SessionFactory sessionFactory;
	private static Session session;
	static Employe employee;

	@BeforeClass
	public static void init() {
		sessionFactory = HibernateUtils.getSessionFactory();
		preferenceRepository = new PreferenceRepository(sessionFactory);
		int id = 1;
		cleanEmployeeUtil(id);
		employee = new Employe(1, "TEST", 570, "7276186835", true, "MALE", "SD");
		insertEmployeeUtil(employee);
	}
	

	@Before
	public void setup() {
		deleteAllPreferenceUtil();
	}

	


	@AfterClass
	public static void end() {
	
		int empID = 1;
		deleteAllPreferenceUtil();
		cleanEmployeeUtil(empID);
		//session.close();
		sessionFactory.close();

	}

	@Test
	public void emptyRepositoryShouldReturnNothing() {

		List<Preference> preferences = preferenceRepository.findAll();
		assertEquals(0, preferences.size());

	}

	@Test
	public void findAllPreferencesFromDB() {
		
		Preference preference = new Preference(1, employee, "FIRST_BATCH", "TRAIN", "TRAIN","EMPLOYEE_DEPENDENT_PARENTS", 3);
		savePreferenceUtil(preference);

		List<Preference> preferences = preferenceRepository.findAll();
		assertEquals(1, preferences.size());
	}

	@Test
	public void shouldSaveRecordIntoRepository() {

		Preference preference = new Preference(1, employee, "FIRST_BATCH", "TRAIN", "TRAIN","EMPLOYEE_DEPENDENT_PARENTS", 3);
		preferenceRepository.save(preference);
		assertEquals(1, preferenceRepository.findAll().size());

	}
	@Test
	public void shouldDeleteAllRecordsFromRepository() {

		preferenceRepository.deleteAll();
		assertEquals(0, preferenceRepository.findAll().size());
	}

	@Test
	public void shouldDeleteSingleRecordFromRepository() {
		Preference preference = new Preference(1, employee, "FIRST_BATCH", "TRAIN", "TRAIN","EMPLOYEE_DEPENDENT_PARENTS", 3);
		int ID = 1;
		preferenceRepository.delete(ID);
		assertEquals(0, preferenceRepository.findAll().size());
	}
	
	@Test
	public void getPreferenceFromEmployee(){
		Preference preference = new Preference(1, employee, "FIRST_BATCH", "TRAIN", "TRAIN","EMPLOYEE_DEPENDENT_PARENTS", 3);
		savePreferenceUtil(preference);
		int companyId = 570;
		Preference preferences = preferenceRepository.getPreferenceForEmployee(companyId);
		int id = preference.getEmploye().getCompanyEmpID();
		assertEquals(companyId, id );
	}

	/**
	 * Preference Test Utilities
	 */

	private void savePreferenceUtil(Preference preference) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(preference);

		session.getTransaction().commit();


	}

	private static void insertEmployeeUtil(Employe employee) {

		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(employee);

		session.getTransaction().commit();
		//session.clear();
	}


	private static void cleanEmployeeUtil(int id ) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "DELETE FROM Employe where empID = :empid";
		Query query = session.createQuery(hql);
		query.setParameter("empid", 1);
		query.executeUpdate();
		session.getTransaction().commit();
		//session.clear();
		
	}

	private static void deleteAllPreferenceUtil() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "DELETE FROM Preference";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		session.getTransaction().commit();
		//session.clear();
		
	}
}
