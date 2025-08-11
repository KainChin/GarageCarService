<%@page import="model.Saler"%>
<%@page import="dao.CarDAO"%>
<%@page import="model.Car"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    long carID = Long.parseLong(request.getParameter("carID"));
    CarDAO carDAO = new CarDAO();
    Car car = carDAO.getCarById(carID); // Lấy thông tin xe cần cập nhật
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Update Car</title>
    </head>
    <style>
        .update-car-form {
            width: 400px;
            margin: 30px auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.15);
        }

        .update-car-form h2 {
            text-align: center;
            color: #333;
        }

        .update-car-form label {
            display: block;
            font-weight: 500;
            margin-top: 10px;
        }

        .update-car-form input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .update-car-form input[type="submit"] {
            background: #28a745; /* Màu xanh lá */
            color: white;
            cursor: pointer;
            margin-top: 15px;
            border: none;
        }

        .update-car-form input[type="submit"]:hover {
            background: #218838; /* Màu xanh lá đậm khi hover */
        }

        /* CSS cho nút Back */
        .update-car-form .btn {
            display: inline-block;
            text-align: center;
            background: #6c757d; /* Màu xám nhạt */
            color: white;
            padding: 8px 16px;
            margin-top: 10px;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s ease-in-out;
        }

        .update-car-form .btn:hover {
            background: #5a6268; /* Màu xám đậm khi hover */
        }

    </style>
    <body>
        <%
            Saler kq = (Saler) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("saleLogin.jsp").forward(request, response);
                return;
            }
        %>
        <h2>Update Car Details</h2>
        <form action="UpdateCarServlet" method="post" class="update-car-form">

            <input type="hidden" name="carID" value="<%= car.getCarID()%>" required min="0">

            <input type="hidden" name="serialNumber" value="<%= car.getSerialNumber()%>" readonly required><br>

            <label>Model:</label>
            <input type="text" name="model" value="<%= car.getModel()%>" required><br>

            <label>Colour:</label>
            <input type="text" name="colour" value="<%= car.getColour()%>" required><br>

            <label>Year:</label>
            <input type="number" name="year" value="<%= car.getYear()%>" required min="0"><br>

            <input type="submit" value="Update Car">

            <a href="SaleDashboard.jsp" class="btn">Back</a>
        </form>
    </body>
</html>