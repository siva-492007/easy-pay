import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String uname = request.getParameter("name");
            String mail = request.getParameter("email");
            String pwd = request.getParameter("pass");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
            Statement s = conn.createStatement();
            String sql = "SELECT * FROM signup WHERE email='" + mail + "'";
            ResultSet rs = s.executeQuery(sql);
            if (!rs.next()) {
                PreparedStatement s1 = conn.prepareStatement("INSERT INTO signup VALUES (?,?,?)");
                s1.setString(1, uname);
                s1.setString(2, mail);
                s1.setString(3, pwd);
                s1.executeUpdate();
                s1.close();
                HttpSession ss = request.getSession();
                ss.setAttribute("Mail", mail);
                response.sendRedirect("bank.jsp");
            }
            else{
                request.setAttribute("isError", true);
                request.setAttribute("errorMessage", "Already Registered  Email");
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", e);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}
