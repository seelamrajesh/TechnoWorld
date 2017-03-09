
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>


<link rel="stylesheet" href="css/loginstyle.css" />
<style>
body {
	background: url("images/bluredimg.jpg") no-repeat fixed top center;
}
</style>
<script>
	$(function() {

		$('#hiderow').fadeIn(2000);
		$('#hideout').fadeOut(1000);

	});
</script>
</head>
<body>
	<div class="container">
		<div class="pad">
			<img src="images/trans.png" id="hideout">

			<div class="row vertical-offset-100">
				<div class="col-sm-3 col-sm-offset-8">
					<div class="form-group" id="hiderow">

						<img src="images/bag.png" class="logo" class="img-responsive">

						<form action="loginsuccess" method="POST" >
							<input name="email" type="email" class="form-control input1"
								placeholder="Email Id" required="true"></input>
							<input name="password" type="password"
								class="form-control input2" required="true"
								placeholder="Password"></input>
							<checkbox path="" value="Remember Me" label="Remember Me" />
							<button type="submit"
								class="btn btn-lg btn-success btn-block button">Login</button>
						</form>

					</div>
					<!-- form-group -->
				</div>
				<!-- col-sm -->
			</div>
			<!-- row -->
		</div>
		<!-- pad -->
	</div>
	<!-- container -->
	<script>
		
	</script>
</body>
</html>