<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.Payment"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Payment.js"></script>
<meta charset="ISO-8859-1">


</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-6">

				<h1>Payment service Management</h1>

				<form id="formPayment" name="formPayment">			
					Payment Id :
					<input id="pid" name="pid" type="text" class="form-control form-control-sm"> 
					
					<br> patient Name :
					<input id="pname" name="pname" type="text" class="form-control form-control-sm">
					
					<br> Patient Address : 
					<input id="paddress" name="paddress" type="text" class="form-control form-control-sm"> 
					
					<br> Time : 
					<input id="time" name="time" type="time" class="form-control form-control-sm"> 
					
					<br> Date:
					<input id="date" name="date" type="date" class="form-control form-control-sm"> 
					
					<br> Amount:
					<input id="amount" name="amount" type="text" class="form-control form-control-sm"> 
					
					<br> Hospital Name:
					<input id="Hname" name="Hname" type="text" class="form-control form-control-sm"> 
					
					<br> 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> <input type="hidden"id="hidItemIDSave" name="hidItemIDSave" value="">
					
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>

				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divItemsGrid">
					<%
						Payment itemobj = new Payment();
						out.print(itemobj.viewPayment());
					%>
				</div>

			</div>
		</div>
	</div>


</body>
</html>