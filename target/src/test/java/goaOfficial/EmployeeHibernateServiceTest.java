package goaOfficial;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeHibernateServiceTest {

	private static SessionFactory sessionFactory;
	private static Session session;

	@BeforeClass
	public static void init() {
		sessionFactory = HibernateUtils.getSessionFactory();
	}

	@Before
	public void setup() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	@After
	public void clean() {
		session.getTransaction().commit();
		
	}
	
	@AfterClass
	public static void end(){
		session.close();
	}

	@Test
	public void shouldCreateSessionFactory() {
		assertNotNull(sessionFactory);
	}

	@Test
	public void shouldSaveEmployee() {
		Employe employee = createEmployeeUtil("Shreyas");
		session.save(employee);
	}

	@Test
	public void shouldSaveListOfEmployees() {

		List<Employe> empList = new ArrayList<Employe>();
		Employe emp1 = createEmployeeUtil("Shreyas");
		Employe emp2 = createEmployeeUtil("Aditya");

		empList.add(emp1);
		empList.add(emp2);

		for (Employe e : empList) {
			session.save(e);
			session.flush();
		}

	}

	@Test
	public void updateEmployee() {
		Employe emp = session.find(Employe.class, 7);
		assertEquals("Shreyas", emp.getEmpName());
		emp.setEmpName(emp.getEmpName() + "@Ideas");
		session.save(emp);
		emp = session.find(Employe.class, 7);
		assertEquals("Shreyas@Ideas", emp.getEmpName());
	}

	@Test
	public void deleteEmployee() {
		Employe emp = session.find(Employe.class, 29);
		session.delete(emp);
		emp = session.find(Employe.class, 28);
		assertNull(emp);
	}

	@Test
	public void shouldFindByName() {
		Query findEmployeeQuery = session.createNamedQuery("FIND_BY_NAME");
		findEmployeeQuery.setParameter("empName", "TEST");
		List<Employe> resultList = findEmployeeQuery.getResultList();
		Employe emp = resultList.get(0);
		assertEquals("TEST", emp.getEmpName());

	}
	
	

	/**
	 * Test Utilities
	 * 
	 */

	private Employe createEmployeeUtil(String empName) {

		return new Employe(1, empName, 570, "992983580", true, "MALE", "SD");

	}

}
