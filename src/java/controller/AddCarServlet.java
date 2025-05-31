package controller;

import dao.CarDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddCarServlet", urlPatterns = {"/AddCarServlet"})
public class AddCarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long carID = Long.parseLong(request.getParameter("carID"));
            String serialNumber = request.getParameter("serialNumber");
            String model = request.getParameter("model");
            String colour = request.getParameter("colour");
            int year = Integer.parseInt(request.getParameter("year"));

            CarDAO carDAO = new CarDAO();
            boolean success = carDAO.addCar(carID, serialNumber, model, colour, year);

            if (success) {
                request.setAttribute("message", "Car added successfully!");
            } else {
                request.setAttribute("error", "Car ID already exists!");
            }
            request.getRequestDispatcher("SaleDashboard.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input! Please enter valid numbers.");
            request.getRequestDispatcher("SaleDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred. Please try again.");
            request.getRequestDispatcher("SaleDashboard.jsp").forward(request, response);
        }
    }

}
