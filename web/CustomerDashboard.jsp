<%@page import="model.InvoiceDetail2"%>
<%@ page import="model.ServiceTicket, model.Customer, java.util.ArrayList, java.util.List, model.InvoiceDetail, model.Invoice" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Customer Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <%
            Customer kq = (Customer) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("customerLogin.jsp").forward(request, response);
                return;
            }
        %>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary p-3">
            <div class="container-fluid">
                <span class="navbar-brand">Welcome, <%= kq.getCustName()%>!</span>
                <div class="navbar-nav ms-auto">
                    <a class="nav-link text-white" href="ServiceTicketCustServlet">View Service Ticket</a>
                    <a class="nav-link text-white" href="InvoiceCustServlet">View Invoices</a>
                    <a class="nav-link text-white" href="UpdateProfile.jsp">Change Profile</a>
                    <a class="btn btn-danger ms-3" href="LogoutServlet?role=customer">Logout</a> 
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h1 class="text-center">Service Ticket & Invoice</h1>
            <!-- Hiển thị thông báo khi không có dịch vụ hoặc phụ tùng -->
            <%
                String message = (String) session.getAttribute("message");
                if (message != null) {
            %>
            <div class="alert alert-success text-center" role="alert"><%= message%></div>
            <%
                    session.removeAttribute("message");
                }
            %>


            <% ArrayList<Invoice> list = (ArrayList<Invoice>) request.getAttribute("listInvoice");
                if (list != null && !list.isEmpty()) { %>
            <table class="table table-bordered table-striped mt-4">
                <thead class="table-primary text-center">
                    <tr>
                        <th>Invoice ID</th>
                        <th>Invoice Date</th>
                        <th>Serial Number</th>
                        <th>Model</th>
                        <th>Colour</th>
                        <th>Year</th>
                        <th>Total Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Invoice invoice : list) {%>
                    <tr>
                        <td class="text-center"><%= invoice.getInvoiceID()%></td>
                        <td class="text-center"><%= invoice.getInvoiceDate()%></td>
                        <td class="text-center"><%= invoice.getSerialNumber()%></td>
                        <td class="text-center"><%= invoice.getModel()%></td>
                        <td class="text-center"><%= invoice.getColour()%></td>
                        <td class="text-center"><%= invoice.getYear()%></td>
                        <td class="text-center"><%= String.format("%,.0f", invoice.getTotalPrice())%> VND</td>
                        <td class="text-center">
                            <a href="InvoiceDetailServlet?invoiceID=<%= invoice.getInvoiceID()%>" class="btn btn-info">Details</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } %>

            <%
                List<InvoiceDetail> invoices = (List<InvoiceDetail>) request.getAttribute("serviceDetails");
                List<InvoiceDetail2> invoices2 = (List<InvoiceDetail2>) request.getAttribute("partDetails");

                if (invoices != null && !invoices.isEmpty()) {
            %>
            <h2 class="mt-5">Service Used</h2>
            <table class="table table-bordered table-striped">
                <thead class="table-primary text-center">
                    <tr>
                        <th>Service Name</th>
                        <th>Hours</th>
                        <th>Hourly Rate</th>
                        <th>Service Cost</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (InvoiceDetail invoice : invoices) {%>
                    <tr>
                        <td><%= invoice.getServiceName()%></td>
                        <td class="text-center"><%= invoice.getHours()%></td>
                        <td class="text-center"><%= String.format("%,.0f", invoice.getHourlyRate())%> VND</td>
                        <td class="text-center"><%= String.format("%,.0f", invoice.getServiceCost())%> VND</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } %>

            <% if (invoices2 != null && !invoices2.isEmpty()) { %>
            <h2 class="mt-5">Parts Used</h2>
            <table class="table table-bordered table-striped">
                <thead class="table-primary text-center">
                    <tr>
                        <th>Part Name</th>
                        <th>Number Used</th>
                        <th>Price</th>
                        <th>Part Total Price</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (InvoiceDetail2 invoice : invoices2) {%>
                    <tr>
                        <td><%= invoice.getPartName()%></td>
                        <td class="text-center"><%= invoice.getNumberUsed()%></td>
                        <td class="text-center"><%= String.format("%,.0f", invoice.getPrice())%> VND</td>
                        <td class="text-center"><%= String.format("%,.0f", invoice.getPartTotalPrice())%> VND</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } %>


            <% ArrayList<ServiceTicket> list1 = (ArrayList<ServiceTicket>) request.getAttribute("listServiceTicket");
                if (list1 != null && !list1.isEmpty()) { %>
            <table class="table table-bordered table-striped mt-4">
                <thead class="table-primary text-center">
                    <tr>
                        <th>Service Ticket ID</th>
                        <th>Date Received</th>
                        <th>Date Returned</th>
                        <th>Car ID</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (ServiceTicket in : list1) {%>
                    <tr>
                        <td class="text-center"><%= in.getServiceTicketID()%></td>
                        <td class="text-center"><%= in.getDateReceived()%></td>
                        <td class="text-center"><%= in.getDateReturned()%></td>
                        <td class="text-center"><%= in.getCarID()%></td>
                        <td class="text-center">
                            <a href="#" class="btn btn-info">Details</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% }%>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
