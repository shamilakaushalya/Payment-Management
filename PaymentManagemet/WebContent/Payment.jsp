<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Payment.js"></script>

<meta charset="ISO-8859-1">
<title>Payment Management</title>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Payment Management</h1>

	<form id="formItem" name="formItem">
		
		 User ID:
		<input id="userID" name="userID" type="text" class="form-control form-control-sm"><br> 
		 Card Name:
		<input id="cardname" name="cardname" type="text" class="form-control form-control-sm"><br>
		 Card Number:
		<input id="cardnumber" name="cardnumber" type="text" class="form-control form-control-sm"><br>		
		 Expire Date:
		<input id="expire_date" name="expire_date" type="text" class="form-control form-control-sm"><br>
		 CVV Number:
		<input id="cvv" name="cvv" type="text" class="form-control form-control-sm"><br>
		 
				
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemGrid">
	<%
		Payment PaymentObj = new Payment(); 
			 out.print(PaymentObj.readPayment());
	%>
	</div>
</div> </div> </div> 
	
</body>
</html>