/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Service;

/**
 *
 * @author Dell
 */
@WebServlet(name = "UpdateServiceController", urlPatterns = {"/UpdateServiceController"})
public class UpdateServiceController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateServiceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateServiceController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int serviceID = Integer.parseInt(request.getParameter("serviceID"));
            ServiceDAO dao = new ServiceDAO();
            Service service = dao.getServiceByID(serviceID);

            if (service != null) {
                request.setAttribute("service", service);
                request.getRequestDispatcher("updateService.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Dịch vụ không tồn tại!");
                response.sendRedirect("MechanicDashboard.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("MechanicDashboard.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            int serviceID = Integer.parseInt(request.getParameter("serviceID"));
            String serviceName = request.getParameter("serviceName");
            String hourlyRate = request.getParameter("hourlyRate");

            Service service = new Service(serviceID, serviceName, hourlyRate);
            ServiceDAO serviceDAO = new ServiceDAO();
            serviceDAO.updateService(service);

            // Lưu thông báo vào session
            request.getSession().setAttribute("successMessage", "Cập nhật dịch vụ thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Lỗi khi cập nhật dịch vụ: " + e.getMessage());
        }
        response.sendRedirect("MechanicDashboard.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
