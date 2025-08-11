<%@page import="model.Parts"%>
<%@page import="dao.PartDAO"%>
<%@page import="model.Saler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Lấy partID từ request
    String partID = request.getParameter("partID");

    // Lấy thông tin bộ phận từ DAO
    PartDAO partDAO = new PartDAO();
    Parts part = partDAO.getPartById(partID);
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Update Part</title>
        <style>
            .update-part-form {
                width: 400px;
                margin: 30px auto;
                padding: 20px;
                background: white;
                border-radius: 8px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.15);
            }

            .update-part-form h2 {
                text-align: center;
                color: #333;
            }

            .update-part-form label {
                display: block;
                font-weight: 500;
                margin-top: 10px;
            }

            .update-part-form input {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            .update-part-form input[type="submit"] {
                background: #28a745;
                color: white;
                cursor: pointer;
                margin-top: 15px;
                border: none;
            }

            .update-part-form input[type="submit"]:hover {
                background: #218838;
            }

            .update-part-form .btn {
                display: inline-block;
                text-align: center;
                background: #6c757d;
                color: white;
                padding: 8px 16px;
                margin-top: 10px;
                text-decoration: none;
                border-radius: 5px;
                transition: background 0.3s ease-in-out;
            }

            .update-part-form .btn:hover {
                background: #5a6268;
            }
        </style>
    </head>
    <body>
        <%
            // Kiểm tra đăng nhập
            Saler kq = (Saler) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("saleLogin.jsp").forward(request, response);
                return;
            }
        %>

        <h2>Update Part Details</h2>
        <form action="UpdatePartServlet" method="post" class="update-part-form">

            <input type="hidden" name="partID" value="<%= part.getPartID()%>" required>

            <label>Part Name:</label>
            <input type="text" name="partName" value="<%= part.getPartName()%>" required>

            <label>Purchase Price:</label>
            <input type="number" name="purchasePrice" value="<%= part.getPurchasePrice()%>" required min="0">

            <label>Retail Price:</label>
            <input type="number" name="retailPrice" value="<%= part.getRetailPrice()%>" required min="0">

            <input type="submit" value="Update Part">

            <a href="SaleDashboard.jsp" class="btn">Back</a>
            <% String errorMessage = (String) request.getAttribute("error"); %>
            <% if (errorMessage != null) {%>
            <div style="color: red; font-weight: bold;"><%= errorMessage%></div>
            <% }%>
        </form>
    </body>
</html>
