<%@page import="model.Saler"%>
<%@page import="model.BestUsedPart"%>
<%@page import="model.CarSalesByYear"%>
<%@page import="model.CarRevenueByYear"%>
<%@page import="model.BestSellingCarModel"%>
<%@page import="java.util.List"%>
<%@page import="model.TopMechanic"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>FPT Garage • Phân tích kinh doanh ô tô</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <!-- Bootstrap + Chart.js + Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1/dist/chart.umd.min.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">

        <style>
            body{ background:#f5f9ff; }
            .page-wrap{ max-width:1200px; margin:26px auto; padding:0 16px; }
            .page-title{ font-weight:800; color:#0b1220; }
            .sub{ color:#64748b; }

            .btn-back{ background:#265BA9; border:none; font-weight:800; }
            .btn-back:hover{ background:#1f4d90; }

            .chart-card{
                background:#fff; border:1px solid #e8eef7; border-radius:18px; padding:18px;
                box-shadow:0 10px 24px rgba(15,23,42,.06);
                min-height:360px;
            }
            .chart-title{ font-weight:800; color:#0b1220; margin:0 0 10px; display:flex; align-items:center; gap:8px; }
            .chart-title i{ color:#265BA9; }

            /* Top 3 thợ máy */
            .top-wrap{ display:grid; grid-template-columns: repeat(3,1fr); gap:14px; }
            @media (max-width: 900px){ .top-wrap{ grid-template-columns: 1fr; } }
            .mech-card{
                position:relative; background:#fff; border:1px solid #e8eef7; border-radius:16px; padding:16px;
                display:flex; gap:12px; align-items:center;
                box-shadow:0 10px 24px rgba(15,23,42,.06);
            }
            .avatar{
                width:56px; height:56px; border-radius:50%; display:flex; align-items:center; justify-content:center;
                font-weight:800; color:#fff;
            }
            .rank-badge{
                position:absolute; top:10px; right:10px; padding:4px 8px; border-radius:999px; font-weight:800; font-size:12px; color:#0b1220;
                background:#eef3fb; border:1px solid #dfe8f7;
            }
            .gold{ background:linear-gradient(135deg,#ffd775,#fbbf24); }
            .silver{ background:linear-gradient(135deg,#d8dee9,#cbd5e1); }
            .bronze{ background:linear-gradient(135deg,#f0b090,#eaa06b); }
            .name{ font-weight:800; color:#0b1220; }
            .meta{ color:#64748b; font-size:13px; }
        </style>
    </head>
    <body>

        <%
            // Bảo vệ route
            Saler kq = (Saler) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "Bạn cần đăng nhập");
                request.getRequestDispatcher("saleLogin.jsp").forward(request, response);
                return;
            }

            // Dữ liệu từ Servlet
            List<CarSalesByYear> salesData = (List<CarSalesByYear>) request.getAttribute("salesData");
            List<CarRevenueByYear> revenueData = (List<CarRevenueByYear>) request.getAttribute("revenueData");
            List<BestSellingCarModel> bestSellingCars = (List<BestSellingCarModel>) request.getAttribute("bestSellingCars");
            List<BestUsedPart> bestUsedParts = (List<BestUsedPart>) request.getAttribute("bestUsedParts");
            List<TopMechanic> topMechanics = (List<TopMechanic>) request.getAttribute("topMechanics");
        %>

        <div class="page-wrap">
            <div class="d-flex flex-column flex-md-row align-items-md-center justify-content-between mb-3">
                <div>
                    <h2 class="page-title">Phân tích kinh doanh ô tô</h2>
                    <div class="sub">Chọn mục để xem chi tiết</div>
                </div>
                <a href="SaleDashboard.jsp" class="btn btn-back mt-2 mt-md-0"><i class="fa-solid fa-arrow-left me-1"></i> Quay lại</a>
            </div>

            <!-- Tabs điều hướng -->
            <ul class="nav nav-tabs" id="statsTabs" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="sales-tab" data-bs-toggle="tab" data-bs-target="#sales" type="button" role="tab">
                        <i class="fa-solid fa-chart-column me-1"></i> Bán hàng & Doanh thu
                    </button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="models-tab" data-bs-toggle="tab" data-bs-target="#models" type="button" role="tab">
                        <i class="fa-solid fa-car-side me-1"></i> Mẫu xe & Phụ tùng
                    </button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="mechanic-tab" data-bs-toggle="tab" data-bs-target="#mechanic" type="button" role="tab">
                        <i class="fa-solid fa-trophy me-1"></i> Top 3 Thợ máy
                    </button>
                </li>
            </ul>

            <div class="tab-content mt-3">
                <!-- Tab 1: Sales + Revenue -->
                <div class="tab-pane fade show active" id="sales" role="tabpanel" aria-labelledby="sales-tab">
                    <div class="row g-3">
                        <div class="col-12 col-lg-6">
                            <div class="chart-card">
                                <div class="chart-title"><i class="fa-solid fa-chart-column"></i> Số xe bán theo năm</div>
                                <canvas id="salesChart" height="280"></canvas>
                            </div>
                        </div>
                        <div class="col-12 col-lg-6">
                            <div class="chart-card">
                                <div class="chart-title"><i class="fa-solid fa-chart-line"></i> Doanh thu theo năm (VND)</div>
                                <canvas id="revenueChart" height="280"></canvas>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Tab 2: Best models + Parts -->
                <div class="tab-pane fade" id="models" role="tabpanel" aria-labelledby="models-tab">
                    <div class="row g-3">
                        <div class="col-12 col-lg-6">
                            <div class="chart-card">
                                <div class="chart-title"><i class="fa-solid fa-circle-notch"></i> Mẫu xe bán chạy</div>
                                <canvas id="bestModelChart" height="300"></canvas>
                            </div>
                        </div>
                        <div class="col-12 col-lg-6">
                            <div class="chart-card">
                                <div class="chart-title"><i class="fa-solid fa-bars-staggered"></i> Phụ tùng sử dụng nhiều</div>
                                <canvas id="usedPartChart" height="300"></canvas>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Tab 3: Top 3 Mechanics -->
                <div class="tab-pane fade" id="mechanic" role="tabpanel" aria-labelledby="mechanic-tab">
                    <div class="mt-3">
                        <h5 class="mb-2"><i class="fa-solid fa-trophy me-2" style="color:#eab308"></i>Top 3 thợ máy</h5>
                        <div class="top-wrap">
                            <%
                                if (topMechanics != null) {
                                    String[] rankText = {"Hạng 1", "Hạng 2", "Hạng 3"};
                                    String[] avatarClass = {"gold", "silver", "bronze"};
                                    int max = Math.min(3, topMechanics.size());
                                    for (int i = 0; i < max; i++) {
                                        TopMechanic m = topMechanics.get(i);
                                        String name = m.getMechanicName();
                                        String initials = (name != null && name.trim().length() > 0) ? name.trim().substring(0, 1).toUpperCase() : "M";
                            %>
                            <div class="mech-card">
                                <span class="rank-badge"><%= rankText[i]%></span>
                                <div class="avatar <%= avatarClass[i]%>"><%= initials%></div>
                                <div>
                                    <div class="name"><%= name%></div>
                                    <div class="meta">Mã thợ: <strong><%= m.getMechanicID()%></strong></div>
                                </div>
                            </div>
                            <% }
              } %>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- ====== Đưa dữ liệu từ JSP sang JS ====== -->
        <script>
            const salesYears = [
            <% if (salesData != null) {
                    for (int i = 0; i < salesData.size(); i++) {%>
            "<%= salesData.get(i).getYear()%>"<%= (i < salesData.size() - 1 ? "," : "")%>
            <% }
                } %>
            ];
            const salesTotals = [
            <% if (salesData != null) {
                    for (int i = 0; i < salesData.size(); i++) {%>
            <%= salesData.get(i).getTotalSold()%><%= (i < salesData.size() - 1 ? "," : "")%>
            <% }
                } %>
            ];

            const revenueYears = [
            <% if (revenueData != null) {
                    for (int i = 0; i < revenueData.size(); i++) {%>
            "<%= revenueData.get(i).getYear()%>"<%= (i < revenueData.size() - 1 ? "," : "")%>
            <% }
                } %>
            ];
            const revenueTotals = [
            <% if (revenueData != null) {
                    for (int i = 0; i < revenueData.size(); i++) {%>
            <%= (long) revenueData.get(i).getRevenue()%><%= (i < revenueData.size() - 1 ? "," : "")%>
            <% }
                } %>
            ];

            const bestModelLabels = [
            <% if (bestSellingCars != null) {
                    for (int i = 0; i < bestSellingCars.size(); i++) {%>
            "<%= bestSellingCars.get(i).getModel()%>"<%= (i < bestSellingCars.size() - 1 ? "," : "")%>
            <% }
                } %>
            ];
            const bestModelValues = [
            <% if (bestSellingCars != null) {
                    for (int i = 0; i < bestSellingCars.size(); i++) {%>
            <%= bestSellingCars.get(i).getTotalSold()%><%= (i < bestSellingCars.size() - 1 ? "," : "")%>
            <% }
                } %>
            ];

            const partLabels = [
            <% if (bestUsedParts != null) {
                    for (int i = 0; i < bestUsedParts.size(); i++) {%>
            "<%= bestUsedParts.get(i).getPartName()%>"<%= (i < bestUsedParts.size() - 1 ? "," : "")%>
            <% }
                } %>
            ];
            const partValues = [
            <% if (bestUsedParts != null) {
                    for (int i = 0; i < bestUsedParts.size(); i++) {%>
            <%= bestUsedParts.get(i).getTotalUsed()%><%= (i < bestUsedParts.size() - 1 ? "," : "")%>
            <% }
                }%>
            ];
        </script>

        <!-- ====== Khởi tạo biểu đồ theo tab (chỉ tạo khi tab hiển thị) ====== -->
        <script>
            const fmtVND = new Intl.NumberFormat('vi-VN');
            const gridCfg = {borderColor: 'rgba(15,23,42,.12)', color: 'rgba(15,23,42,.45)'};

            let salesChartInst, revenueChartInst, bestModelChartInst, usedPartChartInst;

            function buildSalesCharts() {
                if (!salesChartInst && document.getElementById('salesChart')) {
                    salesChartInst = new Chart(document.getElementById('salesChart'), {
                        type: 'bar',
                        data: {labels: salesYears, datasets: [{
                                    label: 'Số xe bán',
                                    data: salesTotals,
                                    backgroundColor: 'rgba(38,91,169,.45)', borderColor: '#265BA9',
                                    borderWidth: 1, borderRadius: 6
                                }]},
                        options: {
                            responsive: true,
                            plugins: {legend: {display: false}, tooltip: {callbacks: {label: ctx => ` ${ctx.raw} xe`}}},
                            scales: {x: {grid: gridCfg, ticks: {font: {weight: '600'}}}, y: {grid: gridCfg, beginAtZero: true}}
                        }
                    });
                }
                if (!revenueChartInst && document.getElementById('revenueChart')) {
                    revenueChartInst = new Chart(document.getElementById('revenueChart'), {
                        type: 'line',
                        data: {labels: revenueYears, datasets: [{
                                    label: 'Doanh thu (VND)',
                                    data: revenueTotals, tension: .35, borderColor: '#265BA9',
                                    backgroundColor: 'rgba(38,91,169,.15)', fill: true, pointRadius: 4, pointHoverRadius: 5
                                }]},
                        options: {
                            responsive: true,
                            plugins: {legend: {display: false}, tooltip: {callbacks: {label: ctx => ` ${fmtVND.format(ctx.raw)} ₫`}}},
                            scales: {x: {grid: gridCfg}, y: {grid: gridCfg, beginAtZero: true, ticks: {callback: v => fmtVND.format(v)}}}
                        }
                    });
                }
            }

            function buildModelPartCharts() {
                if (!bestModelChartInst && document.getElementById('bestModelChart')) {
                    bestModelChartInst = new Chart(document.getElementById('bestModelChart'), {
                        type: 'doughnut',
                        data: {labels: bestModelLabels, datasets: [{
                                    data: bestModelValues, borderWidth: 1, borderColor: '#ffffff',
                                    backgroundColor: ['#265BA9', '#6aa0ff', '#93b7ff', '#bcd0ff', '#89a7e6', '#4f7dd1', '#3b69be', '#8ab6ff']
                                }]},
                        options: {
                            plugins: {legend: {position: 'bottom', labels: {boxWidth: 16}},
                                tooltip: {callbacks: {label: ctx => ` ${ctx.label}: ${ctx.raw} xe`}}},
                            cutout: '58%'
                        }
                    });
                }
                if (!usedPartChartInst && document.getElementById('usedPartChart')) {
                    usedPartChartInst = new Chart(document.getElementById('usedPartChart'), {
                        type: 'bar',
                        data: {labels: partLabels, datasets: [{
                                    label: 'Số lần dùng', data: partValues,
                                    backgroundColor: 'rgba(38,91,169,.45)', borderColor: '#265BA9', borderWidth: 1, borderRadius: 6
                                }]},
                        options: {
                            indexAxis: 'y',
                            plugins: {legend: {display: false}, tooltip: {callbacks: {label: ctx => ` ${ctx.raw} lần`}}},
                            scales: {x: {grid: gridCfg, beginAtZero: true}, y: {grid: gridCfg, ticks: {font: {weight: '600'}}}}
                        }
                    });
                }
            }

            // Khởi tạo tab đầu tiên ngay khi tải trang
            document.addEventListener('DOMContentLoaded', function () {
                buildSalesCharts();
            });

            // Khi đổi tab -> tạo biểu đồ tương ứng nếu chưa có
            const tabsEl = document.getElementById('statsTabs');
            if (tabsEl) {
                tabsEl.addEventListener('shown.bs.tab', function (event) {
                    const target = event.target.getAttribute('data-bs-target');
                    if (target === '#sales')
                        buildSalesCharts();
                    if (target === '#models')
                        buildModelPartCharts();
                    // Tab Top 3 thợ không cần build chart
                });
            }
        </script>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
