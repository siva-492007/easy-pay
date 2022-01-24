import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Bankservlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
            int anum = Integer.parseInt(request.getParameter("account-number"));
            String aname = request.getParameter("account-holdername");
            float bal = Float.parseFloat(request.getParameter("account-balance"));
            int ec = Integer.parseInt(request.getParameter("add-ecoin"));
            Statement s=conn.createStatement();
            String sql="SELECT * FROM account WHERE accountnumber= " + anum + "";
            ResultSet rs = s.executeQuery(sql);
            if(!rs.next()){
                HttpSession ss = request.getSession();
                String email = ss.getAttribute("Mail").toString();
                PreparedStatement st=conn.prepareStatement("INSERT INTO account VALUES (?,?,?,?,?,?)");
                st.setInt(1,anum);
                st.setString(2,aname);
                if(ec*50 <= bal){
                    bal = bal - ec*50;
                    st.setFloat(3,bal);
                    st.setInt(4,ec);
                    int minimum = 1234567, maximum = 9999999;
                    int ran = (int)Math.floor(Math.random()*(maximum-minimum+1)+minimum);
                    st.setInt(5,ran);
                    st.setString(6,email);
                    st.executeUpdate();
                    PreparedStatement s1=conn.prepareStatement("INSERT INTO thistory VALUES (?,?,?,?,?)");
                    s1.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                    s1.setInt(2,ran);
                    s1.setInt(3,ran);
                    s1.setInt(4,ec);
                    s1.setString(5,"Credited");
                    s1.executeUpdate();
                    request.setAttribute("isdone", true);
                    request.setAttribute("doneMessage", "Account Created Successfully!!!");
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                }
                else{
                    request.setAttribute("isError", true);
                    request.setAttribute("errorMessage", "No Sufficient Balance in Bank Account");
                    RequestDispatcher rd = request.getRequestDispatcher("bank.jsp");
                    rd.forward(request, response);
                }
            }
            else{
                request.setAttribute("isError", true);
                request.setAttribute("errorMessage", "Account Number is already present");
                RequestDispatcher rd = request.getRequestDispatcher("bank.jsp");
                rd.forward(request, response);
            }
            conn.close();
        }
        catch(NumberFormatException e){
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", "Enter the value in the range");
            RequestDispatcher rd = request.getRequestDispatcher("bank.jsp");
            rd.forward(request, response);
        }
        catch(Exception e1){
            e1.printStackTrace();
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", e1);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}
