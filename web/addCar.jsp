<%@page import="model.Saler"%>
<style>
    .car-form {
        width: 400px;
        margin: 30px auto;
        padding: 20px;
        background: white;
        border-radius: 8px;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.15);
    }

    .car-form label {
        display: block;
        font-weight: 500;
        margin-top: 10px;
    }

    .car-form input {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .car-form input[type="submit"] {
        background: #007bff;
        color: white;
        cursor: pointer;
        margin-top: 15px;
    }

    .car-form input[type="submit"]:hover {
        background: #0056b3;
    }

    .car-form .btn {
        display: inline-block;
        text-align: center;
        background: #6c757d; /* Màu xám nh?t */
        color: white;
        padding: 8px 16px;
        margin-top: 10px;
        text-decoration: none;
        border-radius: 5px;
        transition: background 0.3s ease-in-out;
    }

    .car-form .btn:hover {
        background: #5a6268; /* Màu xám ??m h?n khi hover */
    }

</style>
<%
    Saler kq = (Saler) session.getAttribute("user");
    if (kq == null) {
        request.setAttribute("error", "You must login");
        request.getRequestDispatcher("saleLogin.jsp").forward(request, response);
        return;
    }
%>
<form action="AddCarServlet" method="post" class="car-form">
    <label>Car ID:</label>
    <input type="number" name="carID" required min="0">

    <label>Serial Number:</label>
    <input type="text" name="serialNumber" required>

    <label>Model:</label>
    <input type="text" name="model" required>

    <label>Colour:</label>
    <input type="text" name="colour" required>

    <label>Year:</label>
    <input type="number" name="year" required>

    <input type="submit" value="Add Car">

    <a href="SaleDashboard.jsp" class="btn">Back</a>
</form>

