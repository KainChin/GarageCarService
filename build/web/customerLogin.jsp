<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng Nhập Khách Hàng</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap" rel="stylesheet">

    <style>
        /* ==== CSS LOGIN ==== */
        *{ box-sizing:border-box; }
        html,body{ height:100%; margin:0; }
        body{
            font-family: Inter, system-ui, -apple-system, Segoe UI, Roboto, Arial, sans-serif;
            background: linear-gradient(135deg,#eef4ff, #f8fafc 40%, #f1f5ff);
            display:flex; flex-direction:column; min-height:100vh;
        }
        .bg-shape{ position:fixed; inset:0; pointer-events:none; overflow:hidden; }
        .blob{ position:absolute; filter: blur(60px); opacity:.5; border-radius:50%; transform: translate(-50%,-50%); }
        .b1{ width:380px; height:380px; left:12%; top:10%; background:#9ec3ff; }
        .b2{ width:420px; height:420px; right:-4%; bottom:-6%; background:#bed7ff; }

        .login-wrap{ width:100%; max-width:420px; margin:auto; position:relative; z-index:1; }
        .login{
            background:#ffffff; border:1px solid #e6ebf5; border-radius:16px; padding:28px 24px;
            box-shadow: 0 14px 40px rgba(15,23,42,.10);
        }
        .brand{ display:flex; align-items:center; justify-content:center; gap:10px; margin-bottom:10px; }
        .brand-badge{
            width:42px; height:42px; border-radius:12px; background:#265BA9; color:#fff; font-weight:800;
            display:flex; align-items:center; justify-content:center; box-shadow: 0 8px 20px rgba(38,91,169,.25);
        }
        h2{ margin:6px 0 4px; text-align:center; font-size:22px; font-weight:800; color:#0b1220; }
        .sub{ text-align:center; color:#64748b; font-size:14px; margin-bottom:18px; }

        .group{ margin-bottom:14px; }
        .label{ font-size:13px; font-weight:700; color:#334155; margin-bottom:6px; display:block; }
        .field{ position:relative; }
        .field i{ position:absolute; left:12px; top:50%; transform:translateY(-50%); color:#265BA9; opacity:.85; }
        .input{
            width:100%; padding:12px 12px 12px 38px; border:1px solid #dbe3f0; border-radius:12px; background:#f9fbff;
            color:#0f172a; font-size:14px; transition:.2s;
        }
        .input:focus{ outline:none; border-color:#265BA9; box-shadow:0 0 0 4px rgba(38,91,169,.15); background:#ffffff; }
        .hint{ color:#6b7280; font-size:12px; margin-top:6px; }

        .btn{
            width:100%; padding:12px; border:none; cursor:pointer; border-radius:12px; background:#265BA9;
            color:#fff; font-weight:800; font-size:15px; box-shadow:0 10px 24px rgba(38,91,169,.25); transition:.2s;
        }
        .btn:hover{ background:#1f4d90; }
        .btn:disabled{ opacity:.7; cursor:not-allowed; }
        .home{
            display:block; text-align:center; margin-top:10px; padding:10px; background:#eef3fb; color:#1f2937;
            text-decoration:none; border-radius:12px; font-weight:700; border:1px solid #e6ebf5;
        }
        .home:hover{ background:#e7eefb; }

        .toast-wrap{ position: fixed; top: 18px; right: 18px; z-index: 9999; display:flex; flex-direction:column; gap:12px; }
        .toast{
            min-width: 280px; max-width: 360px; display:flex; align-items:flex-start; gap:10px; background:#fff;
            border:1px solid #fee2e2; border-left:6px solid #ef4444; border-radius:12px; box-shadow:0 18px 40px rgba(15,23,42,.12);
            padding:12px 14px; animation: toast-in .35s ease forwards;
        }
        .toast.success{ border-left-color:#22c55e; border-color:#dcfce7; }
        .toast.warn{ border-left-color:#f59e0b; border-color:#fef3c7; }
        .toast .icon{ width:28px; height:28px; border-radius:50%; display:flex; align-items:center; justify-content:center; flex:none; margin-top:2px; }
        .toast.error .icon{ background:#fee2e2; color:#ef4444; }
        .toast.success .icon{ background:#dcfce7; color:#22c55e; }
        .toast.warn .icon{ background:#fef3c7; color:#f59e0b; }
        .toast .title{ font-weight:800; margin-bottom:2px; color:#0b1220; }
        .toast .msg{ color:#475569; font-size:14px; }
        .toast .close{ margin-left:auto; border:none; background:transparent; cursor:pointer; font-size:18px; line-height:1; color:#94a3b8; }
        .toast .close:hover{ color:#475569; }
        @keyframes toast-in { from{opacity:0; transform:translateY(-8px) translateX(8px);} to{opacity:1; transform:none;} }
        @keyframes toast-out { to{opacity:0; transform:translateY(-8px) translateX(8px);} }
    </style>
</head>
<body>

<div class="bg-shape"><div class="blob b1"></div><div class="blob b2"></div></div>

<div class="login-wrap">
    <div class="login">
        <div class="brand"><div class="brand-badge">FPT</div></div>
        <h2>Đăng nhập Khách hàng</h2>
        <div class="sub">Nhập tên và số điện thoại để tiếp tục dịch vụ.</div>

        <form action="LoginCustServlet" method="post" id="loginForm" novalidate>
            <div class="group">
                <label class="label" for="name">Tên khách hàng</label>
                <div class="field">
                    <i class="fa-solid fa-user"></i>
                    <input class="input" type="text" id="name" name="txtname" placeholder="VD: Nguyễn Văn A" required>
                </div>
            </div>

            <div class="group">
                <label class="label" for="phone">Số điện thoại</label>
                <div class="field">
                    <i class="fa-solid fa-phone"></i>
                    <input class="input" type="tel" id="phone" name="txtphone"
                           inputmode="numeric" pattern="\d{9,11}" title="Vui lòng nhập 9–11 chữ số"
                           placeholder="VD: 0901234567" maxlength="11" required>
                </div>
                <div class="hint">Nhập 9–11 chữ số (không khoảng trắng/ký tự khác).</div>
            </div>

            <button type="submit" class="btn" id="btnSubmit">
                <i class="fa-solid fa-right-to-bracket" style="margin-right:6px;"></i>Đăng nhập
            </button>
        </form>

        <a href="index.html" class="home"><i class="fa-solid fa-house" style="margin-right:6px;"></i>Trang chủ</a>
    </div>
</div>

<div id="toastRoot" class="toast-wrap"></div>

<script>
    (function () {
        var form = document.getElementById('loginForm');
        var btn = document.getElementById('btnSubmit');
        form.addEventListener('submit', function () {
            if (!form.checkValidity()) {
                return;
            }
            btn.disabled = true;
            btn.innerHTML = '<i class="fa-solid fa-circle-notch fa-spin" style="margin-right:6px;"></i>Đang xử lý...';
        });
    })();

    function showToast(type, title, message, timeout) {
        var root = document.getElementById('toastRoot');
        if (!root) return;
        var t = document.createElement('div');
        t.className = 'toast ' + (type || 'error');
        var icon = document.createElement('div');
        icon.className = 'icon';
        icon.innerHTML = type === 'success' ? '<i class="fa-solid fa-check"></i>' :
            type === 'warn' ? '<i class="fa-solid fa-triangle-exclamation"></i>' :
                '<i class="fa-solid fa-xmark"></i>';
        var body = document.createElement('div');
        var h = document.createElement('div');
        h.className = 'title';
        h.textContent = title || 'Thông báo';
        var p = document.createElement('div');
        p.className = 'msg';
        p.textContent = message || '';
        var close = document.createElement('button');
        close.className = 'close';
        close.innerHTML = '&times;';
        close.addEventListener('click', dismiss);
        body.appendChild(h);
        body.appendChild(p);
        t.appendChild(icon);
        t.appendChild(body);
        t.appendChild(close);
        root.appendChild(t);
        var timer, delay = timeout || 4000;
        function start() { timer = setTimeout(dismiss, delay); }
        function dismiss() {
            t.style.animation = 'toast-out .25s ease forwards';
            setTimeout(function () { if (t.parentNode) t.parentNode.removeChild(t); }, 220);
        }
        t.addEventListener('mouseenter', function () { clearTimeout(timer); });
        t.addEventListener('mouseleave', start);
        start();
    }

    <% if (request.getAttribute("error") != null) { %>
    showToast('error', 'Thất bại!', '<%= String.valueOf(request.getAttribute("error")) %>', 4500);
    <% } %>
</script>
</body>
</html>
