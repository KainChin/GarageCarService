<%@page import="model.Parts"%>
<%@page import="java.util.Map"%>
<%@page import="model.Car"%>
<%@page import="model.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Saler"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>FPT Garage • Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">

        <style>
            /* BẢN KHÔNG DÙNG CSS VARIABLES (để NetBeans 8.2 không báo đỏ) */
            *{ box-sizing:border-box; }
            html,body{ margin:0; }
            body{
                background:#f4f7fb; color:#111;
                font-family: Inter, Poppins, -apple-system, Segoe UI, Roboto, Arial, sans-serif;
            }

            /* NAVBAR */
            .navbar{
                position: sticky; top:0; z-index:50;
                /* backdrop-filter có thể bị cảnh báo trên IDE cũ, có thể giữ lại vì trình duyệt hỗ trợ */
                backdrop-filter: blur(6px);
                background:linear-gradient(90deg, #265BA9, #3f76c9);
                color:#fff; border-bottom:1px solid rgba(255,255,255,.2);
                box-shadow:0 8px 20px rgba(0,0,0,.12);
            }
            .nav-wrap{
                max-width:1200px; margin:0 auto; padding:10px 16px;
                display:flex; align-items:center; gap:14px;
            }
            .brand{ display:flex; align-items:center; gap:12px; text-decoration:none; color:#fff; }
            .logo{
                width:42px; height:42px; border-radius:12px; background:rgba(255,255,255,.18);
                display:flex; align-items:center; justify-content:center; font-weight:800;
                box-shadow:inset 0 0 0 2px rgba(255,255,255,.2);
            }
            .brand h1{ font-size:18px; margin:0; font-weight:800; letter-spacing:.2px; }
            .brand .sub{ font-size:11px; opacity:.9; margin-top:-2px; }

            .nav-actions{ margin-left:auto; display:flex; align-items:center; gap:10px; }
            .btn{
                display:inline-flex; align-items:center; gap:8px; padding:10px 12px; border-radius:12px;
                border:1px solid rgba(255,255,255,.25);
                color:#fff; background:rgba(255,255,255,.08); text-decoration:none; font-weight:800; transition:.2s;
            }
            .btn:hover{ background:rgba(255,255,255,.18); }
            .btn.danger{ background:#dc3545; border-color:#dc3545; }
            .btn.danger:hover{ background:#b02a37; }

            /* PAGE */
            .page{ max-width:1200px; margin:18px auto 28px; padding:0 16px; }

            /* WELCOME */
            .welcome{
                background:#ffffff; border:1px solid #e8eef7; border-radius:18px; padding:16px;
                display:flex; align-items:center; justify-content:space-between; gap:16px;
                box-shadow:0 10px 24px rgba(15,23,42,.06);
            }
            .welcome .hi{ font-weight:800; color:#0b1220; margin:0; }
            .welcome .hint{ color:#64748b; margin:2px 0 0; }

            /* KPI */
            .kpis{ display:grid; grid-template-columns: repeat(3,1fr); gap:14px; margin-top:12px; }
            @media (max-width: 900px){ .kpis{ grid-template-columns: 1fr; } }
            .kpi{
                background:#ffffff; border:1px solid #e8eef7; border-radius:16px; padding:16px;
                display:flex; align-items:center; gap:12px; box-shadow:0 10px 24px rgba(15,23,42,.06);
            }
            .badge-icon{
                width:44px; height:44px; border-radius:12px; background:#eef3fb; color:#265BA9;
                display:flex; align-items:center; justify-content:center; font-size:20px;
            }
            .kpi h4{ margin:0; font-size:14px; color:#64748b; font-weight:700; }
            .kpi .num{ margin:2px 0 0; font-size:22px; font-weight:800; color:#0b1220; }

            /* QUICK ACTIONS */
            .actions{ display:flex; flex-wrap:wrap; gap:10px; margin-top:14px; }
            .btn-primary{
                background:#265BA9; border:1px solid #265BA9; color:#fff; border-radius:12px; padding:10px 14px; font-weight:800; text-decoration:none;
                display:inline-flex; align-items:center; gap:8px; box-shadow:0 10px 24px rgba(38,91,169,.25);
            }
            .btn-primary:hover{ background:#1f4d90; }
            .btn-ghost{
                background:#fff; border:1px solid #e8eef7; color:#0f172a; border-radius:12px; padding:10px 14px; font-weight:800; text-decoration:none;
                display:inline-flex; align-items:center; gap:8px;
            }

            /* Finder (tabs) */
            .finder{
                background:#fff; border:1px solid #e8eef7; border-radius:18px; padding:14px;
                box-shadow:0 10px 24px rgba(15,23,42,.06); margin-top:16px;
            }
            .finder-tabs{ display:flex; gap:8px; flex-wrap:wrap; margin-bottom:10px; }
            .finder .tab{
                border:none; cursor:pointer; padding:10px 14px; border-radius:999px;
                background:#f5f7fb; color:#0b1220; font-weight:800; display:flex; align-items:center; gap:8px;
                border:1px solid #e8eef7; transition:.2s;
            }
            .finder .tab:hover{ background:#eef3fb; }
            .finder .tab.active{
                background:#265BA9; color:#fff; border-color:#265BA9;
                box-shadow:0 8px 18px rgba(38,91,169,.25);
            }
            .panel{ display:none; }
            .panel.show{ display:block; }

            .finder-form{ display:flex; gap:10px; align-items:flex-start; flex-wrap:wrap; }
            .finder-form.grid-3{
                display:grid; grid-template-columns: repeat(3, minmax(180px,1fr)); gap:10px;
            }
            @media (max-width: 900px){ .finder-form.grid-3{ grid-template-columns: 1fr; } }
            .field{
                position:relative; display:flex; align-items:center; gap:8px;
                border:1px solid #dbe3f0; border-radius:12px; background:#f9fbff; padding:8px 10px;
                min-width:240px; flex:1;
            }
            .field label{ color:#265BA9; }
            .input{
                width:100%; border:none; background:transparent; outline:none; padding:6px 4px;
                color:#0f172a; font-weight:600;
            }
            .input::placeholder{ color:#97a6b8; font-weight:500; }
            .btn-find{
                border:none; background:#265BA9; color:#fff; font-weight:800;
                padding:10px 14px; border-radius:12px; cursor:pointer; white-space:nowrap;
                box-shadow:0 10px 24px rgba(38,91,169,.25); transition:.2s;
            }
            .btn-find:hover{ background:#1f4d90; }

            /* TABLES */
            .table-card{
                background:#fff; border:1px solid #e8eef7; border-radius:16px; overflow:hidden;
                box-shadow:0 10px 24px rgba(15,23,42,.06); margin-top:16px;
            }
            .table-title{
                display:flex; align-items:center; gap:8px; padding:12px 14px; border-bottom:1px solid #e8eef7; font-weight:800; color:#0b1220;
            }
            .table-title i{ color:#265BA9; }
            table{ width:100%; border-collapse:collapse; }
            thead th{
                position:sticky; top:0; background:linear-gradient(90deg, #265BA9, #3f76c9);
                color:#fff; text-align:left; padding:12px; font-size:14px;
            }
            tbody td{ padding:10px 12px; border-bottom:1px solid #f0f3f7; }
            tbody tr:hover{ background:#f8fbff; }
            .tbl-wrap{ max-height:360px; overflow:auto; }

            .act{
                display:inline-flex; align-items:center; gap:6px; padding:8px 10px; border-radius:10px; text-decoration:none; font-weight:700; border:1px solid #e8eef7;
                background:#fff; color:#0f172a; margin-right:6px;
            }
            .act.update{ color:#00aa55; border-color:#b4f5c5; background:#ebfff1; }
            .act.delete{ color:#dd1111; border-color:#ffd0d0; background:#fff3f3; }
            .act:hover{ filter:brightness(.97); }

            .msg{ margin-top:8px; font-weight:700; text-align:center; }
            .msg.ok{ color:#16a34a; }
            .msg.err{ color:#dc2626; }
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

        <nav class="navbar">
            <div class="nav-wrap">
                <a class="brand" href="#">
                    <div class="logo"><i class="fa-solid fa-car-wrench"></i></div>
                    <div>
                        <h1>FPT Garage • Dashboard</h1>
                        <div class="sub">Quản lý bán xe • phụ tùng • khách hàng</div>
                    </div>
                </a>
                <div class="nav-actions">
                    <a href="StatisticsServlet" class="btn"><i class="fa-solid fa-chart-line"></i> Báo cáo</a>
                    <a href="LogoutServlet?role=sale" class="btn danger"><i class="fa-solid fa-right-from-bracket"></i> Đăng xuất</a>
                </div>
            </div>
        </nav>

        <div class="page">
            <div class="welcome">
                <div>
                    <p class="hi">Xin chào, <%= kq.getSalesName()%> 👋</p>
                    <p class="hint">Chúc bạn một ngày hiệu quả! Hãy bắt đầu bằng thao tác nhanh bên dưới.</p>
                    <div class="actions">
                        <a href="addCar.jsp" class="btn-primary"><i class="fa-solid fa-square-plus"></i> Thêm Xe</a>
                        <a href="addPart.jsp" class="btn-primary"><i class="fa-solid fa-screwdriver-wrench"></i> Thêm Phụ tùng</a>
                        <a href="StatisticsServlet" class="btn-ghost"><i class="fa-solid fa-chart-column"></i> Xem thống kê</a>
                    </div>
                </div>
            </div>

            <%
                Map<Integer, Integer> salesData = (Map<Integer, Integer>) request.getAttribute("salesData");
                ArrayList<Customer> listCustomer = (ArrayList<Customer>) session.getAttribute("listCustomerName");
                ArrayList<Car> listCar = (ArrayList<Car>) session.getAttribute("listCar");
                ArrayList<Parts> listPart = (ArrayList<Parts>) session.getAttribute("listParts");
            %>
            <div class="kpis">
                <div class="kpi">
                    <div class="badge-icon"><i class="fa-solid fa-calendar"></i></div>
                    <div><h4>Số năm có dữ liệu</h4><div class="num"><%= (salesData != null ? salesData.size() : 0)%></div></div>
                </div>
                <div class="kpi">
                    <div class="badge-icon"><i class="fa-solid fa-users"></i></div>
                    <div><h4>Kết quả khách hàng</h4><div class="num"><%= (listCustomer != null ? listCustomer.size() : 0)%></div></div>
                </div>
                <div class="kpi">
                    <div class="badge-icon"><i class="fa-solid fa-car-side"></i></div>
                    <div><h4>Kết quả xe / phụ tùng</h4><div class="num"><%= ((listCar != null ? listCar.size() : 0) + (listPart != null ? listPart.size() : 0))%></div></div>
                </div>
            </div>

            <%
      if (request.getAttribute("message") != null) {%>
            <div class="msg ok"><i class="fa-regular fa-circle-check"></i> <%= request.getAttribute("message")%></div>
            <% } %>
            <%
      if (request.getAttribute("error") != null) {%>
            <div class="msg err"><i class="fa-regular fa-circle-xmark"></i> <%= request.getAttribute("error")%></div>
            <% } %>
            <%
                String message = (String) session.getAttribute("message");
                String error = (String) session.getAttribute("error");
      if (message != null) {%>
            <div class="msg ok"><i class="fa-regular fa-circle-check"></i> <%= message%></div>
            <%  session.removeAttribute("message");
      } %>
            <% if (error != null) {%>
            <div class="msg err"><i class="fa-regular fa-circle-xmark"></i> <%= error%></div>
            <%  session.removeAttribute("error");
      } %>

            <!-- Finder -->
            <div class="finder">
                <div class="finder-tabs" role="tablist" aria-label="Bộ tìm kiếm">
                    <button class="tab active" data-target="#panel-customer" role="tab" aria-selected="true">
                        <i class="fa-solid fa-user"></i> Khách hàng
                    </button>
                    <button class="tab" data-target="#panel-part" role="tab" aria-selected="false">
                        <i class="fa-solid fa-cubes"></i> Phụ tùng
                    </button>
                    <button class="tab" data-target="#panel-car" role="tab" aria-selected="false">
                        <i class="fa-solid fa-car-side"></i> Xe
                    </button>
                </div>

                <div class="panel show" id="panel-customer" role="tabpanel">
                    <form class="finder-form" action="FindCustNameServlet" method="GET">
                        <div class="field">
                            <label for="custName"><i class="fa-solid fa-user"></i></label>
                            <input id="custName" class="input" type="text" name="custName" placeholder="Nhập tên khách hàng">
                        </div>
                        <button class="btn-find" type="submit"><i class="fa-solid fa-magnifying-glass"></i> Tìm</button>
                    </form>
                </div>

                <div class="panel" id="panel-part" role="tabpanel">
                    <form class="finder-form" action="FindPartServlet" method="GET">
                        <div class="field">
                            <label for="partName"><i class="fa-solid fa-screwdriver-wrench"></i></label>
                            <input id="partName" class="input" type="text" name="partName" placeholder="Nhập tên phụ tùng">
                        </div>
                        <button class="btn-find" type="submit"><i class="fa-solid fa-magnifying-glass"></i> Tìm</button>
                    </form>
                </div>

                <div class="panel" id="panel-car" role="tabpanel">
                    <form class="finder-form grid-3" action="FindCarServlet" method="GET">
                        <div class="field">
                            <label for="serial"><i class="fa-solid fa-hashtag"></i></label>
                            <input id="serial" class="input" type="text" name="serialNumber" placeholder="Serial Number">
                        </div>
                        <div class="field">
                            <label for="model"><i class="fa-solid fa-car"></i></label>
                            <input id="model" class="input" type="text" name="model" placeholder="Model">
                        </div>
                        <div class="field">
                            <label for="year"><i class="fa-solid fa-calendar"></i></label>
                            <input id="year" class="input" type="text" name="year" placeholder="Year">
                        </div>
                        <button class="btn-find" type="submit"><i class="fa-solid fa-magnifying-glass"></i> Tìm</button>
                    </form>
                </div>
            </div>

            <% if (salesData != null) { %>
            <div class="table-card">
                <div class="table-title"><i class="fa-solid fa-chart-simple"></i> Bán xe theo năm</div>
                <div class="tbl-wrap">
                    <table>
                        <thead>
                            <tr><th>Năm</th><th>Số xe bán</th></tr>
                        </thead>
                        <tbody>
                            <% for (Map.Entry<Integer, Integer> entry : salesData.entrySet()) {%>
                            <tr><td><%= entry.getKey()%></td><td><%= entry.getValue()%></td></tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
            <% } %>

            <% if (listCustomer != null && !listCustomer.isEmpty()) { %>
            <div class="table-card">
                <div class="table-title"><i class="fa-solid fa-users"></i> Kết quả tìm khách hàng</div>
                <div class="tbl-wrap">
                    <table>
                        <thead>
                            <tr><th>ID</th><th>Tên</th><th>Điện thoại</th><th>Giới tính</th><th>Địa chỉ</th></tr>
                        </thead>
                        <tbody>
                            <% for (Customer customer : listCustomer) {%>
                            <tr>
                                <td><%= customer.getCustID()%></td>
                                <td><%= customer.getCustName()%></td>
                                <td><%= customer.getPhone()%></td>
                                <td><%= customer.getSex()%></td>
                                <td><%= customer.getCustAddress()%></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
            <% } %>

            <% if (listCar != null && !listCar.isEmpty()) { %>
            <div class="table-card">
                <div class="table-title"><i class="fa-solid fa-car-side"></i> Kết quả tìm xe</div>
                <div class="tbl-wrap">
                    <table>
                        <thead>
                            <tr><th>Car ID</th><th>Serial</th><th>Model</th><th>Màu</th><th>Năm</th><th>Thao tác</th></tr>
                        </thead>
                        <tbody>
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
                                        <button class="act update" type="submit"><i class="fa-solid fa-pen"></i> Sửa</button>
                                    </form>
                                    <form action="DeleteCarServlet" method="POST" style="display:inline;" onsubmit="return confirm('Bạn chắc chắn muốn xóa xe này?');">
                                        <input type="hidden" name="carID" value="<%= car.getCarID()%>"/>
                                        <button class="act delete" type="submit"><i class="fa-solid fa-trash"></i> Xóa</button>
                                    </form>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
            <% } %>

            <% if (listPart != null && !listPart.isEmpty()) { %>
            <div class="table-card">
                <div class="table-title"><i class="fa-solid fa-screwdriver-wrench"></i> Kết quả tìm phụ tùng</div>
                <div class="tbl-wrap">
                    <table>
                        <thead>
                            <tr><th>Part ID</th><th>Tên phụ tùng</th><th>Giá nhập</th><th>Giá bán</th><th>Thao tác</th></tr>
                        </thead>
                        <tbody>
                            <% for (Parts part : listPart) {%>
                            <tr>
                                <td><%= part.getPartID()%></td>
                                <td><%= part.getPartName()%></td>
                                <td><%= part.getPurchasePrice()%></td>
                                <td><%= part.getRetailPrice()%></td>
                                <td>
                                    <form action="updatePart.jsp" method="GET" style="display:inline;">
                                        <input type="hidden" name="partID" value="<%= part.getPartID()%>"/>
                                        <button class="act update" type="submit"><i class="fa-solid fa-pen"></i> Sửa</button>
                                    </form>
                                    <form action="DeletePartServlet" method="POST" style="display:inline;" onsubmit="return confirm('Bạn chắc chắn muốn xóa phụ tùng này?');">
                                        <input type="hidden" name="partID" value="<%= part.getPartID()%>"/>
                                        <button class="act delete" type="submit"><i class="fa-solid fa-trash"></i> Xóa</button>
                                    </form>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
            <% } %>

            <%-- Dọn session list để tránh lặp khi refresh --%>
            <%
                session.removeAttribute("listCustomerName");
                session.removeAttribute("listCar");
                session.removeAttribute("listParts");
            %>
        </div>

        <script>
            (function () {
                var tabs = document.querySelectorAll('.finder .tab');
                var panels = document.querySelectorAll('.finder .panel');
                function activate(target) {
                    for (var i = 0; i < tabs.length; i++) {
                        var t = tabs[i];
                        var on = t.getAttribute('data-target') === target;
                        t.classList.toggle('active', on);
                        t.setAttribute('aria-selected', on ? 'true' : 'false');
                    }
                    for (var j = 0; j < panels.length; j++) {
                        var p = panels[j];
                        p.classList.toggle('show', ('#' + p.id) === target);
                    }
                }
                for (var k = 0; k < tabs.length; k++) {
                    tabs[k].addEventListener('click', function () {
                        activate(this.getAttribute('data-target'));
                    });
                    tabs[k].addEventListener('keydown', function (e) {
                        if (e.key === 'Enter' || e.key === ' ') {
                            e.preventDefault();
                            activate(this.getAttribute('data-target'));
                        }
                    });
                }
            })();
        </script>
    </body>
</html>
