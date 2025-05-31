package controller;

import dao.CustomerDAO;
import model.Customer;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCustServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String name = request.getParameter("txtname");
        String phone = request.getParameter("txtphone");
        if (name != null && phone != null) {
            if (phone.startsWith("0")) {
                request.setAttribute("error", "Login fail");
                request.getRequestDispatcher("customerLogin.jsp").forward(request, response);
                return;
            }
            CustomerDAO dao = new CustomerDAO();
            Customer kq = dao.checkLogin(name, phone);
            if (kq == null) {
                request.setAttribute("error", "Login fail");
                request.getRequestDispatcher("customerLogin.jsp").forward(request, response);
            } else {
                HttpSession s = request.getSession(true);
                s.setAttribute("user", kq);
                response.sendRedirect("CustomerDashboard.jsp");
            }
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
        return "Servlet to handle customer login";
    }
}
