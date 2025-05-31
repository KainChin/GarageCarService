package controller;

import dao.ServiceDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Service;

@WebServlet(name = "AddServiceController", urlPatterns = {"/AddServiceController"})
public class AddServiceController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try {
            
            int serviceID = Integer.parseInt(request.getParameter("serviceID"));
            String serviceName = request.getParameter("serviceName");
            String hourlyRate = request.getParameter("hourlyRate"); 

            ServiceDAO dao = new ServiceDAO();
            if (dao.getServiceByID(serviceID) != null) {
                request.setAttribute("error", "Mã dịch vụ đã tồn tại! Vui lòng chọn mã khác.");
                request.getRequestDispatcher("MechanicDashboard.jsp").forward(request, response);
                return;
            }
            Service service = new Service(serviceID, serviceName, hourlyRate);
            dao.addService(service);

            request.setAttribute("success", "Thêm dịch vụ thành công!");
            request.getRequestDispatcher("MechanicDashboard.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.getRequestDispatcher("MechanicDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("MechanicDashboard.jsp").forward(request, response);
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
        return "Servlet xử lý thêm dịch vụ";
    }
}
