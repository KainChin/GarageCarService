<%@page import="java.util.List"%>
<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Update Profile</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background: linear-gradient(to right, #1e3c72, #2a5298);
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                margin: 0;
                font-family: 'Poppins', sans-serif;
            }
            .container {
                background: white;
                padding: 40px;
                border-radius: 12px;
                box-shadow: 0px 10px 30px rgba(0, 0, 0, 0.2);
                max-width: 500px;
                width: 100%;
                text-align: center;
            }
            .form-control {
                border-radius: 8px;
            }
            .btn-primary, .btn-danger {
                border-radius: 8px;
                padding: 12px;
                font-weight: bold;
                text-transform: uppercase;
            }
            .btn-primary:hover {
                background-color: #2a5298;
            }
            .btn-danger:hover {
                background-color: #b02a37;
            }
        </style>
    </head>
    <body>

        <%
            Customer kq = (Customer) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("customerLogin.jsp").forward(request, response);
            }
        %>

        <div class="container">
            <h2 class="text-primary mb-4">Update Profile</h2>

            <%-- Hiển thị thông báo lỗi nếu có --%>
            <%
                List<String> updateErrors = (List<String>) session.getAttribute("updateErrors");
                if (updateErrors != null && !updateErrors.isEmpty()) {
            %>
            <div class="alert alert-danger" role="alert">
                <% for (String err : updateErrors) {%>
                <p><%= err%></p>
                <% } %>
            </div>

            <%
                    session.removeAttribute("updateErrors");
                }
            %>

            <%-- Hiển thị thông báo thành công hoặc không thay đổi sau khi cập nhật --%>
            <%
                String message = (String) session.getAttribute("message");
                if (message != null) {
            %>
            <div class="alert alert-success" role="alert">
                <%= message%>
            </div>
            <%
                    session.removeAttribute("message");
                }
            %>

            <form action="ChangeProfileCustServlet" method="post">
                <input type="hidden" name="custID" value="<%= kq.getCustID()%>">

                <div class="mb-3 text-start">
                    <label for="custName" class="form-label">Name:</label>
                    <input type="text" class="form-control" id="custName" name="custName" 
                           value="<%= kq.getCustName()%>" required
                           pattern="[A-Za-zÀ-Ỵà-ỵ\s]+" 
                           title="Chỉ nhập chữ cái tiếng Việt, không chứa số hoặc ký tự đặc biệt">
                </div>

                <div class="mb-3 text-start">
                    <label for="custPhone" class="form-label">Phone:</label>
                    <input type="text" class="form-control" id="custPhone" name="phone" value="<%= kq.getPhone()%>" required 
                           pattern="^[1-9][0-9]{8}$"
                           title="Số điện thoại phải gồm 9 chữ số và không bắt đầu bằng số 0.">
                </div>

                <div class="mb-3 text-start">
                    <label for="custSex" class="form-label">Gender:</label>
                    <select class="form-select" id="custSex" name="sex">
                        <option value="M" <%= "M".equals(kq.getSex()) ? "selected" : ""%>>Male</option>
                        <option value="F" <%= "F".equals(kq.getSex()) ? "selected" : ""%>>Female</option>
                    </select>
                </div>

                <div class="mb-3 text-start">
                    <label for="custAddress" class="form-label">Address:</label>
                    <input type="text" class="form-control" id="custAddress" name="custAddress" 
                           value="<%= kq.getCustAddress()%>" required 
                           pattern="[A-Za-zÀ-Ỵà-ỵ0-9\s,-]+" 
                           title="Địa chỉ chỉ được chứa chữ cái, số, khoảng trắng, dấu gạch ngang (-) và dấu phẩy (,)">
                </div>

                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">Update</button>
                    <a href="CustomerDashboard.jsp" class="btn btn-danger">Back</a>
                </div>
            </form>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
