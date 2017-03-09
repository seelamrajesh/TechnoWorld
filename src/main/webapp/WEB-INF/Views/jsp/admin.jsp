<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style>
.hello {
	margin-top: 10px;
	box-shadow: 5px 5px 15px #000;
}
</style>
</head>
<body>
	<jsp:include page="Admin_header.jsp"></jsp:include>

	<div class="container">
		<c:if test="${loggedin==true}">
			<div class="col-sm-4 col-sm-offset-4">
				<div class="alert hello text-center">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>${role}</strong>
				</div>
				<!-- alert -->
			</div>
			<!-- col-sm -->

		</c:if>
	</div>
	<!-- container-->
	<c:choose>
		<c:when test="${categoryrequest==true}">
			<jsp:include page="category.jsp"></jsp:include>
		</c:when>
		<c:when test="${subcategoryrequest==true}">
			<jsp:include page="subcategory.jsp"></jsp:include>
		</c:when>
		<c:when test="${supplierrequest==true}">
			<jsp:include page="supplier.jsp"></jsp:include>
		</c:when>
		<c:when test="${productrequest==true}">
			<jsp:include page="product.jsp"></jsp:include>
		</c:when>
		<c:when test="${adminhome==true}">
		<jsp:include page="admin_home.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<br>
			<br>


			<jsp:include page="admin_home.jsp"></jsp:include>
		</c:otherwise>

	</c:choose>

<br>
<br>
<jsp:include page="footer.jsp"></jsp:include>



</body>
</html>