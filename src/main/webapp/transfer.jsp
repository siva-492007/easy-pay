<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Transfer Ecoin</title>

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

	<!-- Sing in  Form -->
	<section class="sign-in">
		<div class="container">
			<div class="signin-content">
				<div class="signin-image">
					<figure>
						<img src="images/wallet-transfer.jpg" alt="transfer coin image">
					</figure>
					 <a href="main.jsp" class="signup-image-link">BACK</a>
				</div>

				<div class="signin-form">
					<h2 class="form-title"> Wallet Transfer</h2>
					<form method="post" action="transfer" class="register-form" id="transfer-ecoin-form">
						<div class="form-group">
							Wallet ID
							<input
									type="text" name="rwallet-id" id="rwallet-id"
									minlength="7" maxlength="7" placeholder="Receiver wallet ID" required/>
						</div>
						<div class="form-group">
							Ecoins
							<input
									type="number" name="transfer-ecoin" id="transfer-ecoin"
									min="1" max="1000" placeholder="Ecoins to transfer" required/>
						</div>
						<div class="form-group form-button">

							<button id="transfer" class="form-submit"
									onclick="ecoinTransfer()" > Transfer Ecoin</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

</div>

<script>
        function ecoinTransfer() {
            var walletID = document.getElementById("transfer-ecoin-form").elements[0].value;
            var transferEcoin = document.getElementById("transfer-ecoin-form").elements[1].value;
            console.log(walletID, ", ", transferEcoin)
        }
    </script>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>