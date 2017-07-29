package goaOfficial;

public class Employee {
	
	
	private String empID;
	private String empName;
	private String companyEmpID;
	private String empContactNo;
	private String self;
	private String gender;
	private String department;
	

	public Employee(String empID, String empName, String companyEmpID, String empContactNo, String self, String gender,
			String department) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.companyEmpID = companyEmpID;
		this.empContactNo = empContactNo;
		this.self = self;
		this.gender = gender;
		this.department = department;
	}


	public Employee(String empName, String empID){
		this.empName = empName;
		this.empID = empID;
		
	}

	
	public Employee() {
		this.empID =  null;
		this.empName = null;
		// TODO Auto-generated constructor stub
	}


	


	public boolean equals(Object o1){
		
		if(o1 == null){
			return false;
		}
		if(!(o1 instanceof Employee)){
			return false;						
		}
		Employee e1 = (Employee) o1;
		
		if(this == e1)
			return true;
		
		if(this.empName == null || this.empID == null)
			return false;
		if(e1.empID == null || e1.empName == null)
			return false;
		if( this.empName.equals(e1.empName) && this.empID.equals(e1.empID))
			return true;
		return false;
	}


	public String getEmpID() {
		return empID;
	}


	public String getEmpName() {
		return empName;
	}


	public String getCompanyEmpID() {
		return companyEmpID;
	}


	public String getContact_no() {
		return empContactNo;
	}


	public String getSelf() {
		return self;
	}


	public String getGender() {
		return gender;
	}


	public String getDepartment() {
		return department;
	}
	

}
