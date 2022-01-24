<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Add Ecoin</title>
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<% if(request.getAttribute("isError") != null && (boolean) request.getAttribute("isError")) { %>
        <div class="error-message">
            <p><%= request.getAttribute("errorMessage") %></p>
        </div>
 <% } %>
	<div class="main">
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
						<figure>
							<img src="images/add-coins.png" alt="add coin image">
						</figure>
						 <a href="main.jsp" class="signup-image-link">BACK</a>
					</div>

					<div class="signin-form">
						<h2 class="form-title">Add Ecoin from Bank</h2>
						<form method="post" action="add" class="register-form"
							id="add-ecoin-form">
							<div class="form-group">
								Ecoin
                                <input type="number" name="add-ecoin" id="add-coin" min="1" max="1000" onchange="addEcoin()"
									placeholder="Enter Ecoin to add" required/>
							</div>
                           <div class="form-group">
                                <p id="inr-required"></p>
                            </div>

                            <div class="form-group form-button">
								<button name="add-ecoin" id="add-ecoin" class="form-submit" value="Add Ecoin" > Add Ecoin </button>
							</div>

						</form>
					</div>
				</div>
			</div>
		</section>

	</div>

    <script>
        function addEcoin() {
            var addEcoin = document.getElementById("add-ecoin-form").elements[0].value;
            document.getElementById("inr-required").innerHTML = "Required INR is : " + addEcoin * 50;
            var inrRequired = document.getElementById("add-ecoin-form").elements[1].value;
            var accountNumber = document.getElementById("add-ecoin-form").elements[2].value;
            var accountHoldername = document.getElementById("add-ecoin-form").elements[3].value;

            console.log(addEcoin, ", ", inrRequired)
        }
    </script>

	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>