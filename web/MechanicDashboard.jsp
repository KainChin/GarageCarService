<%@page import="java.util.ArrayList"%>
<%@page import="model.ServiceTicket"%>
<%@page import="dao.ServiceMechanicDAO"%>
<%@page import="model.ServiceMechanic"%>
<%@page import="model.Mechanic"%>
<%@page import="model.Service"%>
<%@page import="java.util.List" %>
<%@page import="dao.ServiceDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Kiểm tra đăng nhập
    Mechanic kq = (Mechanic) session.getAttribute("user");
    if (kq == null) {
        request.setAttribute("error", "You must login");
        request.getRequestDispatcher("mechanicLogin.jsp").forward(request, response);
        return;
    }

    // Kiểm tra xem người dùng đã nhấn "View Service" chưa
    String view = request.getParameter("view");
    List<Service> services = new ArrayList<>();
    if ("true".equals(view)) {
        ServiceDAO dao = new ServiceDAO();
        services = dao.getList();
    }
    //Hiển thị danh sách service ticket 
    List<ServiceMechanic> listSM = (List<ServiceMechanic>) request.getAttribute("dataSM");

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mechanic Dashboard</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>

        <div class="container mt-5">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <span class="navbar-brand">Welcome, <%= kq.getMechanicName()%>!</span>
                <a class="btn btn-danger" href="LogoutServlet?role=mechanic">Đăng Xuất</a>
            </div>

            <h1 class="text-center">Mechanic Dashboard</h1>
            
            <ul>

                <div class="d-flex justify-content-center gap-3 mb-3">
                    <a href="MechanicDashboard.jsp?view=true" class="btn btn-primary btn-custom">View Service</a>
                    <a href="ServiceTicketServlet?view=tickets" class="btn btn-primary btn-custom">Search service ticket </a>
                    <a href="ListServiceMechanic?mechanicID=<%= kq.getMechanicID()%>" class="btn btn-primary">View Service Ticket</a>
                </div>
            </ul>
                
            <!-- hiển thị thông báo khi thêm dịch vụ thành công hay lỗi -->
            <% if (session.getAttribute("successMessage") != null) {%>
            <p style="color: green;"><%= session.getAttribute("successMessage")%></p>
            <% session.removeAttribute("successMessage"); %>
            <% } %>
            <!-- hiển thị thông báo khi dịch vụ không tồn tại-->
            <% if (session.getAttribute("errorMessage") != null) {%>
            <p style="color: red;"><%= session.getAttribute("errorMessage")%></p>
            <% session.removeAttribute("errorMessage"); %>
            <% }%>

            <!-- Hiển thị thông báo khi addservice -->
            <p style="color: red;">${error}</p>
            <p style="color: green;">${success}</p>


            <!-- Hiển thị danh sách Service -->
            <% if (services != null && !services.isEmpty()) { %>
            <h2 class="mt-4">Danh Sách Dịch Vụ</h2>
            <table class="table table-bordered text-center">
                <thead class="table-dark">
                    <tr>
                        <th>Mã Dịch Vụ</th>
                        <th>Tên Dịch Vụ</th>
                        <th>Giá (VNĐ/giờ)</th>
                        <th>Trạng Thái</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Service c : services) {%>
                    <tr>
                        <td><%= c.getServiceID()%></td>
                        <td><%= c.getServiceName()%></td>
                        <td><%= c.getHourlyRate()%> VNĐ</td>
                        <td>
                            <a href="addService.jsp" class="btn btn-success btn-custom">
                                <i class="bi bi-plus-circle"></i> Add Service
                            </a>
                            <a href="UpdateServiceController?serviceID=<%= c.getServiceID()%>" class="btn btn-primary btn-sm">Cập Nhật</a>
                            <form action="DeleteServiceController" method="post" style="display:inline;" 
                                  onsubmit="return confirm('Bạn có chắc chắn muốn xóa dịch vụ này?')">
                                <input type="hidden" name="serviceID" value="<%= c.getServiceID()%>">
                                <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                            </form>
                        </td>

                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } else if ("true".equals(view)) { %>
            <p class="text-center text-danger">Không có dịch vụ nào!</p>
            <% }%>


            <!-- Hiển thị danh sách Service Mechanic -->
            <% if (listSM != null && !listSM.isEmpty()) { %>
            <h2 class="mt-4">Danh Sách Service Mechanic</h2>
            <table class="table table-hover table-bordered">
                <thead>
                    <tr class="text-center">
                        <th>Ticket ID</th>
                        <th>Service ID</th>
                        <th>Mechanic ID</th>
                        <th>Hours</th>
                        <th>Comment</th>
                        <th>Rate</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${dataSM}" var="l">
                        <tr>
                            <td class="text-center">${l.serviceTicketID}</td>
                            <td class="text-center">${l.serviceID}</td>
                            <td class="text-center">${l.mechanicID}</td>
                            <td class="text-center">${l.hours}</td>
                            <td class="text-center">${l.comment}</td>
                            <td class="text-center">${l.rate} VNĐ</td>
                            <td class="text-center">
                                <a href="UpdateServiceMechanic?serviceTicketID=${l.serviceTicketID}&serviceID=${l.serviceID}" 
                                   class="btn btn-primary btn-sm btn-action">
                                    <i class="bi bi-pencil-square"></i> Update
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
            <% } else if (listSM != null) { %>
            <p class="text-center text-danger">Không có service ticket nào!</p>
            <% }%>

            <% if ("tickets".equals(view)) { %>
            <h2><i class="bi bi-tools"></i>Danh sách vé dịch vụ</h2>

            <!-- Form tìm kiếm -->
            <form action="ServiceTicketServlet" method="GET" class="search-form">
                <input type="hidden" name="view" value="tickets">

                <label for="custID">Customer ID:</label>
                <input type="text" name="custID" id="custID">

                <label for="carID">Car ID:</label>
                <input type="text" name="carID" id="carID">

                <label for="dateReceived">Date Received:</label>
                <input type="date" name="dateReceived" id="dateReceived">

                <button type="submit">Tìm kiếm</button>
            </form>

            <div class="table-responsive">
                <table class="table table-hover table-bordered text-center">
                    <tr>
                        <th>Service Ticket ID</th>
                        <th>Date Received</th>
                        <th>Date Returned</th>
                        <th>Customer ID</th>
                        <th>Car ID</th>
                    </tr>
                    <%
                        List<ServiceTicket> list = (List<ServiceTicket>) request.getAttribute("listTickets");
                        if (list != null && !list.isEmpty()) {
                            for (ServiceTicket ticket : list) {
                    %>
                    <tr>
                        <td><%= ticket.getServiceTicketID()%></td>
                        <td><%= ticket.getDateReceived()%></td>
                        <td><%= ticket.getDateReturned()%></td>
                        <td><%= ticket.getCustID()%></td>
                        <td><%= ticket.getCarID()%></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr><td colspan="6">Không tìm thấy kết quả phù hợp.</td></tr>
                    <% } %>
                </table>
                <% }%>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>


