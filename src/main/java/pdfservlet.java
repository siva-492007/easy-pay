import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.*;

public class pdfservlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
            HttpSession ss = request.getSession();
            int wid = (int)ss.getAttribute("Wid");
            String path = "C:\\Users\\dhanush\\OneDrive\\Desktop\\Java project\\src\\main\\webapp\\QRcodes\\"+wid+".png";
            String name = "C:\\Users\\dhanush\\OneDrive\\Desktop\\Java project\\src\\main\\webapp\\pdf\\"+wid+".pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc,new FileOutputStream(name));
            doc.open();
            Paragraph para = new Paragraph("QR CODE");
            doc.add(para);
            doc.add(Image.getInstance(path));
            Statement s=conn.createStatement();
            String sql="SELECT * FROM thistory WHERE (swalletid= " + wid + " AND rwalletid= " + wid + ")  OR (swalletid <> " + wid + " AND rwalletid= " + wid + " AND status='Credited') OR (swalletid= " + wid + " AND rwalletid <> " + wid + " AND status <> 'Credited')";
            Statement s1=conn.createStatement();
            String sql2="SELECT * FROM account WHERE walletid= " + wid + "";
            ResultSet rs = s.executeQuery(sql);
            ResultSet rs2 = s1.executeQuery(sql2);
            if(rs2.next()){
                Paragraph para1 = new Paragraph("User Details");
                doc.add(para1);
                doc.add(new Paragraph(" "));
                PdfPTable table = new PdfPTable(5);
                PdfPCell h1 = new PdfPCell(new Phrase("AccountHolderName"));
                PdfPCell h2 = new PdfPCell(new Phrase("AccountNumber"));
                PdfPCell h3 = new PdfPCell(new Phrase("Walletid"));
                PdfPCell h4 = new PdfPCell(new Phrase("Ecoins"));
                PdfPCell h5 = new PdfPCell(new Phrase("Balance"));
                table.addCell(h1);
                table.addCell(h2);
                table.addCell(h3);
                table.addCell(h4);
                table.addCell(h5);
                table.setHeaderRows(1);
                table.addCell(rs2.getString("accounthname"));
                table.addCell(String.valueOf(rs2.getInt("accountnumber")));
                table.addCell(String.valueOf(rs2.getInt("walletid")));
                table.addCell(String.valueOf(rs2.getInt("ecoins")));
                table.addCell(String.valueOf(rs2.getFloat("balanceamount")));
                doc.add(table);
            }
            doc.add(new Paragraph(" "));
            Paragraph para2 = new Paragraph("Transaction History");
            doc.add(para2);
            doc.add(new Paragraph(" "));
            Paragraph para3 = new Paragraph("Date_and_Time                   Senderwalletid        Recieverwalletid         ecoins       Status");
            doc.add(para3);
            doc.add(new Paragraph(" "));
            while(rs.next()){
                Paragraph para4 = new Paragraph(rs.getTimestamp("Date_and_Time")+"          "+rs.getInt("swalletid")+"                  "+rs.getInt("rwalletid")+"                 "+rs.getInt("ecoins")+"          "+rs.getString("status"));
                doc.add(para4);
                doc.add(new Paragraph(" "));
            }
            doc.close();
            s.close();
            conn.close();
            request.setAttribute("isdone", true);
            request.setAttribute("doneMessage", "PDF generated succesfully");
            RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
            rd.forward(request, response);
        }
        catch(Exception e){
            e.printStackTrace();
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", e);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

        }
    }
}
