package goaOfficial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class PreferenceRepository {

	private SessionFactory sessionFactory;
	private Session session;

	public PreferenceRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	public List<Preference> findAll() {

		init();
		String hql = "FROM Preference";
		Query query = session.createQuery(hql);
		List preferences = query.list();
		close();
		return preferences;
	}

	public void save(Preference preference) {
		int companyID = preference.getEmploye().getCompanyEmpID();
		Preference pref2 = getPreferenceForEmployee(companyID);
		init();
		if (pref2 != null) {
			close();
		} else {
			session.save(preference);
			close();
		}

	}
	
	public void updatePreference(Preference preference){
		init();
		Preference mergedPerson = (Preference) session.merge(preference);
		session.update(mergedPerson);
		/*int companyId = preference.getEmploye().getCompanyEmpID();
		String hql = "UPDATE Preference AS pref where where pref.employe.companyEmpID = :company_employee_id";
		Query query = session.createQuery(hql);
		query.setParameter("company_employee_id", companyId);
		int result = query.executeUpdate();*/
		close();
	}

	public void deleteAll() {
		init();
		String hql = "DELETE FROM Preference";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		close();
	}

	public void delete(int ID) {
		init();
		String hql = "DELETE FROM Preference where ID = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", ID);
		query.executeUpdate();
		close();
	}

	public Preference getPreferenceForEmployee(int companyId) {
		init();
		Preference preference = null;
		String hql = "Select p FROM Preference p  where p.employe.companyEmpID = :company_employee_id";
		Query query = session.createQuery(hql);
		query.setParameter("company_employee_id", companyId);
		List<Preference> resultList = query.getResultList();
		if (!resultList.isEmpty())
			preference = resultList.get(0);
		
		close();
		return preference;
	}

	private void close() {
		session.getTransaction().commit();
		session.clear();
		session.close();

	}

	private void init() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

}
