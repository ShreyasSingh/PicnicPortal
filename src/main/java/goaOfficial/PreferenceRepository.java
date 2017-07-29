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
		init();
		session.save(preference);
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
	
	private void close() {
		session.getTransaction().commit();
		session.clear();
	}

	private void init() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

}
