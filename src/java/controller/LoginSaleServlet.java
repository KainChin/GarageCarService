package controller;

import dao.SalerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Saler;

public class LoginSaleServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("utf-8");
            String name = request.getParameter("username");
            if (name != null) {
                SalerDAO d = new SalerDAO();
                Saler kq = d.checkLogin(name);
                if (kq == null) {
//                  
                    request.setAttribute("error", "login fail");
                    request.getRequestDispatcher("saleLogin.jsp").forward(request, response);
                } else {
                    HttpSession s = request.getSession(true);
                    s.setAttribute("user", kq);
                    response.sendRedirect("SaleDashboard.jsp");
                }

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
        return "Servlet to handle saler login";
    }
}
