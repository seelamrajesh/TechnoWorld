<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="m"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Order</title>

<link rel="stylesheet" href="css/category.css" />


</head>
<body>

	<div class="container">
		<div class="panel panel-default" style="margin-top: 40px;">
			<div class="panel-heading">
				<h4>
					<b>My Orders</b>
				</h4>
			</div>
			<div class="panel-body">





				<table class="table table-bordered table-hover">
					<thead>

						<tr>
							<th class="text-center" style="padding: 20px">Product</th>
							<th class="text-center" style="padding: 20px">Email Id</th>
							<th class="text-center" style="padding: 20px">Shipping
								Address</th>
							<th class="text-center" style="padding: 20px">Billing
								Address</th>
							<th class="text-center" style="padding: 20px">Amount</th>





						</tr>
					</thead>


					<m:forEach items="${orderslist}" var="ord" varStatus="loop">
						<tbody>
							<tr>
								<m:set var="lop" value="${loop.index}"></m:set>


								<m:if test='${ord.status=="o"}'>
									<td class="text-center"><img width="30px" height="60px"
										src="pro_images/${ord.pro_id}.jpg"><span
										style="margin-left: 50px;"> <b>${ord.pro_name}</b></span></td>
								</m:if>
								<m:if test='${ord.status=="m"}'>
									<td class="text-center"><m:forEach items="${multi}"
											var="multi" varStatus="mulpos">
											<m:set var="mup" value="${mulpos.index}"></m:set>

											<m:forEach items="${multiname}" begin="${mup}" end="${mup}"
												var="multiname">
                                                 <div style="margin-top: 10px;">
												<img width="30px" height="60px"
													src="pro_images/${multi}.jpg">
												<span style="margin-left: 50px;"><b>${multiname}</b><br>
												</span>
												</div>
											</m:forEach>
										</m:forEach></td>
								</m:if>




								<td class=""><div style="margin-top: 20px;">${ord.email_id}</div></td>
								<m:forEach items="${shiplist}" begin="${lop}" end="${lop}"
									var="ship">

									<td><div style="margin-top: 20px;">

											${ship.street},${ship.zipcode}<br> ${ship.city}<br>
											${ship.state}<br> ${ship.country}
										</div></td>

								</m:forEach>
								<m:forEach items="${billlist}" begin="${lop}" end="${lop}"
									var="bill">

									<td><div style="margin-top: 20px;">

											${bill.street},${bill.zipcode}<br> ${bill.city}<br>
											${bill.state}<br> ${bill.country}
										</div></td>

								</m:forEach>


								<td class="text-center"><div style="margin-top: 20px;">${ord.total}</div></td>

							</tr>

						</tbody>

					</m:forEach>


				</table>
			</div>


		</div>
	</div>
</body>
</html>