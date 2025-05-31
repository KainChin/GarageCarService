/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.Car;
import model.Saler;

/**
 *
 * @author this pc
 */
public class FindCarServlet extends HttpServlet {

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

        String serialNumber = request.getParameter("serialNumber");
        String model = request.getParameter("model");
        String yearStr = request.getParameter("year");
        int year = 0;

        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            year = 0;
        }

        HttpSession session = request.getSession();
        Saler kq = (Saler) session.getAttribute("user");

        if (kq != null) {
            String salesid = kq.getSalesID();
            SalerDAO d = new SalerDAO();
            ArrayList<Car> rs = d.getCar(serialNumber, model, year);

            if (rs.isEmpty()) {
                // Nếu không tìm thấy xe, hiển thị thông báo
                session.setAttribute("error", "No cars found!");
            } else {
                session.setAttribute("listCar", rs);
            }

            request.getRequestDispatcher("SaleDashboard.jsp?findcar=" + serialNumber + "" + model + "" + year)
                    .forward(request, response);
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
        processRequest(request, response);
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