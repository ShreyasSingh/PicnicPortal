package goaOfficial;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "preferences")
public class Preference {

	private Integer ID;
	private Employe employee;
	private String picnicBatch;
	private String modeOfTravelReturn;
	private String modeOfTravelOnward;
	private String setOfFamily;
	private Integer totalFamilyCount;

	
	public  Preference() {}

	public Preference(int iD , Employe employee, String picnicBatch , String modeOfTravelOnward ,
			String modeOfTravelReturn , String setOfFamily , int totalFamilyCount ) {
		this.ID = iD;
		this.employee = employee;
		this.picnicBatch = picnicBatch;
		this.modeOfTravelReturn = modeOfTravelReturn;
		this.modeOfTravelOnward = modeOfTravelOnward;
		this.setOfFamily = setOfFamily;
		this.totalFamilyCount = totalFamilyCount;	
	}
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Employee_ID")
	public Employe getEmploye() {
		return employee;
	}
	public void setEmploye(Employe employe) {
		this.employee = employe;
	}
	public void setID(Integer iD) {
		ID = iD;
	}

	
	public void setPicnicBatch(String picnicBatch) {
		this.picnicBatch = picnicBatch;
	}

	public void setModeOfTravelReturn(String modeOfTravelReturn) {
		this.modeOfTravelReturn = modeOfTravelReturn;
	}

	public void setModeOfTravelOnward(String modeOfTravelOnward) {
		this.modeOfTravelOnward = modeOfTravelOnward;
	}

	public void setSetOfFamily(String setOfFamily) {
		this.setOfFamily = setOfFamily;
	}

	public void setTotalFamilyCount(Integer totalFamilyCount) {
		this.totalFamilyCount = totalFamilyCount;
	}

	@Column(name = "picnicBatch")
	public String getPicnicBatch() {
		return picnicBatch;
	}

	@Column(name = "modeOfTravel_return")
	public String getModeOfTravelReturn() {
		return modeOfTravelReturn;
	}

	@Column(name = "modeOfTravel_onward")
	public String getModeOfTravelOnward() {
		return modeOfTravelOnward;
	}

	@Column(name = "setOfFamily")
	public String getSetOfFamily() {
		return setOfFamily;
	}

	@Column(name = "totalFamilyCount")
	public Integer getTotalFamilyCount() {
		return totalFamilyCount;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == null || obj == null)
			return false;
		if (!((this instanceof Preference) && (obj instanceof Preference)))
			return false;
		Preference pref1 = (Preference) obj;
		if (this == pref1)
			return true;
		if (this.ID.equals(null) || this.employee.equals(null) || this.picnicBatch.equals(null)
				|| this.modeOfTravelOnward.equals(null) || this.modeOfTravelReturn.equals(null)
				|| this.setOfFamily.equals(null) || this.totalFamilyCount.equals(null))
			return false;
		if (pref1.ID.equals(null) || pref1.employee.equals(null) || pref1.picnicBatch.equals(null)
				|| pref1.modeOfTravelOnward.equals(null) || pref1.modeOfTravelReturn.equals(null)
				|| pref1.setOfFamily.equals(null) || pref1.totalFamilyCount.equals(null))
			return false;
		if (this.ID.equals(pref1.ID) && this.employee.equals(pref1.employee) && this.picnicBatch.equals(pref1.picnicBatch)
				&& this.modeOfTravelOnward.equals(pref1.modeOfTravelOnward)
				&& this.modeOfTravelReturn.equals(pref1.modeOfTravelReturn)
				&& this.setOfFamily.equals(pref1.setOfFamily) && this.totalFamilyCount.equals(pref1.totalFamilyCount))
			return true;

		return false;
	}

}
