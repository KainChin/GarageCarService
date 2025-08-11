<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Saler"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>FPT Garage • Thêm xe</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">

        <style>
            *{box-sizing:border-box}
            html,body{margin:0}
            body{font-family:Inter,-apple-system,Segoe UI,Roboto,Arial,sans-serif;background:#f4f7fb;color:#0b1220}

            /* Navbar mini */
            .nav{background:linear-gradient(90deg,#265BA9,#3f76c9);color:#fff;padding:10px 16px;
                 display:flex;align-items:center;gap:10px;box-shadow:0 6px 18px rgba(0,0,0,.12)}
            .nav .logo{width:38px;height:38px;border-radius:10px;background:rgba(255,255,255,.15);
                       display:flex;align-items:center;justify-content:center}
            .nav h1{margin:0;font-size:18px;font-weight:800}
            .nav .sub{font-size:11px;opacity:.9;margin-top:-2px}

            .wrap{max-width:860px;margin:24px auto;padding:0 16px}
            .card{background:#fff;border:1px solid #e8eef7;border-radius:16px;padding:20px;
                  box-shadow:0 10px 24px rgba(15,23,42,.06)}
            .card h2{margin:0 0 6px;font-weight:800}
            .hint{color:#64748b;margin:0 0 14px}

            /* Alerts */
            .alert{padding:10px 14px;border-radius:10px;font-weight:700;margin-bottom:12px;display:flex;gap:8px;align-items:center}
            .alert.error{background:#fee2e2;color:#b91c1c;border:1px solid #fecaca}
            .alert.success{background:#dcfce7;color:#166534;border:1px solid #bbf7d0}

            /* Form layout */
            .grid{display:grid;grid-template-columns:1fr 1fr;gap:12px}
            @media (max-width:800px){.grid{grid-template-columns:1fr}}

            .field{display:flex;align-items:center;gap:10px;border:1px solid #dbe3f0;border-radius:12px;
                   background:#f9fbff;padding:10px 12px}
            .field i{color:#265BA9}
            .input{border:none;background:transparent;outline:none;width:100%;font-weight:600;color:#0b1220}
            .input::placeholder{color:#9aa7b7;font-weight:500}
            .help{font-size:12px;color:#6b7280;margin:4px 2px 0}

            .actions{display:flex;gap:10px;flex-wrap:wrap;margin-top:12px}
            .btn{display:inline-flex;align-items:center;gap:8px;padding:10px 14px;border-radius:12px;
                 border:1px solid #e8eef7;background:#fff;text-decoration:none;font-weight:800;color:#0b1220}
            .btn-primary{background:#265BA9;border-color:#265BA9;color:#fff;box-shadow:0 10px 24px rgba(38,91,169,.25)}
            .btn-primary:hover{background:#1f4d90}
            .btn-secondary{background:#eef3fb}
            .btn-secondary:hover{filter:brightness(.97)}
            .btn-danger{background:#dc3545;border-color:#dc3545;color:#fff}
            .btn-danger:hover{background:#b02a37}
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

            // Lấy lại input đã nhập (để giữ giá trị khi lỗi)
            String pCarID = request.getParameter("carID");
            String pSerial = request.getParameter("serialNumber");
            String pModel = request.getParameter("model");
            String pColour = request.getParameter("colour");
            String pYear = request.getParameter("year");

            String errorMessage = (String) request.getAttribute("errorMessage");
            String successMessage = (String) request.getAttribute("successMessage");
        %>

        <!-- NAV -->
        <div class="nav">
            <div class="logo"><i class="fa-solid fa-car-wrench"></i></div>
            <div>
                <h1>FPT Garage</h1>
                <div class="sub">Thêm xe vào kho / danh mục bán</div>
            </div>
            <div style="margin-left:auto;font-weight:600">Xin chào, <%= kq.getSalesName()%></div>
        </div>

        <div class="wrap">
            <% if (errorMessage != null) {%>
            <div class="alert error"><i class="fa-solid fa-circle-exclamation"></i><%= errorMessage%></div>
                <% } %>
                <% if (successMessage != null) {%>
            <div class="alert success"><i class="fa-solid fa-circle-check"></i><%= successMessage%></div>
                <% }%>

            <div class="card">
                <h2>Thêm xe</h2>
                <p class="hint">Điền thông tin chi tiết. Hệ thống sẽ kiểm tra định dạng và khoảng giá trị hợp lệ.</p>

                <form action="AddCarServlet" method="post" id="carForm" novalidate>
                    <div class="grid">
                        <!-- Car ID -->
                        <div>
                            <label class="help" for="carID">Car ID (số nguyên không âm)</label>
                            <div class="field">
                                <i class="fa-solid fa-key"></i>
                                <input id="carID" class="input" type="number" name="carID"
                                       min="0" placeholder="VD: 1001" required
                                       value="<%= (pCarID != null ? pCarID : "")%>">
                            </div>
                        </div>

                        <!-- Serial -->
                        <div>
                            <label class="help" for="serial">Serial Number (6–20 ký tự chữ & số, tự động in hoa)</label>
                            <div class="field">
                                <i class="fa-solid fa-hashtag"></i>
                                <input id="serial" class="input" type="text" name="serialNumber"
                                       placeholder="VD: WDD2221234567890"
                                       pattern="[A-Za-z0-9\\-]{6,20}" maxlength="20" required
                                       value="<%= (pSerial != null ? pSerial : "")%>">
                            </div>
                        </div>

                        <!-- Model -->
                        <div>
                            <label class="help" for="model">Model</label>
                            <div class="field">
                                <i class="fa-solid fa-car"></i>
                                <input id="model" class="input" list="modelList" type="text" name="model"
                                       placeholder="VD: Mercedes S450, Civic, CX-5…" required
                                       value="<%= (pModel != null ? pModel : "")%>">
                            </div>
                            <datalist id="modelList">
                                <option value="Mercedes-Benz S450"></option>
                                <option value="BMW 530i"></option>
                                <option value="Toyota Camry"></option>
                                <option value="Honda Civic"></option>
                                <option value="Mazda CX-5"></option>
                                <option value="Ford Ranger"></option>
                            </datalist>
                        </div>

                        <!-- Colour -->
                        <div>
                            <label class="help" for="colour">Màu sắc</label>
                            <div class="field">
                                <i class="fa-solid fa-droplet"></i>
                                <input id="colour" class="input" list="colorList" type="text" name="colour"
                                       placeholder="VD: Đen, Trắng, Xanh…" required
                                       value="<%= (pColour != null ? pColour : "")%>">
                            </div>
                            <datalist id="colorList">
                                <option value="Đen"></option>
                                <option value="Trắng"></option>
                                <option value="Bạc"></option>
                                <option value="Xanh"></option>
                                <option value="Đỏ"></option>
                                <option value="Xám"></option>
                            </datalist>
                        </div>

                        <!-- Year -->
                        <div>
                            <label class="help" for="year">Năm sản xuất (1980 → Năm hiện tại + 1)</label>
                            <div class="field">
                                <i class="fa-solid fa-calendar"></i>
                                <input id="year" class="input" type="number" name="year"
                                       placeholder="VD: 2024" required
                                       value="<%= (pYear != null ? pYear : "")%>">
                            </div>
                        </div>
                    </div>

                    <div class="actions">
                        <button type="submit" class="btn btn-primary"><i class="fa-solid fa-plus"></i> Thêm xe</button>
                        <button type="reset" class="btn btn-secondary"><i class="fa-solid fa-rotate-left"></i> Nhập lại</button>
                        <a href="SaleDashboard.jsp" class="btn btn-danger"><i class="fa-solid fa-circle-arrow-left"></i> Quay về Dashboard</a>
                    </div>
                </form>
            </div>
        </div>

        <script>
            (function () {
                // Giới hạn năm: 1980 -> (năm hiện tại + 1)
                var year = document.getElementById('year');
                var now = new Date().getFullYear();
                year.min = 1980;
                year.max = now + 1;

                // Serial luôn in hoa
                var serial = document.getElementById('serial');
                serial.addEventListener('input', function () {
                    this.value = this.value.toUpperCase();
                });

                // Ngăn submit nếu vi phạm HTML5 validity
                var form = document.getElementById('carForm');
                form.addEventListener('submit', function (e) {
                    if (!form.checkValidity()) {
                        e.preventDefault();
                        form.reportValidity();
                    }
                });
            })();
        </script>
    </body>
</html>
