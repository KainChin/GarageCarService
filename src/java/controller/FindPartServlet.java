package controller;

import dao.PartDAO;
import dao.SalerDAO;
import model.Parts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet to find parts by name.
 *
 * @author this pc
 */
public class FindPartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("utf-8");
            // Get partName from request
            String partName = request.getParameter("partName");

            // Get session and check user login
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                // Call DAO to search for parts
                SalerDAO dao = new SalerDAO();
                ArrayList<Parts> result = dao.getPartByName(partName);

                // Store result in session
                session.setAttribute("listParts", result);

                // Forward to JSP with query data
                request.getRequestDispatcher("SaleDashboard.jsp?findpart=" + partName)
                        .forward(request, response);
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
        return "Servlet to find parts by name";
    }
}
