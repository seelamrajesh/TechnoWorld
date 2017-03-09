<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="a"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Supplier</title>
<link rel="stylesheet" href="css/supplier.css" />
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script>
var sup_list=${sup_list};

angular.module('supplierModule',[]).controller('supplierController',function($scope){
	
	$scope.supplier=sup_list;
	        
});


</script>
<style>
#supplier{
margin-top: 60px;
	padding: 20px;
	box-shadow: 5px 5px 15px #000;
}
#sup_search{
margin-top: 60px;
padding: 20px;
}
#sup_list{
margin-top: 15px;

box-shadow: 5px 5px 15px #000;

}
#search_sup{
position:absolute;

margin-top: 75px;
margin-left:699px;



}



.panel-heading {
padding:5px;
}
#del {
    position: relative;
    top: -15px;
    width:10%;
    
} 
#edit{
    position: relative;
    top: -15px;
    width:10%;
    right:20px;
} 

.panel-title{
    position: relative;

 top:10px;
 left:5px;
}

</style>

</head>
<body ng-app="supplierModule" ng-controller="supplierController">

	<div class="container">
	<c:if test="${save==true}">
		
		
		<div class="col-sm-3  text-center " id="supplier">
		
		
			<h1>Supplier</h1>
			${message}
			<a:form action="supplierrequestpost" method="post"
				modelAttribute="sup"
				onsubmit="this.submit(); this.reset(); return false;">
				<a:input path="sup_id" placeholder="Supplier Id"
					class="form-control inputone" />
				<a:input path="sup_name" placeholder="Supplier Name"
					class="form-control inputone" />
				<a:input path="sup_address" placeholder="Supplier Address"
					class="form-control  inputtwo" />
				<a:input path="sup_number" placeholder="Supplier Number"
					class="form-control inputone" />
				<a:input path="sup_email" placeholder="Supplier Email"
					class="form-control inputone" />
				<a:button type="submit" class="btn btn-success btn-block button">Submit</a:button>
				<a class="btn btn-warning btn-block" href="supplierrequest">Clear</a>
			</a:form>
		</div> <!-- col-sm -->
		</c:if><!-- save -->
			<c:if test="${update==true}">
		<div class="col-sm-3  text-center " id="supplier">
			<h1>Supplier</h1>
			${message}
			<a:form action="requestpostupdate" method="post"
				modelAttribute="sup"
				onsubmit="this.submit(); this.reset(); return false;">
				<a:input path="sup_id" placeholder="Supplier Id"
					class="form-control inputone" />
				<a:input path="sup_name" placeholder="Supplier Name"
					class="form-control inputone" />
				<a:input path="sup_address" placeholder="Supplier Address"
					class="form-control  inputtwo" />
				<a:input path="sup_number" placeholder="Supplier Number"
					class="form-control inputone" />
				<a:input path="sup_email" placeholder="Supplier Email"
					class="form-control inputone" />
				<a:button type="submit" class="btn btn-success btn-block button">Update</a:button>
				<a class="btn btn-warning btn-block" href="supplierrequest">Clear</a>
			</a:form>
		</div> <!-- col-sm -->
		</c:if><!-- update -->
		<div class="col-sm-8 col-sm-offset-1" >
		<div  id="search_sup">
		<b class="glyphicon glyphicon-search"></b>
		</div>
		
		<input type="text" placeholder="Search Supplier" class="form-control" id="sup_search" ng-model="searchsup"/>
		<div class="row_sup" ng-repeat="sup in supplier|filter:searchsup" id="sup_list" >
		  <div class="panel-group" id="accordion">
     <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title" >
        <a data-toggle="collapse" data-parent="#accordion" href="#{{sup.sup_id}}"><b>{{sup.sup_name}}</b></a>
        
   </h4>
     
   
   <a class="btn btn-danger pull-right" id="del" href="deletesup?sid={{sup.sup_id}}">Delete</a>&nbsp;
    <a class="btn btn-primary pull-right" id="edit" href="updatesup?sid={{sup.sup_id}}">Edit</a>
   
  
   </div>
   <div id={{sup.sup_id}} class="panel-collapse collapse ">
    <div class="panel-body">
    
    <ul class="list-group">
  <li class="list-group-item"><span><b>Supplier Id:</b></span>&nbsp;&nbsp;{{sup.sup_id}}</li>
  <li class="list-group-item"><b>Supplier Name:</b>&nbsp;&nbsp;{{sup.sup_name}}</li>
  <li class="list-group-item"><b>Supplier Address:</b>&nbsp;&nbsp;{{sup.sup_address}}</li>
    <li class="list-group-item"><b>Supplier Number:</b>&nbsp;&nbsp;{{sup.sup_number}}</li>
  
    <li class="list-group-item"><b>Supplier Email:</b>&nbsp;&nbsp;{{sup.sup_email}}</li>
  
  
</ul>
   </div>
   </div>
   </div>
   </div>
  </div>
  

		</div> <!-- col-sm -->
		
	</div> <!-- container -->
</body>
</html>