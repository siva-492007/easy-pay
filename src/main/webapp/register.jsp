<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Sign Up</title>

	<!-- Font Icon -->
	<link rel="stylesheet"
		  href="fonts/material-icon/css/material-design-iconic-font.min.css">

	<!-- Main css -->
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
<% if(request.getAttribute("isError") != null && (boolean) request.getAttribute("isError")) { %>
        <div class="error-message">
            <p><%= request.getAttribute("errorMessage") %></p>
        </div>
 <% } %>
<div class="main">
	<!-- Sign up form -->
	<section class="signup">
		<div class="container">
			<div class="signup-content">
				<div class="signup-form">
					<h2 class="form-title">Sign up</h2>

					<form method="post" action="register" class="register-form"
						  id="register-form">
						<div class="form-group">
							<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
								type="text" name="name" id="name" placeholder="Your Name" required/>
						</div>
						<div class="form-group">
							<label for="email"><i class="zmdi zmdi-email"></i></label> <input
								type="email" name="email" id="email" placeholder="Your Email" required/>
						</div>
						<div class="form-group">
							<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
								type="password" name="pass" id="pass" placeholder="Password"
								minlength="8" required/>
						</div>

						<div class="form-group form-button">
							<button name="signup" id="signup" class="form-submit"
									onclick="register()" > Register </button>
						</div>
					</form>
				</div>
				<div class="signup-image">
					<figure>
						<img src="images/signup-image.jpg" alt="sing up image">
					</figure>
					<a href="index.jsp" class="signup-image-link">I am already
						member</a>
				</div>
			</div>
		</div>
	</section>
</div>

<script>
		function register() {
			var username = document.getElementById("register-form").elements[0].value;
			var email = document.getElementById("register-form").elements[1].value;
			var password = document.getElementById("register-form").elements[2].value;
            if(password && email && username){
                console.log("register clicked!")
            }
		}
	</script>
<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>



</body>
</html>