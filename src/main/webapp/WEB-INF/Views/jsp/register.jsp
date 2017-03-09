
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="css/loginstyle.css" />
<style>
body {
	background: url("images/bluredimg.jpg") no-repeat fixed top center;
}

.inputone {
	padding: 20px;
	margin-top: 10px;
}
.inputpass{
	padding: 20px;
	margin-top: 10px;
}
.inputcpass{
	padding: 20px;
	margin-top: 10px;
}

.inputtwo {
	padding: 5px;
	margin-top: 10px;
}
</style>

	

</head>
<body>
	<div class="container">

		<div class="row vertical-offset-100">
			<div class="col-sm-3 col-sm-offset-8 col">
				<div class="form-group form" id="">
					<img src="images/bag.png" class="logo" class="img-responsive">

					<f:form action="registerrequest" method="POST"
						modelAttribute="register" >
						<f:input path="name" type="name" class="form-control inputone"
							placeholder="Full Name"  required="true"/>
						<f:input path="email" type="email" class="form-control inputone"
							placeholder="Email Id" required="true"></f:input>
						<input type="password" class="form-control inputone"
							required="true" placeholder="Password" name="inputpass"></input>
						<f:input path="password" type="password"
							class="form-control inputone" placeholder="Confirm Password"
							required="true" id="inputcpass"></f:input>
						<f:input path="number" type="tel" class="form-control inputone"
							required="true" placeholder="Phone Number"></f:input>
						<f:input path="dob" type="date" class="form-control inputone"
							placeholder="Date" required="true"></f:input>
						<f:select path="gender" items="${gender}"
							class="form-control inputtwo"></f:select>
						<f:textarea path="address" type="text"
							class="form-control inputone" required="true"
							placeholder="Address"></f:textarea>
						<f:checkbox path="" value="Remember Me"
							label="I agree the terms and conditions" />
						<f:button type="submit"
							class="btn btn-lg btn-success btn-block button" id="permit">Permit</f:button>
					</f:form>

				</div>
				<!-- form-group -->
			</div>
			<!-- col-sm -->
		</div>
		<!-- row -->
	</div>
	<!-- container -->
	
</body>
</html>