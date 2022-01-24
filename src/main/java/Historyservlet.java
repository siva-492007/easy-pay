import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Historyservlet extends HttpServlet {
    public void doGet(HttpServletRequest request, @org.jetbrains.annotations.NotNull HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
            HttpSession ss = request.getSession();
            int wid = (int)ss.getAttribute("Wid");
            Statement s=conn.createStatement();
            String sql="SELECT * FROM thistory WHERE (swalletid= " + wid + " AND rwalletid= " + wid + ")  OR (swalletid <> " + wid + " AND rwalletid= " + wid + " AND status='Credited') OR (swalletid= " + wid + " AND rwalletid <> " + wid + " AND status <> 'Credited')";
            ResultSet rs = s.executeQuery(sql);
            Statement s1=conn.createStatement();
            String sql1="SELECT ecoins,balanceamount FROM account WHERE walletid= " + wid + "";
            ResultSet rs1 = s1.executeQuery(sql1);
            String cssTag="<link rel='stylesheet' type='text/css' href='style.css'>";
            out.println("<html>");
            out.println("<head><title>Transaction History</title>"+cssTag+"</head>");
            out.println("<body>");
            out.println("<header><a href=\"main.jsp\" style=\"margin-left:50px;\" >Back</a><h1>Transaction History</h1><a href=\"http://localhost:5151/Epay/pdf\" style=\"width:70px;\">Download</a></header>");
            if(rs1.next()){
                out.println("<div style=\"text-align:center; margin-top:5px;\">");
                out.println("<h3>");
                out.println("Ecoins:"+rs1.getInt("ecoins"));
                out.println("</h3>");
                out.println("<h3>");
                out.println("BalanceAmount:"+rs1.getInt("balanceamount"));
                out.println("</h3>");
                out.println("</div>");
            }
            out.println("<table><tr><th>DATE_AND_TIME</th><th>SENDER WALLETID</th><th>RECEIVER WALLETID</th><th>ECOINS</th><th>STATUS</th></tr>");
            while(rs.next()){
                out.println("<tr>");
                out.println("<td>");
                out.println(rs.getTimestamp("Date_and_Time"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getInt("swalletid"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getInt("rwalletid"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getInt("ecoins"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("status"));
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
            s.close();
            conn.close();
        }
        catch(Exception e){
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", "Unable to process. Try Again later");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}
