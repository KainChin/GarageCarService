package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.InvoiceDetail;
import dao.InvoiceDetailDAO;
import model.InvoiceDetail2;

/**
 *
 * @author Dell
 */
@WebServlet(name = "InvoiceDetailServlet", urlPatterns = {"/InvoiceDetailServlet"})
public class InvoiceDetailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String invoiceid = request.getParameter("invoiceID");

        if (invoiceid != null) {
            try {
                int invoiceID = Integer.parseInt(invoiceid.trim());
                InvoiceDetailDAO dao = new InvoiceDetailDAO();
                List<InvoiceDetail> serviceDetails = dao.getServiceDetailsByInvoiceID(invoiceID);
                List<InvoiceDetail2> partDetails = dao.getPartDetailsByInvoiceID(invoiceID);
                if ((serviceDetails == null || serviceDetails.isEmpty())
                      && (partDetails == null || partDetails.isEmpty())) {
                    session.setAttribute("message", "Hiện tại chưa có bất kì thông tin về vé của bạn.");
                } else {
                    request.setAttribute("serviceDetails", serviceDetails);
                    request.setAttribute("partDetails", partDetails);
                }
            } catch (NumberFormatException e) {
            }
        }

        request.getRequestDispatcher("CustomerDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles invoice detail retrieval";
    }
}
