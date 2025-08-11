package controller;

import dao.CarDAO;
import java.io.IOException;
import java.time.Year;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddCarServlet", urlPatterns = {"/AddCarServlet"})
public class AddCarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // UTF-8 cho tiếng Việt
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Lấy & chuẩn hoá input
        String carIDStr = trimOrEmpty(request.getParameter("carID"));
        String serialNumber = trimOrEmpty(request.getParameter("serialNumber")).toUpperCase();
        String model = trimOrEmpty(request.getParameter("model"));
        String colour = trimOrEmpty(request.getParameter("colour"));
        String yearStr = trimOrEmpty(request.getParameter("year"));

        // Đưa lại input để JSP giữ giá trị khi lỗi
        request.setAttribute("carID", carIDStr);
        request.setAttribute("serialNumber", serialNumber);
        request.setAttribute("model", model);
        request.setAttribute("colour", colour);
        request.setAttribute("year", yearStr);

        // Validate cơ bản
        String error = validate(carIDStr, serialNumber, model, colour, yearStr);
        if (error != null) {
            request.setAttribute("errorMessage", error);
            request.getRequestDispatcher("addCar.jsp").forward(request, response);
            return;
        }

        try {
            long carID = Long.parseLong(carIDStr);
            int year = Integer.parseInt(yearStr);

            CarDAO carDAO = new CarDAO();

            // Tuỳ thiết kế DAO: hoặc bạn check exists trước, hoặc DAO trả false khi trùng
            // Ví dụ kiểm tra trước:
            // if (carDAO.existsCarId(carID)) { ... }
            boolean success = carDAO.addCar(carID, serialNumber, model, colour, year);

            if (success) {
                request.setAttribute("successMessage", "Thêm xe thành công!");
                // Xoá giá trị form sau khi thêm thành công
                request.setAttribute("carID", "");
                request.setAttribute("serialNumber", "");
                request.setAttribute("model", "");
                request.setAttribute("colour", "");
                request.setAttribute("year", "");
            } else {
                request.setAttribute("errorMessage", "Car ID đã tồn tại!");
            }

            request.getRequestDispatcher("addCar.jsp").forward(request, response);

        } catch (NumberFormatException nfe) {
            request.setAttribute("errorMessage", "Giá trị số không hợp lệ. Vui lòng kiểm tra lại!");
            request.getRequestDispatcher("addCar.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Có lỗi xảy ra, vui lòng thử lại sau.");
            request.getRequestDispatcher("addCar.jsp").forward(request, response);
        }
    }

    private static String trimOrEmpty(String s) {
        return s == null ? "" : s.trim();
    }

    private static String validate(String carID, String serial, String model, String colour, String year) {
        if (carID.isEmpty() || serial.isEmpty() || model.isEmpty() || colour.isEmpty() || year.isEmpty()) {
            return "Vui lòng nhập đầy đủ thông tin.";
        }
        // carID phải là số nguyên không âm
        try {
            long id = Long.parseLong(carID);
            if (id < 0) {
                return "Car ID phải là số nguyên không âm.";
            }
        } catch (NumberFormatException e) {
            return "Car ID không hợp lệ.";
        }
        // Serial 6–20 ký tự chữ/số/gạch ngang
        if (!serial.matches("[A-Z0-9\\-]{6,20}")) {
            return "Serial Number phải gồm 6–20 ký tự chữ/số (cho phép dấu '-').";
        }
        // Năm 1980 → năm hiện tại + 1
        int thisYearPlusOne = Year.now().getValue() + 1;
        try {
            int y = Integer.parseInt(year);
            if (y < 1980 || y > thisYearPlusOne) {
                return "Năm sản xuất phải trong khoảng 1980 đến " + thisYearPlusOne + ".";
            }
        } catch (NumberFormatException e) {
            return "Năm sản xuất không hợp lệ.";
        }
        return null;
    }
}
