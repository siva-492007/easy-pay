<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Transfer Ecoins Via QR SCAN</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/material-icon/css/material-design-iconic-font.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"></script>
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
            		<img src="images/qr-scanner.png" alt="Scan image">
               </figure>
            		<a href="main.jsp" class="signup-image-link">BACK</a>
            </div>

				<div class="signin-form">
					<h2 class="form-title"> Wallet Transfer Via QR SCAN</h2>
					<form method="post" action="scan" class="register-form" id="transfer-ecoin-form">
						<div>
						   <video id="preview" width="100%"></video>
						</div>
						<div class="form-group">
							Wallet ID
							<input
									type="text" name="rwallet-id" id="rwallet-id" placeholder="Receiver wallet ID" value="" required readonly/>
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
        let scanner = new Instascan.Scanner({ video: document.getElementById('preview')});
        Instascan.Camera.getCameras().then(function(cameras){
        if(cameras.length > 0){
          scanner.start(cameras[0]);
		}
        else{
          alert('No cameras found');
		}
          }).catch(function(e){
             console.error(e);
            });
          scanner.addListener('scan',function(c){
          document.getElementById('rwallet-id').value=c;
          });
</script>
</body>
</html>