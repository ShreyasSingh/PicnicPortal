package goaOfficial;

import java.util.List;

public class EmployeeService {

	private EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
			this.employeeRepository = employeeRepository;
	}

	public List<Employe> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public void deleteAllEmployees() {
		employeeRepository.deleteAllRecords();
	}

	public void  saveEmployee(Employe employee) {
		employeeRepository.save(employee);
	}

	public void deleteSingleEmployee(int empID) {
		employeeRepository.deleteRecord(empID);
	}

	public Employe getEmployee(int empCompanyID) {
		Employe employee = employeeRepository.getEmployee(empCompanyID);
		return employee;
	}

}
