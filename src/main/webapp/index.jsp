<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Sign In</title>

	<!-- Font Icon -->
	<link rel="stylesheet"
		  href="fonts/material-icon/css/material-design-iconic-font.min.css">

	<!-- Main css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
 <% if(request.getAttribute("isError") != null && (boolean) request.getAttribute("isError")) { %>
        <div class="error-message">
            <p><%= request.getAttribute("errorMessage") %></p>
        </div>
 <% } %>
 <% if(request.getAttribute("isdone") != null && (boolean) request.getAttribute("isdone")) { %>
         <div class="done-message">
             <p><%= request.getAttribute("doneMessage") %></p>
         </div>
  <% } %>
<div class="main">
	<!-- Sign up Form -->
	<section class="sign-in">
		<div class="container">
			<div class="signin-content">
				<div class="signin-image">
					<figure>
						<img src="images/login.jpg" alt="login image">
					</figure>
					<a href="register.jsp" class="signup-image-link">Create an
						account</a>
				</div>

				<div class="signin-form">
					<h2 class="form-title">Sign In</h2>
					<form method="post" action="login" class="register-form"
						  id="login-form">
						<div class="form-group">
							<label for="email"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
								type="email" name="email" id="email"
								placeholder="Your Email" required/>
						</div>
						<div class="form-group">
							<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
								type="password" name="password" id="password"
								placeholder="Password" required/>
						</div>
						<div class="form-group form-button">
							<button type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" > Log in </button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

</div>

<script>
		function login() {
			var username = document.getElementById("login-form").elements[0].value;
			var password = document.getElementById("login-form").elements[1].value;
            if(username && password){
                console.log("login clicked!")
            }
		}
	</script>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>