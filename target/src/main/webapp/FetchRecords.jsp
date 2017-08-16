<%@page import="goaOfficial.Preference"%>
<%@page import="goaOfficial.PreferenceRepository"%>
<%@page import="goaOfficial.PreferenceService"%>
<%@page import="goaOfficial.Employe"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="goaOfficial.HibernateUtils"%>
<%@page import="goaOfficial.EmployeeRepository"%>
<%@page import="goaOfficial.EmployeeService"%>

<% SessionFactory sessionFactory = HibernateUtils.getSessionFactory();;
	EmployeeRepository employeeRepository = new EmployeeRepository(sessionFactory);
	EmployeeService employeeService = new EmployeeService(employeeRepository);
	PreferenceRepository preferenceRepository = new PreferenceRepository(sessionFactory); 
	PreferenceService preferenceService = new PreferenceService(preferenceRepository);
	%>
	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<title>Employee Data</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/records.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">IdeaS Picnic Portal</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">Go To 
      <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li> <a href="Welcome?action=AddRecord" class="btn btn-default">Add New Record</a></li>
        <li> <a href="Welcome?action=FetchRecords" class="btn btn-default" >Show Records</a> </li>
        </ul>
      </li>
      <li><a href="#">Page 2</a></li>
      <li><a href="#">Page 3</a></li>
    </ul>
  </div>
</nav>
  
<div class="container">


	<div style="margin: auto;">
		<h2 style="text-align: center">Entries</h2>
		<div class="table-responsive">
		<table class="table table-bordered" style="max-width:80%;margin:auto;">
			<thead class="thead-inverse" style="background-color:cyan;color:black;">
			<tr>
				<th>Employee ID</th>
				<th>Name</th>
				<th>Gender</th>
				<th>Contact Number</th>
				<th>Department</th>
				<th>Coming for Picnic?</th>
				<th>Picnic Batch</th>
				<th>Mode of Travel - Onward </th>
				<th>Mode of Travel - Return </th>
				<th>Set of Family </th>
				<th>Total Family Count </th>
				
			</tr>
			</thead>
			<tbody>
			<%
			
				List <Employe> employees = employeeService.getAllEmployees();
						for(Employe employee : employees){
							out.println("<tr>");
							out.println("<td>" + "<a href=\"basic.jsp?companyEmpID="+employee.getCompanyEmpID()
									+"&operation=fetch\" class=\"btn btn-sm btn-info\" role=\"button\" id=\"emp_" + employee.getCompanyEmpID()
									+ "\" title=\"View Preference\"> " + employee.getCompanyEmpID() + " </a>" + "</td>");
							out.println("<td>" + employee.getEmpName() + "</td>");
							out.println("<td>" + employee.getGender() + "</td>");
							out.println("<td>" + employee.getEmpContactNo() + "</td>");
							out.println("<td>" + employee.getDepartment() + "</td>");
							
							out.println("<td>" + (employee.getSelf() ? "Yes" : "No") + "</td>");
							if(employee.getSelf()){
								Preference preference = preferenceService.getPreferenceForEmployee(employee.getCompanyEmpID());
								if(preference != null){
									out.println("<td>" + preference.getPicnicBatch() + "</td>");
									out.println("<td>" + preference.getModeOfTravelOnward() + "</td>");
									out.println("<td>" + preference.getModeOfTravelReturn() + "</td>");
									out.println("<td>" + preference.getSetOfFamily() + "</td>");
									out.println("<td>" + preference.getTotalFamilyCount()+ "</td>");
									out.println("</tr>");
								}
								
							}
						}
					
			%>
			</tbody>
		</table>
		</div>
</div>
</body>
</html>