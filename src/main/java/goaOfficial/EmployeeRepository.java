package goaOfficial;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class EmployeeRepository {

	private static SessionFactory sessionFactory;
	private static Session session;

	public EmployeeRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Employe> findAll() {
		init();
		String hql = "FROM Employe";
		Query query = session.createQuery(hql);
		List employees = query.list();
		close();
		return employees;

	}

	public void save(Employe employee) {
		init();
		session.save(employee);
		close();
	}

	public void deleteAllRecords() {
		init();
		String hql = "DELETE FROM Employe";
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		close();

	}

	public void deleteRecord(int empID) {
		init();
		String hql = "DELETE FROM Employe where companyEmpID = :company_employee_id";
		Query query = session.createQuery(hql);
		query.setParameter("company_employee_id", empID);
		int result = query.executeUpdate();
		close();

	}

	public Employe getEmployee(int empCompanyID) {
		Employe employee = null;
		String hql = "FROM Employe emp " + 
					"where emp1.companyEmpID= :company_employee_id";
		Query query = session.createQuery(hql);
		query.setParameter("company_employee_id", empCompanyID);

		return employee;

	}

	private void close() {
		session.getTransaction().commit();
		session.clear();
	}

	private void init() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

}
