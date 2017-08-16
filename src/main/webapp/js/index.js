/*
	$(document).ready(function(){
		$("#Preferences").hide();
		$('#self').click(function() {
			alert("Changed");
	   		if(this.checked) {
	       		$("#Preferences").show();
	    	} else {
	        	$("#Preferences").hide();
	    	}
		}); 
	});
 */

/*$(function (){
 PreferenceToggle = function(element){
 $(element).click(function (){
 if(this.checked) {
 $("#Preferences").show();
 } else {
 $("#Preferences").hide();
 }
 });
 }
 });*/

window.onload = getDetailsFromQueryString();

/**
 * Flow of html onload page - search dubstring if value is there ---> call ajax
 * caller else return
 * 
 * Normal Form filling onblur - fetchDetails is called
 * 
 * call ajax caller to fetch the details if present
 * 
 * 
 */

function getDetailsFromQueryString() {

	var GET = {};
	var query = window.location.search.substring(1).split("&");
	for (var i = 0, max = query.length; i < max; i++) {
		if (query[i] === "") // check for trailing & with no param
			continue;

		var param = query[i].split("=");
		GET[decodeURIComponent(param[0])] = decodeURIComponent(param[1] || "");

	}
	var companyID = GET.companyEmpID;
	if (companyID === undefined) {
		return;
	} else {
		FetchDetailsAJAXCaller(companyID);
	}
}

function getDetailsFromInputField() {
	
	var empID = document.getElementById("companyEmpID").value;
	if (empID === "undefined" || empID === "") {
		confirm('Please Enter Company Employee ID');
	} else {
		FetchDetailsAJAXCaller(empID);
	}
}

function FetchDetailsAJAXCaller(companyID) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var notFoundPattern = /NOT_FOUND/;
			var responseData = this.responseText;

			if (notFoundPattern.test(responseData)) {
				return;
			} else {
				debugger;
				var data = JSON.parse(responseData);
				var f = document.getElementById("employeeForm");
				
				// seperation between employee json and preference json
				if (data.self === false) {
					fillFromEmployeeJson(data, f);
				} else {
					fillFromPreferenceJson(data, f);
				}

			}
		}
	};

	xhttp.open("GET", 'SaveRecord?companyEmpID=' + companyID
			+ "&operation=fetch", true);
	xhttp.send();

}

function fillFromEmployeeJson(data, form) {
	form["companyEmpID"].value = data.companyEmpID;
	form["empName"].value = data.empName;
	form["empContactNo"].value = data.empContactNo;
	form["department"].value = data.department;
	form["gender"].value = data.gender;

}

function fillFromPreferenceJson(data, form) {

	form["companyEmpID"].value = data.employee.companyEmpID;
	form["empName"].value = data.employee.empName;
	form["empContactNo"].value = data.employee.empContactNo;
	form["department"].value = data.employee.department;
	form["gender"].value = data.employee.gender;
	form["self"].click();
	PreferenceToggle();
	form["picnicBatch"].value = data.picnicBatch;
	form["modeOfTravelOnward"].value = data.modeOfTravelOnward;
	form["modeOfTravelReturn"].value = data.modeOfTravelReturn;
	form["setOfFamily"].value = data.setOfFamily;
	form["totalFamilyCount"].value = data.totalFamilyCount;

}
function PreferenceToggle(value) {

	value = !value;
	var checkboxValue = document.getElementById("self").checked;

	if (checkboxValue && value) {
		document.getElementById("Preferences").style.display = "block";
	} else {
		document.getElementById("Preferences").style.display = "none";
	}

}
