<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shipping Address</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
<link rel="stylesheet" href="css/category.css"/>

<style >
#ship{
margin-top: 100px;
	padding: 40px;
	box-shadow: 5px 5px 15px #000;
}

</style>
</head>
<body>
<div class="container">
<div class="col-sm-4 col-sm-offset-4 text-center " id="ship">
			<h1>Shipping Address</h1>
			${message}
			<f:form  method="post" 	modelAttribute="shipping">

				
				<f:input path="street" placeholder="Street"
					class="form-control inputone" />
				<f:input path="city" placeholder="City"
					class="form-control  inputtwo" />
				<f:input path="state" placeholder="state"
					class="form-control inputone" />
			    <f:input path="country" placeholder="Country"
					class="form-control inputone" />
					
				<f:input path="zipcode" placeholder="Zipcode"
					class="form-control inputone" />
					<c:set var="pro_id" value="${product}"></c:set>
				
				
				
				<f:button type="submit" name="_eventId_submitShipping" class="btn btn-success btn-block button">Submit</f:button>
			   <f:button type="submit" name="_eventId_cancel" class="btn btn-danger btn-block" style="margin-top:10px;">Cancel</f:button>
				
			</f:form>
			
		</div>
		<!-- col-sm -->
		</div>
</body>
</html>