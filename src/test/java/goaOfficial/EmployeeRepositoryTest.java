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

public class EmployeeRepositoryTest {

	private static EmployeeRepository employeeRepository;
	private static SessionFactory sessionFactory;
	private static Session session;

	@BeforeClass
	public static void init(){
		sessionFactory = HibernateUtils.getSessionFactory();
		employeeRepository = new EmployeeRepository(sessionFactory);
	}
	
	
	@Before
	public void setUp(){
		deleteEmployeeUtil();
	}
	
	@AfterClass
	public static void clean(){
		session.close();
		sessionFactory.close();
	}
	private void deleteEmployeeUtil() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "DELETE FROM Employe";
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		session.getTransaction().commit();
		session.clear();
	}


	
	
	@Test
	public void emptyRepositoryShouldReturnNothing() {
		List<Employe> employees = employeeRepository.findAll();
		assertEquals(0, employees.size());
	}

	@Test
	public void findAllEmployeeFromDB() {
		Employe emp = new Employe(1, "TEST", 570, "7276186835", true, "MALE", "SD");
		saveEmployeeUtil(emp);
		
		List<Employe> employees = employeeRepository.findAll();
		System.out.println(employees.toString());
		assertEquals(1, employees.size());
		assertEquals("TEST", (employees.get(0)).getEmpName());
	}

	@Test
	public void shouldSaveRecordIntoRepository() {

		Employe employee = new Employe(1, "TEST", 570, "7276186825", true, "MALE", "SD");
		employeeRepository.save(employee);

		assertEquals(1, employeeRepository.findAll().size());

	}

	@Test
	public void shouldDeleteAllRecordFromRepository() {
		employeeRepository.deleteAllRecords();
		assertEquals(0, employeeRepository.findAll().size());
	}

	@Test
	public void shouldDeleteSingleEmployee() {
		int companyEmpID = 570;
		employeeRepository.deleteRecord(companyEmpID);
		assertEquals(0, employeeRepository.findAll().size());
	}
	
	
	/**
	 * Test Utilities
	 * 
	 */
	private void saveEmployeeUtil(Employe e){
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		session.clear();
	}
	
	

}
