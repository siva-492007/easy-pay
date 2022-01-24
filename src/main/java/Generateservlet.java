import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import javax.swing.*;

public class Generateservlet extends HttpServlet {
    public static void createQR (String data, String path, String charset,int height, int width) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession ss = request.getSession();
            int wid = (int)ss.getAttribute("Wid");
            String data = Integer.toString(wid);
            String path = "C:\\Users\\dhanush\\OneDrive\\Desktop\\Java project\\src\\main\\webapp\\QRcodes\\"+wid+".png";
            String charset = "UTF-8";
            createQR(data, path, charset, 200, 200);
            var frame = new JFrame();
            var icon = new ImageIcon(path);
            var label = new JLabel(icon);
            frame.add(label);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            request.setAttribute("isdone", true);
            request.setAttribute("doneMessage", "QR Generated Succesfully");
            RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
            rd.forward(request, response);
        }
        catch(Exception e){
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", "Unable to process. Try Again later");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}