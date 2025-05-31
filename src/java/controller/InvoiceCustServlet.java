package controller;

import dao.InvoiceDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.Invoice;

/**
 *
 * @author Admin
 */
public class InvoiceCustServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Customer user = (Customer) session.getAttribute("user");
        if (user != null) {
            String custid = String.valueOf(user.getCustID());
            InvoiceDAO dao = new InvoiceDAO();
            ArrayList<Invoice> invoices = dao.getInvoice(custid);
            if (invoices == null || invoices.isEmpty()) {
                session.setAttribute("message", "No invoices found for your account.");
            } else {
                request.setAttribute("listInvoice", invoices);
            }
            request.getRequestDispatcher("CustomerDashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("customerLogin.jsp");
        }
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
        return "Servlet for retrieving invoices by customer ID.";
    }
}
