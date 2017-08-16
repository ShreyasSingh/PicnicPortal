package goaOfficial;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.internal.expression.BinaryArithmeticOperation.Operation;

import com.google.gson.Gson;

/**
 * Servlet implementation class SaveRecord
 */
public class SaveRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SessionFactory sessionFactory;
	private static Session session;
	private static EmployeeRepository employeeRepository;
	private static EmployeeService employeeService;
	private static PreferenceService preferenceService;
	private static PreferenceRepository preferenceRepository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		sessionFactory = HibernateUtils.getSessionFactory();
		employeeRepository = new EmployeeRepository(sessionFactory);
		employeeService = new EmployeeService(employeeRepository);
		preferenceRepository = new PreferenceRepository(sessionFactory);
		preferenceService = new PreferenceService(preferenceRepository);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// check parameter receieved
		String responseString = null;
		PrintWriter out = response.getWriter();
		String operation = request.getParameter("operation");
		String empCompanyID = request.getParameter("companyEmpID");
		if (empCompanyID == "" || empCompanyID == null) {
			return;
		} else {
			int companyID = Integer.parseInt(empCompanyID);
			String recordJson = null;
			if (operation.equalsIgnoreCase("fetch")) {
				System.out.println("Fetching Data ");
				recordJson = fetchRecord(companyID);
				out.println(recordJson);

			} else if (operation.equalsIgnoreCase("submit")) {
				// Employe employee = createEmployee(request);
				responseString = saveORUpdateRecord(request, companyID);
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				System.out.println(responseString);
			}
		}

	}

	public String saveORUpdateRecord(HttpServletRequest request, int companyID) {
		Employe employee = employeeService.getEmployee(companyID);
		String responseString = null;
		if (employee == null) {
			
			System.out.println("Saving new Record");
			responseString = saveRecord(request, companyID);
			
		} else {
			
			System.out.println("Updating Record");
			responseString = updateRecord(request, employee);
			
		}

		return responseString;

	}

	private String updateRecord(HttpServletRequest request, Employe employee) {
		String responseString = "<RECORD_UPDATED>";
		String self = request.getParameter("self");
		
		System.out.println("Updating Employee");
		
		employee.setCompanyEmpID(Integer.parseInt(request.getParameter("companyEmpID")));
		employee.setEmpName(request.getParameter("empName"));
		employee.setDepartment(request.getParameter("department"));
		employee.setEmpContactNo(request.getParameter("empContactNo"));
		employee.setGender(request.getParameter("gender"));
		employee.setSelf((self != null) ? true : false);
		
		employeeService.updateEmployee(employee);
		
		int companyID = employee.getCompanyEmpID();
		Preference preference = preferenceService.getPreferenceForEmployee(companyID);

		if (employee.getSelf() && preference != null) {
			
			System.out.println("Updating Preference");
			preference.setEmploye(employee);
			preference.setPicnicBatch(request.getParameter("picnicBatch"));
			preference.setModeOfTravelOnward(request.getParameter("modeOfTravelOnward"));
			preference.setModeOfTravelReturn(request.getParameter("modeOfTravelReturn"));
			preference.setSetOfFamily(request.getParameter("setOfFamily"));
			preference.setTotalFamilyCount(Integer.parseInt(request.getParameter("totalFamilyCount")));
			
			preferenceService.updatePreference(preference);

		} else if (employee.getSelf() && preference == null) {
			System.out.println("Saving Preference");
			preference = new Preference();
			preference.setEmploye(employee);
			preference.setPicnicBatch(request.getParameter("picnicBatch"));
			preference.setModeOfTravelOnward(request.getParameter("modeOfTravelOnward"));
			preference.setModeOfTravelReturn(request.getParameter("modeOfTravelReturn"));
			preference.setSetOfFamily(request.getParameter("setOfFamily"));
			preference.setTotalFamilyCount(Integer.parseInt(request.getParameter("totalFamilyCount")));
			
			preferenceService.savePreference(preference);

		}

		else if (!employee.getSelf() && preference != null) {
			System.out.println("Deleting Preference");
			
			preferenceService.deleteSinglePreference(preference.getEmploye().getCompanyEmpID());
		}

		return responseString;
	}

	private String saveRecord(HttpServletRequest request, int companyID) {
		String responsString = "<RECORD_SAVED>";
		Employe employee = new Employe();
		employee.setCompanyEmpID(Integer.parseInt(request.getParameter("companyEmpID")));
		employee.setEmpName(request.getParameter("empName"));
		employee.setDepartment(request.getParameter("department"));
		employee.setEmpContactNo(request.getParameter("empContactNo"));
		employee.setGender(request.getParameter("gender"));
		String self = request.getParameter("self");
		employee.setSelf((self != null) ? true : false);
		employeeService.saveEmployee(employee);
		if (employee.getSelf()) {
			System.out.println("Saving Preference");
			Preference preference = new Preference();
			preference.setEmploye(employee);
			preference.setPicnicBatch(request.getParameter("picnicBatch"));
			preference.setModeOfTravelOnward(request.getParameter("modeOfTravelOnward"));
			preference.setModeOfTravelReturn(request.getParameter("modeOfTravelReturn"));
			preference.setSetOfFamily(request.getParameter("setOfFamily"));
			preference.setTotalFamilyCount(Integer.parseInt(request.getParameter("totalFamilyCount")));
			preferenceService.savePreference(preference);
		}
		return responsString;
	}

	/*private void saveOrUpdateRecord(HttpServletRequest request, int empCompanyID) {
		String self = request.getParameter("self");
		if (employee != null) {
			System.out.println("Updating Employee");
			// updateRecordOfEmployee(DBEmployee, employee);
			employee.setCompanyEmpID(Integer.parseInt(request.getParameter("companyEmpID")));
			employee.setEmpName(request.getParameter("empName"));
			employee.setDepartment(request.getParameter("department"));
			employee.setEmpContactNo(request.getParameter("empContactNo"));
			employee.setGender(request.getParameter("gender"));
			employee.setSelf((self != null) ? true : false);
			employeeService.updateEmployee(employee);
		} else {
			System.out.println("Saving Employee");
			employee = new Employe();
			employee.setCompanyEmpID(Integer.parseInt(request.getParameter("companyEmpID")));
			employee.setEmpName(request.getParameter("empName"));
			employee.setDepartment(request.getParameter("department"));
			employee.setEmpContactNo(request.getParameter("empContactNo"));
			employee.setGender(request.getParameter("gender"));

			employee.setSelf((self != null) ? true : false);
			employeeService.saveEmployee(employee);
		}
		int companyID = employee.getCompanyEmpID();
		Preference preference = preferenceService.getPreferenceForEmployee(companyID);

		if (employee.getSelf() && preference != null) {
			
			 * Preference preference = createPreference(request, employee);
			 * saveOrUpdatePreference(preference,
			 * employeeService.getEmployee(employee.getCompanyEmpID()));
			 
			System.out.println("Updating Preference");
			preference.setEmploye(employee);
			preference.setPicnicBatch(request.getParameter("picnicBatch"));
			preference.setModeOfTravelOnward(request.getParameter("modeOfTravelOnward"));
			preference.setModeOfTravelReturn(request.getParameter("modeOfTravelReturn"));
			preference.setSetOfFamily(request.getParameter("setOfFamily"));
			preference.setTotalFamilyCount(Integer.parseInt(request.getParameter("totalFamilyCount")));
			preferenceService.updatePreference(preference);

		} else if (employee.getSelf() && preference == null) {
			System.out.println("Saving Preference");
			preference = new Preference();
			preference.setEmploye(employee);
			preference.setPicnicBatch(request.getParameter("picnicBatch"));
			preference.setModeOfTravelOnward(request.getParameter("modeOfTravelOnward"));
			preference.setModeOfTravelReturn(request.getParameter("modeOfTravelReturn"));
			preference.setSetOfFamily(request.getParameter("setOfFamily"));
			preference.setTotalFamilyCount(Integer.parseInt(request.getParameter("totalFamilyCount")));
			preferenceService.savePreference(preference);

		}

		else if (!employee.getSelf() && preference != null) {
			System.out.println("Deleting Preference");
			preferenceService.deleteSinglePreference(preference.getEmploye().getCompanyEmpID());
		}

	}
*/
	private String fetchRecord(int empCompanyID) {
		Gson gson = new Gson();
		System.out.println(empCompanyID);
		String responseString = "NOT_FOUND";
		// breaking into 2 parts
		// employee exists -- preference does not
		// employee and preference both exists

		Employe employee = employeeService.getEmployee(empCompanyID);
		if (employee == null) {
			System.out.println(responseString);
			return responseString;
		}
		if (!employee.getSelf()) {
			responseString = gson.toJson(employee);
			System.out.println(responseString);
			return responseString;
		} else {
			Preference preference = preferenceService.getPreferenceForEmployee(empCompanyID);
			if (preference == null) {
				return responseString;
			}
			responseString = gson.toJson(preference);
		}
		System.out.println(responseString);
		return responseString;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
