package goaOfficial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@javax.persistence.NamedQueries({
	@NamedQuery(name = "FIND_BY_NAME", query = "select emp1 from Employe emp1 "
			+ "where emp1.empName= :empName")
})

@Entity
@Table(name="employee")
public class Employe {


	private Integer empID;
	private String empName;
	private Integer companyEmpID;
	private String empContactNo;
	private Boolean self;
	private String gender;
	private String department;
	
	@Id
	@Column(name= "Employee_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer getEmpID() {
		return empID;
	}
	
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}
	
	@Column(name="NAME")
	public String getEmpName() {
		return empName;
	}
	
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@Column(name="Company_Employee_ID")
	public Integer getCompanyEmpID() {
		return companyEmpID;
	}
	
	public void setCompanyEmpID(Integer companyEmpID) {
		this.companyEmpID = companyEmpID;
	}
	
	@Column(name="Contact_No")
	public String getEmpContactNo() {
		return empContactNo;
	}
	
	public void setEmpContactNo(String empContactNo) {
		this.empContactNo = empContactNo;
	}
	
	@Column(name="Self")
	public Boolean getSelf() {
		return self;
	}
	public Employe(){}
	
	public Employe(Integer empID, String empName, Integer companyEmpID, String empContactNo, Boolean self,
			String gender, String department) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.companyEmpID = companyEmpID;
		this.empContactNo = empContactNo;
		this.self = self;
		this.gender = gender;
		this.department = department;
	}

	public void setSelf(boolean b) {
		this.self = b;
	}
	
	@Column(name="Gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name="Department")
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return this.empID + " " + this.empName + " " +this.companyEmpID + " " +this.empContactNo + " " 
			+this.gender + " " +this.department ;
	}
	
	
}
