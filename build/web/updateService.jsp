<%@page import="model.Mechanic"%>
<%@page import="model.Service"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập Nhật Dịch Vụ</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <%
            Mechanic kq = (Mechanic) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("mechanicLogin.jsp").forward(request, response);
                return;
            }
            Service service = (Service) request.getAttribute("service");
        %>

        <div class="container d-flex justify-content-center mt-5">
            <div class="col-md-6 shadow p-4 rounded bg-white">
                <h2 class="text-center mb-4 text-primary">Cập Nhật Dịch Vụ</h2>
            
                <form action="UpdateServiceController" method="POST">
                    <input type="hidden" name="serviceID" value="<%= service != null ? service.getServiceID() : ""%>">

                    <div class="mb-3">
                        <label class="form-label fw-bold">Tên Dịch Vụ</label>
                        <input type="text" class="form-control" name="serviceName" value="<%= service != null ? service.getServiceName() : ""%>" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-bold">Giá (VNĐ/giờ)</label>
                        <input type="number" class="form-control" name="hourlyRate" value="<%= service != null ? service.getHourlyRate() : ""%>" required>
                    </div>

                    <button type="submit" class="btn btn-success w-100">Cập Nhật</button>
                    <a href="MechanicDashboard.jsp" class="btn btn-secondary w-100 mt-2">Hủy</a>
                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>



