package controller;

import dao.ChangeProfileCustDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChangeProfileCustServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        Customer currentCustomer = (Customer) session.getAttribute("user");

        if (currentCustomer == null) {
            session.setAttribute("error", "You must login.");
            response.sendRedirect("customerLogin.jsp");
            return;
        }

        List<String> errors = new ArrayList<>();

        try {
            int custID = Integer.parseInt(request.getParameter("custID"));
            String custName = request.getParameter("custName").trim();
            String phone = request.getParameter("phone").trim();
            String sex = request.getParameter("sex").trim();
            String custAddress = request.getParameter("custAddress").trim();

            // Kiểm tra địa chỉ hợp lệ
            String checkAdd = "^[A-Za-zÀ-Ỵà-ỵ0-9\\s,-]+$";
            if (!custAddress.matches(checkAdd)) {
                errors.add("Địa chỉ chưa nhập đúng định dạng.");
            }

            // Kiểm tra số điện thoại: không bắt đầu bằng 0 và có đúng 9 chữ số
            if (!phone.matches("^[1-9][0-9]{8}$")) {
                errors.add("Số điện thoại phải gồm 9 chữ số và không được bắt đầu bằng số 0.");
            }

            // Nếu có lỗi, hiển thị lỗi và quay lại trang update
            if (!errors.isEmpty()) {
                session.setAttribute("updateErrors", errors);
                request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
                return;
            }

            // Kiểm tra nếu không có thay đổi
            if (custName.equals(currentCustomer.getCustName())
                    && phone.equals(currentCustomer.getPhone())
                    && sex.equals(currentCustomer.getSex())
                    && custAddress.equals(currentCustomer.getCustAddress())) {
                session.setAttribute("message", "Không có thay đổi nào! Hồ sơ không được cập nhật.");
            } else {
                // Cập nhật thông tin
                ChangeProfileCustDAO dao = new ChangeProfileCustDAO();
                Customer updatedCustomer = new Customer(custID, custName, phone, sex, custAddress);

                if (dao.updateCustomer(updatedCustomer)) {
                    session.setAttribute("user", updatedCustomer);
                    session.setAttribute("message", "Cập nhật hồ sơ thành công!");
                } else {
                    errors.add("Cập nhật thất bại! Vui lòng thử lại.");
                    session.setAttribute("updateErrors", errors);
                }
            }
            request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("UpdateProfile.jsp");
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
        return "Change Profile Servlet";
    }
}
