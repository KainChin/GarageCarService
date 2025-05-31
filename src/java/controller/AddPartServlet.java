package controller;

import dao.PartDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddPartServlet", urlPatterns = {"/AddPartServlet"})
public class AddPartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        try {
            String partID = request.getParameter("partID");
            String partName = request.getParameter("partName");
            String purchasePrice = request.getParameter("purchasePrice");
            String retailPrice = request.getParameter("retailPrice");

            PartDAO partDAO = new PartDAO();
            boolean success = partDAO.addPart(partID, partName, purchasePrice, retailPrice);

            if (success) {
                request.setAttribute("message", "Part added successfully!");
            } else {
                request.setAttribute("error", "Part ID already exists!");
            }
            request.getRequestDispatcher("SaleDashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred. Please try again.");
            request.getRequestDispatcher("SaleDashboard.jsp").forward(request, response);
        }
    }
}
