<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="a"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<title>Product</title>
<link rel="stylesheet" href="css/product.css"/>
<script>
var cate_list=${cate_list};
var sup_list=${sup_list};
var prod_list=${prod_list};
var subcate_list=${subcate_list};


angular.module('product_app',[]).controller('product_ctrl',function($scope){
	$scope.category=cate_list;
	$scope.suplier=sup_list;
	$scope.product=prod_list;
	$scope.subcategory=subcate_list;


	
});
$(document).on('click', '.browse', function(){
	  var file = $(this).parent().parent().parent().find('.file');
	  file.trigger('click');
	});
	$(document).on('change', '.file', function(){
	  $(this).parent().find('.form-control').val($(this).val().replace(/C:\\fakepath\\/i, ''));
	});


</script>
<style>
.row_prod {
	margin-top: 60px;
	padding: 20px;
	box-shadow: 5px 5px 15px #000;
}
#listpro{
   margin-top: 15px;
	box-shadow: 5px 5px 15px #000;

}
#search{
position:absolute;

margin-top: 75px;
margin-left:699px;



}
#prod_search{
margin-top: 60px;
	padding: 20px;
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
.file {
  visibility: hidden;
  position: absolute;
}

</style>
</head>
<body>

	<div class="container" ng-app="product_app" ng-controller="product_ctrl" >
	<c:if test="${edit==true}">
		<div class="col-sm-3 text-center row_prod" >
			<h1>Product</h1>
			${message}
			<a:form action="productrequestpost" method="post"
				modelAttribute="prod" enctype="multipart/form-data">
				<a:input path="pro_id" placeholder="Product Id"
					class="form-control " style="margin-top:10px"/>
				<a:input path="pro_name" placeholder="Product Name"
					class="form-control " style="margin-top:10px"/>
				<a:input path="pro_price" placeholder="Product Price"
					class="form-control" style="margin-top:10px" />
				<a:select path="pro_cate_id" class="form-control" style="margin-top:10px"> 
				<a:option value="-1">Select Category Name</a:option>
					<a:option ng-repeat="cat in category" value="{{cat.cate_id}}">{{cat.cate_name}}</a:option>
									</a:select>
									<a:select path="pro_sub_cate_id" class="form-control" style="margin-top:10px"> 
				<a:option value="-1">Select Sub Category Name</a:option>
					<a:option ng-repeat="sub in subcategory" value="{{sub.sub_cate_id}}">{{sub.sub_cate_name}}</a:option>
									</a:select>
			<a:select path="pro_sup_id" class="form-control" style="margin-top:10px"> 
				<a:option value="-1">Select Supplier Name</a:option>
					<a:option ng-repeat="sup in suplier" value="{{sup.sup_id}}">{{sup.sup_name}}</a:option>
									</a:select>
				
				<a:input path="pro_desc" placeholder="Product Description" style="margin-top:10px"
					class="form-control " />
				<a:input path="pro_stock" placeholder="Product Stock"
					class="form-control " style="margin-top:10px"/>
					
					 <div class="form-group" style="margin-top:10px;">
              <a:input path="pro_image" placeholder="Product Image"
					type="file" class="file"  style="margin-top:10px"/>	    
					<div class="input-group">
      <input type="text" class="form-control "  placeholder="Upload Image">
      <span class="input-group-btn">
        <button class="browse btn btn-defualt " type="button"><i class="glyphicon glyphicon-search"></i> Browse</button>
      </span>
    </div>
  </div>
   <div class="form-group" style="margin-top:10px;">
              <a:input path="pro_text" placeholder="Product Image"
					type="file" class="file"  style="margin-top:10px"/>	    
					<div class="input-group">
      <input type="text" class="form-control "  placeholder="Upload Full Description">
      <span class="input-group-btn">
        <button class="browse btn btn-defualt " type="button"><i class="glyphicon glyphicon-search"></i> Browse</button>
      </span>
    </div>
  </div>
      <a:input path="multipleImage" type="file" multiple="multiple"/>
					
					
				<a:button type="submit" class="btn btn-success btn-block button">Save</a:button>
				<a class="btn btn-warning btn-block" href="productrequest">Clear</a>
			</a:form>
		</div> <!-- col-sm -->
		</c:if> <!-- save -->
			<c:if test="${udate==true}">
		<div class="col-sm-3 text-center row_prod" >
			<h1>Product</h1>
			${message}
			<a:form action="updaterequestpost" method="post"
				modelAttribute="prod" enctype="multipart/form-data">
				<a:input path="pro_id" placeholder="Product Id"
					class="form-control " style="margin-top:10px"/>
				<a:input path="pro_name" placeholder="Product Name"
					class="form-control " style="margin-top:10px"/>
				<a:input path="pro_price" placeholder="Product Price"
					class="form-control" style="margin-top:10px" />
				<a:select path="pro_cate_id" class="form-control" style="margin-top:10px"> 
				<a:option value="-1">Select Category Name</a:option>
					<a:option ng-repeat="cat in category" value="{{cat.cate_id}}">{{cat.cate_name}}</a:option>
									</a:select>
									<a:select path="pro_sub_cate_id" class="form-control" style="margin-top:10px"> 
				<a:option value="-1">Select Sub Category Name</a:option>
					<a:option ng-repeat="sub in subcategory" value="{{sub.sub_cate_id}}">{{sub.sub_cate_name}}</a:option>
									</a:select>
			<a:select path="pro_sup_id" class="form-control" style="margin-top:10px"> 
				<a:option value="-1">Select Supplier Name</a:option>
					<a:option ng-repeat="sup in suplier" value="{{sup.sup_id}}">{{sup.sup_name}}</a:option>
									</a:select>
				
				<a:input path="pro_desc" placeholder="Product Description" style="margin-top:10px"
					class="form-control"/>
				<a:input path="pro_stock" placeholder="Product Stock"
					class="form-control " style="margin-top:10px"/>
					 <div class="form-group" style="margin-top:10px;">
              <a:input path="pro_image" placeholder="Product Image"
					type="file" class="file" style="margin-top:10px"/>	    
					<div class="input-group">
      <input type="text" class="form-control "  placeholder="Upload Image">
      <span class="input-group-btn">
        <button class="browse btn btn-defualt " type="button"><i class="glyphicon glyphicon-search"></i> Browse</button>
      </span>
    </div>
  </div>
  <div class="form-group" style="margin-top:10px;">
              <a:input path="pro_text" placeholder="Product Image"
					type="file" class="file"  style="margin-top:10px"/>	    
					<div class="input-group">
      <input type="text" class="form-control "  placeholder="Upload Full Description">
      <span class="input-group-btn">
        <button class="browse btn btn-defualt " type="button"><i class="glyphicon glyphicon-search"></i> Browse</button>
      </span>
    </div>
  </div>
        <a:input path="multipleImage" type="file" multiple="multiple"/>
  
				<a:button type="submit" class="btn btn-success btn-block button">Update</a:button>
				<a class="btn btn-warning btn-block" href="productrequest">Clear</a>
			</a:form>
		</div> <!-- col-sm -->
		</c:if> <!-- udate -->
		<div class="col-sm-8 col-sm-offset-1">
		<div  id="search">
		<b class="glyphicon glyphicon-search"></b>
		</div>
		
		<input type="text" placeholder="Search Product" class="form-control" id="prod_search" ng-model="searchprod"/>
		<div class="row_pro" ng-repeat="prod in product|filter:searchprod" id="listpro">
		  <div class="panel-group" id="accordion">
     <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title" >
        <a data-toggle="collapse" data-parent="#accordion" href="#{{prod.pro_id}}"><b>{{prod.pro_name}}</b></a>
        
   </h4>
     
   
   <a class="btn btn-danger pull-right" id="del" href="delete?pid={{prod.pro_id}}">Delete</a>&nbsp;
    <a class="btn btn-primary pull-right" id="edit" href="update?pid={{prod.pro_id}}">Edit</a>
   
  
   </div>
   
   <div id={{prod.pro_id}} class="panel-collapse collapse ">
    <div class="panel-body">
    <div class="col-sm-6">
