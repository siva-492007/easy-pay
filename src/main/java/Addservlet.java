import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.*;

public class Addservlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
            Statement s=conn.createStatement();
            int ecoin = Integer.parseInt(request.getParameter("add-ecoin"));
            HttpSession ss = request.getSession();
            String email = ss.getAttribute("Email").toString();
            String sql="SELECT * FROM account WHERE email= '" + email + "'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                float bal=(float)rs.getInt("balanceamount");
                if(ecoin*50 <= bal){
                    PreparedStatement st=conn.prepareStatement("UPDATE account SET ecoins= " + ecoin + " + " + rs.getInt("ecoins") + " WHERE email= '" + email + "'");
                    st.executeUpdate();
                    st.close();
                    bal = bal - ecoin*50;
                    PreparedStatement stmt=conn.prepareStatement("UPDATE account SET balanceamount= " + bal + " WHERE email= '" + email + "'");
                    stmt.executeUpdate();
                    stmt.close();
                    PreparedStatement s1=conn.prepareStatement("INSERT INTO thistory VALUES (?,?,?,?,?)");
                    s1.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                    s1.setInt(2,rs.getInt("walletid"));
                    s1.setInt(3,rs.getInt("walletid"));
                    s1.setInt(4,ecoin);
                    s1.setString(5,"Credited");
                    s1.executeUpdate();
                    s1.close();
                    request.setAttribute("isdone", true);
                    request.setAttribute("doneMessage", "Ecoins Added Succesfully");
                    RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
                    rd.forward(request, response);
                }
                else{
                    request.setAttribute("isError", true);
                    request.setAttribute("errorMessage", "No Sufficient Balance in Bank Account");
                    RequestDispatcher rd = request.getRequestDispatcher("add.jsp");
                    rd.forward(request, response);
                }
            }
            s.close();
            conn.close();
        }
        catch(NumberFormatException e){
            out.println("Enter the number in a valid range");
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", "Enter the number in a valid range 1-1000");
            RequestDispatcher rd = request.getRequestDispatcher("add.jsp");
            rd.forward(request, response);

        }
        catch(Exception e1)
        {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", "Unable to process. Try Again later");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}
