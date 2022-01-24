<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Bank Details</title>

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
            <img src="images/add-coins.png" alt="add coin image">


          </figure>
        </div>

        <div class="signin-form">
          <h2 class="form-title">Bank Details</h2>
          <form method="post" action="bank" class="register-form"
                id="add-ecoin-form">
            <div class="form-group">
              Account Number
              <input
                      type="text" name="account-number" id="account-number"
                      minlength="10" maxlength="10" placeholder="Bank account number" required/>
            </div>
            <div class="form-group">
              Account Holdername
              <input
                      type="text" name="account-holdername" id="account-holdername"
                      placeholder="Bank account holdername" required/>
            </div>
            <div class="form-group">
              Account BalanceAmount
              <input
                      type="text" name="account-balance" id="account-balance"
                      placeholder="Bank account Balance" required/>
            </div>
            <p> 1 Ecoin = 50 Rs</p>
            <div class="form-group">
              Ecoin
              <input type="number" name="add-ecoin" id="add-coin" min="1"
                     placeholder="Enter Initial Ecoins to add" required/>
            </div>
            <div class="form-group form-button">
              <button name="bank-details" id="bank-details" class="form-submit" value="bank-details" > SUBMIT </button>
            </div>

          </form>
        </div>
      </div>
    </div>
  </section>

</div>
<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>