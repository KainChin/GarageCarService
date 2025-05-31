<%@page import="model.Saler"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
    .part-form {
        width: 400px;
        margin: 30px auto;
        padding: 20px;
        background: white;
        border-radius: 8px;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.15);
    }

    .part-form label {
        display: block;
        font-weight: 500;
        margin-top: 10px;
    }

    .part-form input {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .part-form input[type="submit"] {
        background: #007bff;
        color: white;
        cursor: pointer;
        margin-top: 15px;
    }

    .part-form input[type="submit"]:hover {
        background: #0056b3;
    }

    .part-form .btn {
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

    .part-form .btn:hover {
        background: #5a6268;
    }

</style>

<%
    // Kiểm tra xem user đã đăng nhập chưa
    Saler kq = (Saler) session.getAttribute("user");
    if (kq == null) {
        request.setAttribute("error", "You must login");
        request.getRequestDispatcher("saleLogin.jsp").forward(request, response);
        return;
    }
%>

<form action="AddPartServlet" method="post" class="part-form">
    <h2>Add New Part</h2>

    <label>Part ID:</label>
    <input type="number" name="partID" required min="0">

    <label>Part Name:</label>
    <input type="text" name="partName" required>

    <label>Purchase Price:</label>
    <input type="number" step="0.01" name="purchasePrice" required min="0">

    <label>Retail Price:</label>
    <input type="number" step="0.01" name="retailPrice" required min="0">

    <input type="submit" value="Add Part">

    <a href="SaleDashboard.jsp" class="btn">Back</a>
</form>
