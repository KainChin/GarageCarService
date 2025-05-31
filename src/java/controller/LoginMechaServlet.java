package controller;

import dao.MechanicDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Mechanic;

@WebServlet("/LoginMechaServlet")
public class LoginMechaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("utf-8");
            String name = request.getParameter("username");
            if (name != null) {
                MechanicDAO d = new MechanicDAO();
                Mechanic kq = d.checkLogin(name);
                if (kq == null) {
//                  
                    request.setAttribute("error", "login fail");
                    request.getRequestDispatcher("mechanicLogin.jsp").forward(request, response);
                } else {
                    HttpSession s = request.getSession(true); // Tao session mới nếu chưa có và có rồi thì trả về cái cũ
                    s.setAttribute("user", kq); // Đặt user thành Customer kq
                    response.sendRedirect("MechanicDashboard.jsp"); // Chuyển hướng
                }

            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }
}
