<%@page import="model.Mechanic"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Dịch Vụ</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f2f5;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            .container {
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                width: 380px;
                text-align: center;
            }

            h2 {
                color: #333;
                margin-bottom: 15px;
            }

            .form-group {
                margin-bottom: 12px;
                text-align: left;
            }

            label {
                font-weight: bold;
                font-size: 14px;
                display: block;
                margin-bottom: 5px;
            }

            input[type="text"],
            input[type="number"] {
                width: calc(100% - 20px);
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 14px;
                transition: border-color 0.3s;
                display: block;
                margin: 0 auto;
            }

            input:focus {
                border-color: #007bff;
                outline: none;
            }

            .btn {
                width: calc(100% - 20px);
                padding: 10px;
                margin-top: 8px;
                border: none;
                border-radius: 5px;
                font-size: 15px;
                cursor: pointer;
                font-weight: bold;
                transition: background-color 0.3s;
            }

            .btn-primary {
                background-color: #007bff;
                color: white;
            }

            .btn-primary:hover {
                background-color: #0056b3;
            }

            .btn-secondary {
                background-color: #6c757d;
                color: white;
                text-decoration: none;
                display: block;
                text-align: center;
                padding: 10px;
                border-radius: 5px;
                margin-top: 8px;
                font-size: 15px;
                font-weight: bold;
            }

            .btn-secondary:hover {
                background-color: #5a6268;
            }

            .message {
                margin-top: 10px;
                font-weight: bold;
                font-size: 14px;
            }

            .error {
                color: red;
            }

            .success {
                color: green;
            }
        </style>
    </head>
    <body>
        <%
            // Kiểm tra đăng nhập
            Mechanic kq = (Mechanic) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("mechanicLogin.jsp").forward(request, response);
                return;
            }
        %>
        <div class="container">
            <h2>Thêm Dịch Vụ</h2>
            <form action="AddServiceController" method="post">
                <div class="form-group">
                    <label for="serviceID">Mã Dịch Vụ:</label>
                    <input type="number" id="serviceID" name="serviceID" required min="1">
                </div>
                <div class="form-group">
                    <label for="serviceName">Tên Dịch Vụ:</label>
                    <input type="text" id="serviceName" name="serviceName" required>
                </div>
                <div class="form-group">
                    <label for="hourlyRate">Giá Theo Giờ:</label>
                    <input type="number" id="hourlyRate" name="hourlyRate" required min="1">
                </div>
                <button type="submit" class="btn btn-primary">Thêm Dịch Vụ</button>
                <a href="MechanicDashboard.jsp" class="btn btn-secondary">Hủy</a>
            </form>
            <p class="message error">${error}</p>
            <p class="message success">${success}</p>
        </div>
    </body>
</html>