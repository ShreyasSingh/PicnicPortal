package goaOfficial;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class SaveRecord
 */
public class SaveRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection = null;
	private static EmployeeRepository employeeRepository;
	private static EmployeeService employeeService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
		employeeRepository = new EmployeeRepository(connection);
		employeeService = new EmployeeService(employeeRepository);

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation");
		String empCompanyID = request.getParameter("companyEmpID");
		PrintWriter out = response.getWriter();
		String employeeJson = null;
		if (operation != null) {
			employeeJson = fetchEmployeeDetails(empCompanyID);
			out.println(employeeJson);

		} else {
			saveRecord(request, response);
			out.println("Data Successfully saved!");
		}

		// out.println("{\"username\" : \"Shreyas Singh\" , \"employeeid\":23,
		// \"department\""
		// + ":\"SD\", \"contact\":\"54356656\", " + "\"gender\":\"male\",
		// \"self\":\"yes\"}");	
		}

		private void saveRecord(HttpServletRequest request, HttpServletResponse response) {
			Employee employee = new Employee();
			employee.setCompanyEmpID(request.getParameter("companyEmpID"));
			employee.setEmpName(request.getParameter("empName"));
			employee.setEmpContactNo(request.getParameter("empContactNo"));
			employee.setDepartment(request.getParameter("department"));
			employee.setGender(request.getParameter("gender"));
			employee.setSelf(request.getParameter("self").equals("1")?"1":"0");
			employeeService.saveEmployee(employee);
		}

		private String fetchEmployeeDetails(String empCompanyID) {
			Gson gson = new Gson();
			Employee employee = employeeService.getEmployee(empCompanyID);
			String employeejson = gson.toJson(employee);
			return employeejson;
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
