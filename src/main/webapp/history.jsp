<!DOCTYPE html>
<html>
<head>
<title>Transaction History</title>
</head>

<body>
<% if(request.getAttribute("isError") != null && (boolean) request.getAttribute("isError")) { %>
        <div class="error-message">
            <p><%= request.getAttribute("errorMessage") %></p>
        </div>
 <% } %>
<header>
   <h1>Transaction History</h1>
   <a href="main.jsp">Back</a>
</header>
<form method="post" action="history" class="register-form" id="add-ecoin-form">
	<div class="form-group">
	     Date
            <input type="date" name="fmonth" id="fmonth" placeholder="Starting Date" required/>
    </div>
    <div class="form-group">
	     Date
            <input type="date" name="tmonth" id="tmonth" placeholder="Ending Date" required/>
    </div>
    <div class="form-group form-button">
            <button name="th" id="th" class="form-submit" value="View" > View </button>
    </div>
</form>
</body>
</html>