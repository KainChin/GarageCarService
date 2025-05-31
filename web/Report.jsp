<%@page import="model.Saler"%>
<%@page import="model.BestUsedPart"%>
<%@page import="model.CarSalesByYear"%>
<%@page import="model.CarRevenueByYear"%>
<%@page import="model.BestSellingCarModel"%>
<%@page import="model.PartRevenue"%>
<%@page import="java.util.List"%>
<%@page import="model.TopMechanic"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Car Sales Statistics</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            body {
                background-color: #e3f2fd; /* Nền xanh nhạt */
            }
            .container {
                margin-top: 20px;
            }
            .stats-container {
                display: grid;
                grid-template-columns: repeat(2, 1fr);
                gap: 15px;
            }
            .card {
                padding: 10px;
                font-size: 14px;
                background-color: #bbdefb; /* Xanh dương nhạt */
                border: 1px solid #64b5f6; /* Viền xanh đậm hơn */
                border-radius: 8px;
                box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
            }
            .card h5 {
                color: #0d47a1; /* Xanh dương đậm */
            }
            .table-container {
                max-height: 250px;
                overflow-y: auto;
            }
            table {
                font-size: 12px;
            }
            th, td {
                padding: 5px;
                text-align: center;
            }
            th {
                background: #0d47a1; /* Xanh đậm */
                color: white;
            }
            tr:hover {
                background-color: #90caf9; /* Hiệu ứng hover */
            }

        </style>
    </head>
    <body>
        <%
            Saler kq = (Saler) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("saleLogin.jsp").forward(request, response);
                return;
            }
        %>
        <div class="container">
            <h2 class="text-center mb-4">Car Sales Statistics</h2>
            <div class="stats-container">
                <div class="card">
                    <h5 class="text-center">Cars Sold by Year</h5>
                    <div class="table-container">
                        <table class="table table-striped">
                            <thead>
                                <tr><th>Year</th><th>Cars Sold</th></tr>
                            </thead>
                            <tbody>
                                <% List<CarSalesByYear> salesData = (List<CarSalesByYear>) request.getAttribute("salesData");
                                    if (salesData != null) {
                                        for (CarSalesByYear entry : salesData) {%>
                                <tr><td><%= entry.getYear()%></td><td><%= entry.getTotalSold()%></td></tr>
                                <% }
                                    } %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="card">
                    <h5 class="text-center">Revenue by Year</h5>
                    <div class="table-container">
                        <table class="table table-striped">
                            <thead>
                                <tr><th>Year</th><th>Revenue (VND)</th></tr>
                            </thead>
                            <tbody>
                                <%
                                    List<CarRevenueByYear> revenueData = (List<CarRevenueByYear>) request.getAttribute("revenueData");
                                    if (revenueData != null) {
                                        for (CarRevenueByYear entry : revenueData) {
                                %>
                                <tr>
                                    <td><%= entry.getYear()%></td>
                                    <td><%= String.format("%,.0f", entry.getRevenue())%> VND</td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                            </tbody>

                        </table>
                    </div>
                </div>
                <div class="card">
                    <h5 class="text-center">Best Selling Car Models</h5>
                    <div class="table-container">
                        <table class="table table-striped">
                            <thead>
                                <tr><th>Model</th><th>Total Sold</th></tr>
                            </thead>
                            <tbody>
                                <% List<BestSellingCarModel> bestSellingCars = (List<BestSellingCarModel>) request.getAttribute("bestSellingCars");
                                    if (bestSellingCars != null) {
                                        for (BestSellingCarModel entry : bestSellingCars) {%>
                                <tr><td><%= entry.getModel()%></td><td><%= entry.getTotalSold()%></td></tr>
                                <% }
                                    } %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="card">
                    <h5 class="text-center">Most Used Parts</h5>
                    <div class="table-container">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Part Name</th>
                                    <th>Total Used</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% List<BestUsedPart> bestUsedParts = (List<BestUsedPart>) request.getAttribute("bestUsedParts");
                                    if (bestUsedParts != null) {
                                        for (BestUsedPart entry : bestUsedParts) {%>
                                <tr>
                                    <td><%= entry.getPartName()%></td>
                                    <td><%= entry.getTotalUsed()%></td>
                                </tr>
                                <% }
                                    } %>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
            <div class="card mt-3">
                <h5 class="text-center">Top 3 Mechanics</h5>
                <div class="table-container">
                    <table class="table table-striped">
                        <thead>
                            <tr><th>Mechanic ID</th><th>Mechanic Name</th></tr>
                        </thead>
                        <tbody>
                            <% List<TopMechanic> topMechanics = (List<TopMechanic>) request.getAttribute("topMechanics");
                                if (topMechanics != null) {
                                    for (TopMechanic entry : topMechanics) {%>
                            <tr><td><%= entry.getMechanicID()%></td><td><%= entry.getMechanicName()%></td></tr>
                            <% }
                                }%>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="text-center mt-3">
                <a href="SaleDashboard.jsp" class="btn btn-primary">Back</a>
            </div>
        </div>
    </body>
</html>