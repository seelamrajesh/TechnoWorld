<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="m"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
<link rel="stylesheet" href="css/category.css"/>


</head>
<body>

<div class="container">
<div class="panel panel-default" style="margin-top:40px;">
<div class="panel-heading">
<h4>Order</h4>
</div>
<div class="panel-body">


<div class="col-sm-6 ">
<div  class="panel panel-default" >
<div class="panel-heading" >
<div ><b >Shipping Address</b></div>
<div class="panel-body">
<m:set var="ship" value='<%=session.getAttribute("ship")%>' ></m:set>
<m:set var="user" value='<%=session.getAttribute("user")%>' ></m:set>

${user}<br>
${ship.street},${ship.zipcode}<br>
${ship.city}<br>
${ship.state}<br>
${ship.country}

</div>
</div>
</div>
</div>
<div class="col-sm-6 ">
<div  class="panel panel-default">
<div class="panel-heading">
<b>Billing Address</b>
<div class="panel-body">
<m:set var="bill" value='<%=session.getAttribute("bill")%>' ></m:set>
<m:set var="user" value='<%=session.getAttribute("user")%>' ></m:set>

${user}<br>
${bill.street},${bill.zipcode}<br>
${bill.city}<br>
${bill.state}<br>
${bill.country}
</div>
</div>
</div>
</div>



<table class="table table-bordered table-hover" >
 <thead>
 
      <tr >
        <th class="text-center" style="padding:20px">Product</th>
        <th class="text-center" style="padding:20px">Quantity</th>
          <th class="text-center" style="padding:20px">Date</th>
            <th class="text-center" style="padding:20px" >Price</th>
            
         
         
        
      </tr>
    </thead>


<m:set var="pro" value='<%=session.getAttribute("onepro")%>' ></m:set>
<tbody>

      <tr>
        
        <td class="text-center"><img width="30px" height="60px" src="pro_images/${pro.pro_id}.jpg"><span style="margin-left: 50px;">
        <b >${pro.pro_name}</b></span></td>
        <td class="text-center"> 
       1
        </td>
        <td class="text-center"><div style="margin-top:20px;"><%=session.getAttribute("date")%></div></td>
           <td class="text-center"><div style="margin-top:20px;">${pro.pro_price}</div></td>
          
      </tr>
      
    </tbody>
   
  
   
 
  </table>
  <b style="font-size: 20px; color: red;" class="pull-right">Total:    <%=session.getAttribute("total")%></b>
  </div>
 
<div class="panel-footer">
  
 <h6>@ShopOn</h6>
<f:form modelAttribute="order">
<div class="pull-right" style="position:relative; top:-30px;"> <f:button type="submit" name="_eventId_submitOrder" value="confirm order" class="btn btn-success ">Confirm Order </f:button></div>
<div class="pull-left" style="position:relative; top:-40px; "><f:button type="submit" name="_eventId_cancel" class="btn btn-danger " style="margin-top:10px; ">Cancel</f:button></div>

</f:form>
</div>



</div>
 </div>
</div>
</body>
</html>