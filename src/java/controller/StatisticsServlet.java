/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CarSalesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BestSellingCarModel;
import model.BestUsedPart;
import model.CarRevenueByYear;
import model.CarSalesByYear;
import model.PartRevenue;
import model.TopMechanic;

/**
 *
 * @author Dell
 */
@WebServlet(name = "StatisticsServlet", urlPatterns = {"/StatisticsServlet"})
public class StatisticsServlet extends HttpServlet {

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
            out.println("<title>Servlet StatisticsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticsServlet at " + request.getContextPath() + "</h1>");
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
        
        List<CarSalesByYear> salesData = CarSalesDAO.getCarsSoldByYear();
        List<CarRevenueByYear> revenueData = CarSalesDAO.getCarSalesRevenueByYear();
        List<BestSellingCarModel> bestSellingCars = CarSalesDAO.getBestSellingCarModels();
        List<TopMechanic> topMechanics = CarSalesDAO.getTop3Mechanics();
        List<BestUsedPart> bestUsedParts = CarSalesDAO.getBestUsedParts();
        
        request.setAttribute("salesData", salesData);
        request.setAttribute("revenueData", revenueData);
        request.setAttribute("bestSellingCars", bestSellingCars);
        request.setAttribute("topMechanics", topMechanics);
        request.setAttribute("bestUsedParts", bestUsedParts);
        
        request.getRequestDispatcher("Report.jsp").forward(request, response);
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
