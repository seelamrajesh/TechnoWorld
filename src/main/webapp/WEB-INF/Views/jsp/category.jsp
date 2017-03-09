<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Category</title>
<link rel="stylesheet" href="css/category.css" />

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script>
var cate_list=${cate_list};

angular.module('categoryModule',[]).controller('categoryController',function($scope){
	
	$scope.category=cate_list;
	        
});


</script>
<style >
#cat{
margin-top: 60px;
	padding: 20px;
	box-shadow: 5px 5px 15px #000;
}
#cat_list{
margin-top: 20px;
	box-shadow: 5px 5px 15px #000;
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
#cate_search{
margin-top: 60px;
padding: 20px;
}
#search_cate{
position:absolute;

margin-top: 75px;
margin-left:699px;



}
</style>
</head>
<body  ng-app="categoryModule" ng-controller="categoryController">
	<div class="container">
	<c:if test="${savecate==true}">
		<div class="col-sm-3 text-center " id="cat">
			<h1>Category</h1>
			${message}
			<f:form action="categoryrequestpost" method="post"
				modelAttribute="cate">

				<f:input path="cate_id" placeholder="Category Id"
					class="form-control inputone" />
				<f:input path="cate_name" placeholder="Category Name"
					class="form-control inputone" />
				<f:input path="cate_desc" placeholder="Category Description"
					class="form-control  inputtwo" />
				<f:button type="submit" class="btn btn-success btn-block button">Save</f:button>
				<a class="btn btn-warning btn-block clearbutton"
					href="categoryrequest">Clear</a>
			</f:form>
			
		</div>
		<!-- col-sm -->
		</c:if> <!-- save -->
		<c:if test="${updatecate==true}">
		<div class="col-sm-3 text-center " id="cat">
			<h1>Category</h1>
			${message}
			<f:form action="categoryupdatepost" method="post"
				modelAttribute="cate">

				<f:input path="cate_id" placeholder="Category Id"
					class="form-control inputone" />
				<f:input path="cate_name" placeholder="Category Name"
					class="form-control inputone" />
				<f:input path="cate_desc" placeholder="Category Description"
					class="form-control  inputtwo" />
				<f:button type="submit" class="btn btn-success btn-block button">Update</f:button>
				<a class="btn btn-warning btn-block clearbutton"
					href="categoryrequest">Clear</a>
			</f:form>
			
		</div>
		<!-- col-sm -->
		</c:if> <!-- update -->
		<div class="col-sm-8 col-sm-offset-1">
		<div  id="search_cate">
		<b class="glyphicon glyphicon-search"></b>
		</div>
		
		<input type="text" placeholder="Search Supplier" class="form-control" id="cate_search" ng-model="searchcate"/>
		<div class="row_col" ng-repeat="cate in category|filter:searchcate" id="cat_list" >
		  <div class="panel-group" id="accordion">
     <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title" >
        <a  data-toggle="collapse" data-parent="#accordion" href="#{{cate.cate_id}}"><b>{{cate.cate_name}}</b></a>
        
   </h4>
     
   
   <a class="btn btn-danger pull-right" id="del" href="deletecate?cid={{cate.cate_id}}">Delete</a>&nbsp;
    <a class="btn btn-primary pull-right" id="edit"  href="updatecate?cid={{cate.cate_id}}" >Edit</a>
   
  
   </div>
   <div id={{cate.cate_id}} class="panel-collapse collapse ">
    <div class="panel-body">
    
    <ul class="list-group">
  <li class="list-group-item"><span><b>Category Id:</b></span>&nbsp;&nbsp;{{cate.cate_id}}</li>
  <li class="list-group-item"><b>Category Name:</b>&nbsp;&nbsp;{{cate.cate_name}}</li>
  <li class="list-group-item"><b>Category Description:</b>&nbsp;&nbsp;{{cate.cate_desc}}</li>
    
  
</ul>
   </div>
   </div>
   </div>
   </div>
  </div>
  

		</div> <!-- col-sm -->
	</div>
	<!-- container -->
</body>
</html>