<!-- <div align="center"> -->
<img width="150px" height="150px"  class="img-responsive" alt="img" src="pro_images/{{prod.pro_id}}.jpg">
<!-- </div>
 --></div>
            <div class="col-sm-6">
    
    <ul class="list-group">
   
  <li class="list-group-item"><span><b>Product Id:</b></span>&nbsp;&nbsp;{{prod.pro_id}}</li>
  <li class="list-group-item"><b>Product Price:</b>&nbsp;&nbsp;{{prod.pro_price}}</li>
  <li class="list-group-item"><b>Product Category Id:</b>&nbsp;&nbsp;{{prod.pro_cate_id}}</li>
    <li class="list-group-item"><b>Product Sub Category Id:</b>&nbsp;&nbsp;{{prod.pro_sub_cate_id}}</li>
  
    <li class="list-group-item"><b>Product Supplier Id:</b>&nbsp;&nbsp;{{prod.pro_sup_id}}</li>
  
    <li class="list-group-item"><b>Product Description:</b>&nbsp;&nbsp;{{prod.pro_desc}}</li>
    <li class="list-group-item"><b>Product Stock:</b>&nbsp;&nbsp;{{prod.pro_stock}}</li>
  
</ul>

  </div>

   </div>
   </div>
   </div>
   </div> <!-- panel group -->
  
  </div> <!-- row -->

		</div> <!-- col-sm -->
	</div> <!-- container -->
</body>
</html>