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
		int companyID = employee.getCompanyEmpID();
		Employe emp2 = getEmployee(companyID);
		init();
		if (emp2 != null) {
			close();
		} else {
			session.save(employee);
			close();
		}
	}

	public void deleteAllRecords() {
		init();
		String hql = "DELETE FROM Employe";
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		close();

	}
	public void  updateEmployee(Employe employee){
		
		init();
	/*	int companyId = employee.getCompanyEmpID();
		String hql = "UPDATE Employe AS emp where where emp.companyEmpID = :company_employee_id";
		Query query = session.createQuery(hql);
		query.setParameter("company_employee_id", companyId);
		int result = query.executeUpdate();*/
		Employe mergedEmployee = (Employe) session.merge(employee);
		session.update(mergedEmployee);
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
		init();
		String hql = "Select emp FROM Employe emp " + "where emp.companyEmpID = :company_employee_id";
		Query query = session.createQuery(hql);
		query.setParameter("company_employee_id", empCompanyID);
		List<Employe> resultList = query.getResultList();
		if (!resultList.isEmpty())
			employee = resultList.get(0);
		
		close();
		return employee;

	}

	private void init() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}
	private void close() {
		session.getTransaction().commit();
		session.clear();
		session.close();
	}

	

}
