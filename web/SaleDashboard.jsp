<%@page import="model.Parts"%>
<%@page import="java.util.Map"%>
<%@page import="model.Car"%>
<%@page import="model.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Saler"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap">
        <style>
            * { 
                margin: 0; 
                padding: 0; 
                box-sizing: border-box; 
                font-family: 'Poppins', sans-serif; 
            }

            .search-container {
                display: flex;
                justify-content: center;
                gap: 10px; /* Giảm khoảng cách giữa các form */
                flex-wrap: wrap;
                margin-top: 20px;
            }

            .search-container form {
                display: flex;
                gap: 5px; /* Giảm khoảng cách giữa các input và button */
                align-items: center;
            }

            input[type="text"], 
            input[type="submit"] { 
                padding: 8px; /* Giảm padding để input nhỏ hơn */
                width: 220px; /* Giảm chiều rộng để form gọn hơn */
                border: 1px solid #ccc; 
                border-radius: 5px; 
            }

            input[type="submit"] { 
                background: #007bff; 
                color: white; 
                cursor: pointer; 
                transition: background 0.3s ease; 
            }

            input[type="submit"]:hover { 
                background: #0056b3; 
            }


            body { 
                background: #f4f7f9; 
                color: #333; 
                padding-bottom: 50px; 
            }

            .header-container { 
                display: flex; 
                justify-content: flex-end; 
                align-items: center; 
                padding: 10px 20px; 
                background: linear-gradient(to right, #007bff, #0056b3); 
                box-shadow: 0px 3px 5px rgba(0, 0, 0, 0.1); 
            }

            .nav-links { 
                display: flex; 
                gap: 15px; 
                margin-left: auto; 
            }

            .nav-links a { 
                text-decoration: none; 
                padding: 8px 15px; 
                font-size: 14px; 
                color: white; 
                font-weight: 500; 
                transition: background 0.3s ease, transform 0.2s ease; 
                border-radius: 4px; 
            }

            .nav-links a:hover { 
                background: rgba(255, 255, 255, 0.2); 
                transform: scale(1.05); 
            }

            .logout { 
                background: #dc3545 !important; 
                padding: 8px 12px !important; 
                font-size: 13px !important; 
            }

            .logout:hover { 
                background: #b02a37 !important; 
            }

            .dashboard-container { 
                text-align: center; 
                padding: 30px; 
            }

            h1, h2 { 
                color: black; 
            }

            form { 
                margin: 20px auto; 
                display: flex; 
                justify-content: center; 
                gap: 10px; 
            }

            table { 
                width: 90%; 
                margin: 30px auto; 
                border-collapse: collapse; 
                border-radius: 10px; 
                overflow: hidden; 
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.15); 
                background: white; 
            }

            th { 
                background: linear-gradient(to right, #007bff, #0056b3); 
                color: white; 
                font-size: 1.1rem; 
                padding: 15px; 
                text-transform: uppercase; 
            }

            td { 
                padding: 12px; 
                text-align: center; 
                font-size: 0.95rem; 
                border-bottom: 1px solid #ddd; 
                transition: background 0.3s ease; 
            }

            tr:hover td { 
                background: #f1faff; 
            }

            tr:nth-child(even) { 
                background: #f9f9f9; 
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

        <!-- Header -->
        <div class="header-container">
            <h2>Welcome, <%= kq.getSalesName()%></h2>
            <div class="nav-links">
                <a href="StatisticsServlet" class="btn btn-primary">View Report</a>
                <a class="btn btn-danger ms-3" href="LogoutServlet?role=sale">Logout</a>
            </div>
            <div class="col-md-8">

                <%
                    Map<Integer, Integer> salesData = (Map<Integer, Integer>) request.getAttribute("salesData");
                    if (salesData != null) {
                %>
                <table class="table table-bordered mt-3">
                    <thead>
                        <tr>
                            <th>Year</th>
                            <th>Cars Sold</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Map.Entry<Integer, Integer> entry : salesData.entrySet()) {%>
                        <tr>
                            <td><%= entry.getKey()%></td>
                            <td><%= entry.getValue()%></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <% } %>
            </div>
        </div>

        <div style="text-align: center; margin-top: 20px;">
            <a href="addCar.jsp" style="
               display: inline-block;
               background: #28a745;
               color: white;
               padding: 10px 20px;
               text-decoration: none;
               font-weight: bold;
               border-radius: 5px;
               transition: background 0.3s ease;">
                + Add Car
            </a>


            <a href="addPart.jsp" style="
               display: inline-block;
               background: #28a745;
               color: white;
               padding: 10px 20px;
               text-decoration: none;
               font-weight: bold;
               border-radius: 5px;
               transition: background 0.3s ease;">
                + Add Part
            </a>
        </div>

        <% if (request.getAttribute("message") != null) {%>
        <p style="color: green; font-weight: bold; text-align: center; margin-top: 10px;">
            <%= request.getAttribute("message")%>
        </p>
        <% } %>

        <% if (request.getAttribute("error") != null) {%>
        <p style="color: red; font-weight: bold; text-align: center; margin-top: 10px;">
            <%= request.getAttribute("error")%>
        </p>
        <% }%>

        <%-- Hiển thị thông báo nếu có --%>
        <%
            String message = (String) session.getAttribute("message");
            String error = (String) session.getAttribute("error");

            if (message != null) {
        %>
        <div style="color: green; font-weight: bold; text-align: center; margin-top: 10px;">
            <%= message%>
        </div>
        <%
                session.removeAttribute("message"); // Xóa sau khi hiển thị
            }

            if (error != null) {
        %>
        <div style="color: red; font-weight: bold; text-align: center; margin-top: 10px;">
            <%= error%>
        </div>
        <%
                session.removeAttribute("error"); // Xóa sau khi hiển thị
            }
        %>



        <!-- Search Forms -->
        <div class="search-container">
            <!-- Search Customer -->
            <form action="FindCustNameServlet" method="GET">
                <input type="text" name="custName" placeholder="Enter customer name"/>
                <input type="submit" value="Find Customer"/>
            </form>

            <form action="FindPartServlet" method="GET">
                <input type="text" name="partName" 
                       placeholder="Enter part name"/>
                <input type="submit" value="Find Part"/>
            </form>


            <!-- Search Car -->
            <form action="FindCarServlet" method="GET">
                <input type="text" name="serialNumber" placeholder="Enter Serial Number"/>
                <input type="text" name="model" placeholder="Enter Model"/>
                <input type="text" name="year" placeholder="Enter Year"/>
                <input type="submit" value="Find Car"/>
            </form>
        </div>


        <%
            ArrayList<Customer> listCustomer = (ArrayList<Customer>) session.getAttribute("listCustomerName");
            ArrayList<Car> listCar = (ArrayList<Car>) session.getAttribute("listCar");
            ArrayList<Parts> listPart = (ArrayList<Parts>) session.getAttribute("listParts");

            // Xóa session cũ để tránh trùng lặp
            session.removeAttribute("listCustomerName");
            session.removeAttribute("listCar");
            session.removeAttribute("listParts");
        %>

        <% if (listCustomer != null && !listCustomer.isEmpty()) { %>
        <table>
            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Phone</th>
                <th>Sex</th>
                <th>Address</th>
            </tr>
            <% for (Customer customer : listCustomer) {%>
            <tr>
                <td><%= customer.getCustID()%></td>
                <td><%= customer.getCustName()%></td>
                <td><%= customer.getPhone()%></td>
                <td><%= customer.getSex()%></td>
                <td><%= customer.getCustAddress()%></td>
            </tr>
            <% } %>

        </table>
        <% } %>

        <% if (listCar != null && !listCar.isEmpty()) { %>
        <table>
            <tr>
                <th>Car ID</th>
                <th>Serial Number</th>
                <th>Model</th>
                <th>Color</th>
                <th>Year</th>
                <th>Action</th>
            </tr>
            <% for (Car car : listCar) {%>
            <tr>
                <td><%= car.getCarID()%></td>
                <td><%= car.getSerialNumber()%></td>
                <td><%= car.getModel()%></td>
                <td><%= car.getColour()%></td>
                <td><%= car.getYear()%></td>
                <td>
                    <form action="updateCar.jsp" method="GET" style="display:inline;">
                        <input type="hidden" name="carID" value="<%= car.getCarID()%>"/>
                        <input type="submit" value="Update"/>
                    </form>
                        
                        <!-- Form Delete -->
                    <form action="DeleteCarServlet" method="POST" style="display:inline;">
                        <input type="hidden" name="carID" value="<%= car.getCarID()%>"/>
                        <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this car?');"/>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <% }%>

        <% if (listPart != null && !listPart.isEmpty()) { %>
        <table>
            <tr>
                <th>Part ID</th>
                <th>Part Name</th>
                <th>Purchase Price</th>
                <th>Retail Price</th>
                <th>Action</th> 
            </tr>
            <% for (Parts part : listPart) {%>
            <tr>
                <td><%= part.getPartID()%></td>
                <td><%= part.getPartName()%></td>
                <td><%= part.getPurchasePrice()%></td>
                <td><%= part.getRetailPrice()%></td>
                <td>
                    <!-- Form Update -->
                    <form action="updatePart.jsp" method="GET" style="display:inline;">
                        <input type="hidden" name="partID" value="<%= part.getPartID()%>"/>
                        <input type="submit" value="Update"/>
                    </form>

                    <!-- Form Delete -->
                    <form action="DeletePartServlet" method="POST" style="display:inline;">
                        <input type="hidden" name="partID" value="<%= part.getPartID()%>"/>
                        <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this part?');"/>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <% }%>
    </body>
</html>