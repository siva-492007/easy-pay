import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Scanservlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
            Statement s=conn.createStatement();
            HttpSession ss = request.getSession();
            String email = ss.getAttribute("Email").toString();
            int rid = Integer.parseInt(request.getParameter("rwallet-id"));
            int ecoin = Integer.parseInt(request.getParameter("transfer-ecoin"));
            String sql="SELECT * FROM account WHERE email= '" + email + "'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                if(rs.getInt("walletid") != rid) {
                    if (ecoin <= rs.getInt("ecoins")) {
                        Statement s1 = conn.createStatement();
                        String sq1 = "SELECT ecoins FROM account WHERE walletid= " + rid + "";
                        ResultSet rs1 = s1.executeQuery(sq1);
                        if (rs1.next()) {
                            PreparedStatement st = conn.prepareStatement("UPDATE account SET ecoins= " + ecoin + " + " + rs1.getInt("ecoins") + " WHERE walletid= " + rid + "");
                            st.executeUpdate();
                            st.close();
                            PreparedStatement stmt = conn.prepareStatement("UPDATE account SET ecoins= " + rs.getInt("ecoins") + " - " + ecoin + " WHERE email= '" + email + "'");
                            stmt.executeUpdate();
                            stmt.close();
                            PreparedStatement s2 = conn.prepareStatement("INSERT INTO thistory VALUES (?,?,?,?,?)");
                            s2.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                            s2.setInt(2, rs.getInt("walletid"));
                            s2.setInt(3, rid);
                            s2.setInt(4, ecoin);
                            s2.setString(5, "Debited");
                            s2.executeUpdate();
                            s2.close();
                            PreparedStatement s3 = conn.prepareStatement("INSERT INTO thistory VALUES (?,?,?,?,?)");
                            s3.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                            s3.setInt(2, rs.getInt("walletid"));
                            s3.setInt(3, rid);
                            s3.setInt(4, ecoin);
                            s3.setString(5, "Credited");
                            s3.executeUpdate();
                            s3.close();
                            request.setAttribute("isdone", true);
                            request.setAttribute("doneMessage", "Ecoins Transfered To " + rid + " Succesfully");
                            RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
                            rd.forward(request, response);
                        } else {
                            request.setAttribute("isError", true);
                            request.setAttribute("errorMessage", "Enter a Valid Receiver ID");
                            RequestDispatcher rd = request.getRequestDispatcher("scan.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("isError", true);
                        request.setAttribute("errorMessage", "No Sufficient Ecoins in Wallet");
                        RequestDispatcher rd = request.getRequestDispatcher("scan.jsp");
                        rd.forward(request, response);
                    }
                }
                else{
                    request.setAttribute("isError", true);
                    request.setAttribute("errorMessage", "Sender and Receiver ID Cannot be Same");
                    RequestDispatcher rd = request.getRequestDispatcher("scan.jsp");
                    rd.forward(request, response);
                }
            }
            s.close();
            conn.close();
        }
        catch(NumberFormatException e){
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", "Enter value within the range 1-1000");
            RequestDispatcher rd = request.getRequestDispatcher("scan.jsp");
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
