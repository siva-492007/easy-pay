import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Loginservlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
            Statement s = conn.createStatement();
            String email = request.getParameter("email");
            String pwd = request.getParameter("password");
            String sql = "SELECT * FROM signup WHERE email='" + email + "'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                if(pwd.equals(rs.getString("password"))){
                    HttpSession ss = request.getSession();
                    ss.setAttribute("Email", email);
                    Statement s1 = conn.createStatement();
                    String sql1 = "SELECT * FROM account WHERE email='" + email + "'";
                    ResultSet rs1 = s1.executeQuery(sql1);
                    if(rs1.next()){
                        ss.setAttribute("Wid",rs1.getInt("walletid"));
                        ss.setAttribute("Becoin",rs1.getInt("ecoins"));
                        ss.setAttribute("uname",rs.getString("username"));
                        response.sendRedirect("main.jsp");
                    }
                }
                else{
                    request.setAttribute("isError", true);
                    request.setAttribute("errorMessage", "Password does not match");
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                }

            } else {
                request.setAttribute("isError", true);
                request.setAttribute("errorMessage", "Email is not registered");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
            s.close();
            conn.close();
        } catch (Exception e) {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", "Unable to login. Try Again later");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}
