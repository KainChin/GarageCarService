/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author this pc
 */
public class UpdatePartServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdatePartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePartServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
            // Lấy dữ liệu từ request
            request.setCharacterEncoding("utf-8");
            String partID = request.getParameter("partID");
            String partName = request.getParameter("partName");
            String purchasePriceStr = request.getParameter("purchasePrice");
            String retailPriceStr = request.getParameter("retailPrice");

            // Kiểm tra xem purchasePrice và retailPrice có phải số không
            try {
                Double.parseDouble(purchasePriceStr);
                Double.parseDouble(retailPriceStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Purchase Price and Retail Price must be numbers!");
                request.getRequestDispatcher("updatePart.jsp").forward(request, response);
                return;
            }

            // Gọi DAO để cập nhật dữ liệu
            PartDAO partDAO = new PartDAO();
            partDAO.updatePart(partID, partName, purchasePriceStr, retailPriceStr);

            // Thông báo cập nhật thành công
            request.getSession().setAttribute("message", "Part updated successfully!");
            response.sendRedirect("SaleDashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to update part! Please try again.");
            request.getRequestDispatcher("updatePart.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet to update Part details";
    }
}
