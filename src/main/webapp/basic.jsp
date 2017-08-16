					
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" href="styles.css"> -->
 <link rel="stylesheet" href="css/bootstrap.min.css"> 
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script src ="js/index.js"></script>

</head>

<body >

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

	<form id="employeeForm" class="form-horizontal" action="SaveRecord" method="post">
		<fieldset >
			<legend class="text-center col-sm-offset-2">Employee Information </legend>
			   
			       
				<div class="form-group">
					 <label class="control-label col-sm-4">Company Employee ID :</label>
					 <div class="col-sm-5">
						<input id="companyEmpID" name="companyEmpID" type="number" class="form-control" onblur="getDetailsFromInputField()" > 
					</div>
				</div>	
				
				<div class="form-group">
					<label class="control-label col-sm-4">Employee Name :</label>
					 <div class="col-sm-5">
						<input id="empName"	name="empName" type="text" class="form-control">
						</div>
				</div>
				
				<div class="form-group">
				 <label class="control-label col-sm-4 ">Contact No :</label>
				 <div class="col-sm-5">
					 <input id="empContactNo"name="empContactNo" type="text" class="form-control">
				</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-4">Department :</label> 
					<div class="col-sm-5">
						<select id="department" name="department" class="form-control" >
							<option value="SD"> Software Development</option>
							<option value="QA"> Quality Assurance </option>
							<option value="CARE"> CARE </option>
							<option value="ROA"> ROA </option>
							<option value="TechOps"> TechOps </option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
				<label class="control-label col-sm-4">Gender :</label>
						<div class="col-sm-5">
							<div class="radio-inline">
								<label><input type="radio" name="gender" value="MALE">Male</label>
							</div>
							<div class="radio-inline">
								<label><input type="radio" name="gender" value="FEMALE">Female</label>
							</div>
					</div>
				</div>
				
				
				
				<div  class="form-group">
					<div class="col-sm-offset-4 col-sm-10 ">
						<div class="checkbox">
							<label> <input type="checkbox" onclick ="PreferenceToggle()" id = "self" name="self" value="1">Coming to the picnic</label>
						</div>
					</div>
				</div>
			
				
				<div id = "Preferences" style="display:none">
				
				<div class="form-group" >
					<label class="control-label col-sm-4">Picnic Batch :</label>
					<div class="col-sm-5">
							<div class="radio-inline">
								<label><input type="radio" name="picnicBatch" value="FIRST_BATCH">First Batch </label>
							</div>
							<div class="radio-inline">
								<label><input type="radio" name="picnicBatch" value="SECOND_BATCH">Second Batch</label>
					 		</div>
					</div>
					</div>
				
				<div class="form-group" >
					<label class="control-label col-sm-4">Mode of Travel - Onward :</label>
					<div class="col-sm-5"> 
						<select name="modeOfTravelOnward" id="modeOfTravelOnward" class="form-control">
							<option value="BUS"> Bus</option>
							<option value="CAR"> Car</option>
							<option value="TRAIN"> Train </option>
							<option value="AEROPLANE"> Aeroplane</option>
						</select>
					</div>
					
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">Mode of Travel - Return :</label> 
					<div class="col-sm-5">
						<select name="modeOfTravelReturn" id="modeOfTravelReturn" class="form-control">
							<option value="BUS"> Bus</option>
							<option value="CAR"> Car</option>
							<option value="TRAIN"> Train </option>
							<option value="AEROPLANE"> Aeroplane</option>
						</select>
					</div>
				</div>
			
			<div class="form-group">
					<label class="control-label col-sm-4">Set Of Family :</label> 
					<div class="col-sm-5">
						<select name="setOfFamily" id="setOfFamily" class="form-control">
							<option value="EMPLOYEEE"> Employee</option>
							<option value="EMPLOYEE_DEPENDENT_PARENTS">Employee + Dependent Parents</option>
							<option value="EMPLOYEE_SPOUSE">Employee + Spouse</option>
							<option value="EMPLOYEE_SPOUSE_CHILDREN">Employee + Spouse + Children</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
				
					<label class="control-label col-sm-4">Total Family Count :</label> 
					<div class="col-sm-5">
						<input id="totalFamilyCount" name="totalFamilyCount" type="number" class="form-control"> <br>
					</div>
				</div>
			
			</div>	
			<input type="hidden" name="operation" value="submit">
			<div class="form-group">
			 	<div class="col-sm-offset-4 col-sm-10"> 
					<button name="submit"  class="btn btn-primary" type="submit"/>Submit </button>
				</div>
			</div>
		
		</fieldset>
	</form>
</div>
</body>
</html>

