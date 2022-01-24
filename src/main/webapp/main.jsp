<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Easypay</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
   <header>
        <img src="images/ecoin-modified.png" alt="ecoin" class="hicon"></img>
        <h1>WELCOME TO EPAY</h1>
        <a href="index.jsp">Signout</a>
   </header>
<div class="details">
    <h3 style="margin-left:20px;">
         <%
              String name = session.getAttribute("uname").toString();
              out.println("Username:"+name);
         %>
    </h3>
    <h3 style="margin-right:20px;">
         <%
              int wid = (int)session.getAttribute("Wid");
              out.println("Wallet ID:"+wid);
         %>
    </h3>
</div>
<% if(request.getAttribute("isdone") != null && (boolean) request.getAttribute("isdone")) { %>
        <div class="done-message">
            <p><%= request.getAttribute("doneMessage") %></p>
        </div>
 <% } %>
    <section>
      <div class="container">
        <div class="box">
            <img src="images/QRscan.jpg" alt="QR Scan" class="icon">
            <div class="content">
                <h3>QR Scan</h3>
                <p>Scan any QR to pay</p>
                <a href="scan.jsp">Scan</a>
                <a href="http://localhost:5151/Epay/generate">Generate</a>
            </div>
        </div>
        <div class="box">
            <img src="images/bank.jpg" alt="bank" class="icon">
             <div class="content">
                 <h3>Transfer</h3>
                 <p>Transfer ecoins to any wallet</p>
                 <a href="transfer.jsp">Transfer</a>
             </div>
         </div>
         <div class="box">
             <img src="images/wallet.png" alt="wallet" class="icon">
             <div class="content">
                 <h3>Eplus</h3>
                 <p>Add ecoins to your wallet(1ecoin=50rs)</p>
                 <a href="add.jsp">Add</a>
             </div>
         </div>
         <div class="box">
             <img src="images/transactions.jpg" alt="transcation History" class="icon">
             <div class="content">
                 <h3>Transation History</h3>
                 <p>View your last payments through this</p>
                 <a href="http://localhost:5151/Epay/view">view</a>
             </div>
         </div>
      </div>
    </section>
</body>
</html>