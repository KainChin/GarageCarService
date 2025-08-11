<%@page import="model.Saler"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Kiểm tra đăng nhập trước khi render HTML để tránh đã ghi output mà forward
    Saler kq = (Saler) session.getAttribute("user");
    if (kq == null) {
        request.setAttribute("error", "You must login");
        request.getRequestDispatcher("saleLogin.jsp").forward(request, response);
        return;
    }
%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Garage – Add New Part</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <!-- Font & Icons -->
        <link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet"/>

        <style>
            /* ===== Legacy-safe CSS for NetBeans 8.2 ===== */
            html,body{
                height:100%;
                background:#0b0f17; /* nền tối */
                color:#e5e7eb;      /* chữ sáng */
                font-family:'Inter', system-ui, -apple-system, Segoe UI, Roboto, sans-serif;
                letter-spacing:.2px;
            }

            /* Overlay gradient đơn giản (tránh mix-blend-mode, inset) */
            body:before{
                content:"";
                position:fixed; top:0; right:0; bottom:0; left:0;
                pointer-events:none;
                background: linear-gradient(180deg, rgba(30,144,255,.10), transparent 70%);
            }

            .shell{
                min-height:100%;
                display:flex; align-items:center; justify-content:center;
                padding:40px 16px;
            }

            .card{
                width:92vw; max-width:960px;
                background: linear-gradient(180deg, #111827 0%, #0f172a 100%);
                border:1px solid rgba(255,255,255,.06);
                border-radius:16px;
                box-shadow:0 10px 30px rgba(0,0,0,.35);
                overflow:hidden;
            }

            .card__header{
                display:flex; align-items:center;
                padding:22px 26px;
                background: linear-gradient(90deg, rgba(30,144,255,.12), rgba(0,212,255,.06) 60%, transparent);
                border-bottom:1px solid rgba(255,255,255,.06);
            }
            .badge{
                display:inline-flex; align-items:center;
                padding:8px 12px; border-radius:999px;
                font-weight:600; font-size:14px;
                color:#dbeafe; background:rgba(30,144,255,.12);
                border:1px solid rgba(30,144,255,.35);
                gap:8px;
            }
            .title{ font-size:22px; font-weight:800; letter-spacing:.3px; margin-left:12px; }
            .status{ display:flex; gap:10px; align-items:center; margin-left:auto; font-size:12px; color:#9ca3af; }
            .dot{ width:8px; height:8px; border-radius:50%; background:#22c55e; box-shadow:0 0 0 3px rgba(34,197,94,.18); }

            .card__body{ padding:26px; }

            /* layout 2 cột dùng flex (tránh CSS Grid) */
            .grid{ display:flex; flex-wrap:wrap; margin:-9px; }
            .grid .field{ flex:1 1 100%; margin:9px; }
            @media (min-width:820px){ .grid .field{ flex:1 1 calc(50% - 18px); } }

            .field{
                position:relative;
                background:rgba(17,24,39,.6);
                border:1px solid #1f2937;
                border-radius:12px; padding:14px 14px 10px;
                transition:border-color .2s, box-shadow .2s, transform .06s;
            }
            .field:hover{ border-color:#2b3a55; }
            .field:focus-within{
                border-color:#1e90ff;
                box-shadow:0 0 0 3px rgba(30,144,255,.18);
            }

            .label{
                font-size:12px; color:#9ca3af;
                display:flex; align-items:center; gap:8px;
                margin-bottom:6px; text-transform:uppercase; letter-spacing:.8px;
            }
            .label i{ opacity:.7; }

            .input{
                width:100%; border:0; outline:none; background:transparent;
                color:#e5e7eb; font-size:15px; padding:6px 2px;
            }
            .input[type=number]{ -moz-appearance:textfield; }
            .input::-webkit-outer-spin-button,
            .input::-webkit-inner-spin-button{ -webkit-appearance:none; margin:0; }

            .hint{ font-size:12px; color:#9ca3af; margin-top:4px; }

            .actions{
                display:flex; gap:12px; align-items:center; justify-content:flex-end;
                padding-top:20px; border-top:1px dashed rgba(255,255,255,.08); margin-top:6px;
            }

            .btn{
                display:inline-flex; align-items:center; gap:10px;
                padding:12px 18px; border-radius:12px;
                border:1px solid rgba(255,255,255,.14);
                background:linear-gradient(180deg, #171f2e, #121826);
                color:#e5e7eb; text-decoration:none; font-weight:700;
                transition: transform .06s, box-shadow .2s, border-color .2s;
                cursor:pointer;
            }
            .btn:hover{ border-color:rgba(255,255,255,.25); box-shadow:0 6px 18px rgba(0,0,0,.35); }
            .btn:active{ transform: translateY(1px); }

            .btn--primary{
                background: linear-gradient(180deg, #1b68d1, #124aa3);
                border-color: rgba(30,144,255,.6);
            }
            .btn--primary:hover{
                box-shadow: 0 8px 24px rgba(30,144,255,.35), inset 0 0 20px rgba(255,255,255,.06);
            }

            .form-msg{
                margin-bottom:14px; padding:10px 12px; border-radius:10px;
                background:rgba(34,197,94,.08); color:#a7f3d0; border:1px solid rgba(34,197,94,.3);
                display:none;
            }
            .wm{
                position:absolute; right:-20px; bottom:-12px;
                opacity:.06; font-size:80px; font-weight:800; pointer-events:none;
            }
        </style>
    </head>
    <body>
        <div class="shell">
            <div class="card">
                <div class="card__header">
                    <span class="badge"><i class="fa-solid fa-wrench"></i> Garage Admin</span>
                    <div class="title">Add New Part</div>
                    <div class="status">
                        <span class="dot"></span> Secure Session
                    </div>
                </div>

                <div class="card__body">
                    <div id="formMsg" class="form-msg"></div>

                    <form action="AddPartServlet" method="post" id="partForm" novalidate>
                        <div class="grid">

                            <div class="field">
                                <label class="label"><i class="fa-solid fa-hashtag"></i> Part ID</label>
                                <input class="input" type="number" name="partID" required min="0" placeholder="VD: 1001" autofocus>
                                <div class="hint">Mã phụ tùng nội bộ, không âm.</div>
                            </div>

                            <div class="field">
                                <label class="label"><i class="fa-solid fa-car-battery"></i> Part Name</label>
                                <input class="input" type="text" name="partName" required placeholder="VD: Lọc nhớt Mobil 1 M1-110A">
                                <div class="hint">Tên hiển thị cho kho & bán lẻ.</div>
                            </div>

                            <div class="field">
                                <label class="label"><i class="fa-solid fa-hand-holding-dollar"></i> Purchase Price</label>
                                <input class="input" type="number" step="0.01" name="purchasePrice" required min="0" placeholder="Giá nhập (VND)">
                                <div class="hint">Giá nhập vào kho.</div>
                            </div>

                            <div class="field">
                                <label class="label"><i class="fa-solid fa-tags"></i> Retail Price</label>
                                <input class="input" type="number" step="0.01" name="retailPrice" required min="0" placeholder="Giá bán lẻ (VND)">
                                <div class="hint">Giá bán lẻ đề xuất.</div>
                            </div>

                        </div>

                        <div class="actions">
                            <a href="SaleDashboard.jsp" class="btn">
                                <i class="fa-solid fa-arrow-left"></i> Back
                            </a>
                            <button class="btn btn--primary" type="submit">
                                <i class="fa-solid fa-plus"></i> Add Part
                            </button>
                        </div>
                        <div class="wm">AUTO</div>
                    </form>
                </div>
            </div>
        </div>

        <script>
            (function () {
                var form = document.getElementById('partForm');
                var msg = document.getElementById('formMsg');

                function showMsg(text, ok) {
                    if (!msg)
                        return;
                    msg.style.display = 'block';
                    if (ok) {
                        msg.style.background = 'rgba(34,197,94,.08)';
                        msg.style.borderColor = 'rgba(34,197,94,.3)';
                        msg.style.color = '#a7f3d0';
                    } else {
                        msg.style.background = 'rgba(239,68,68,.08)';
                        msg.style.borderColor = 'rgba(239,68,68,.3)';
                        msg.style.color = '#fecaca';
                    }
                    msg.textContent = text;
                    setTimeout(function () {
                        msg.style.display = 'none';
                    }, 3500);
                }

                form.addEventListener('submit', function (e) {
                    var id = (form.partID.value || '').trim();
                    var name = (form.partName.value || '').trim();
                    var pIn = parseFloat(form.purchasePrice.value || '0');
                    var pOut = parseFloat(form.retailPrice.value || '0');

                    if (!id || !name) {
                        e.preventDefault();
                        showMsg('Vui lòng nhập Part ID và Part Name.', false);
                        return;
                    }
                    if (pIn < 0 || pOut < 0) {
                        e.preventDefault();
                        showMsg('Giá không được âm.', false);
                        return;
                    }
                    if (pOut < pIn) {
                        // Cảnh báo nhẹ, không chặn submit
                        showMsg('Lưu ý: Retail Price đang nhỏ hơn Purchase Price.', false);
                    }
                });
            })();
        </script>
    </body>
</html>
