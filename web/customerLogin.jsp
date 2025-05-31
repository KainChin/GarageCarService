<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng Nhập Khách Hàng</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            body {
                font-family: Arial, sans-serif;
                background: #f4f4f9; /* Brighter background */
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .login-container {
                background: #ffffff; /* Bright white login box */
                padding: 30px;
                border-radius: 12px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                width: 350px;
                text-align: center;
            }
            .login-container h2 {
                margin-bottom: 20px;
                color: #333; /* Darker text for contrast */
            }
            .input-group {
                margin-bottom: 15px;
                text-align: left;
            }
            .input-group label {
                font-size: 14px;
                font-weight: bold;
                color: #555; /* Slightly darker label */
                display: block;
                margin-bottom: 5px;
            }
            .input-group input {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc; /* Lighter input border */
                border-radius: 8px;
                font-size: 14px;
                background: #f9f9f9; /* Bright input background */
                color: #333; /* Darker text */
                transition: 0.3s;
            }
            .input-group input:focus {
                border-color: #4a90e2;
                outline: none;
            }
            .login-btn {
                background: #4a90e2;
                color: white;
                padding: 12px;
                border: none;
                border-radius: 8px;
                font-size: 16px;
                cursor: pointer;
                width: 100%;
                transition: 0.3s;
            }
            .login-btn:hover {
                background: #357abd;
            }
            .profile-img {
                width: 90px;
                height: 90px;
                border-radius: 50%;
                object-fit: cover;
                margin-bottom: 15px;
                border: 3px solid #4a90e2;
            }
            .error-message {
                color: red;
                font-size: 14px;
                margin-top: 10px;
                text-align: center;
            }
            .home-btn {
                display: block;
                text-align: center;
                margin-top: 10px;
                padding: 10px;
                background: #ddd;
                color: #333;
                border-radius: 8px;
                text-decoration: none;
                font-size: 14px;
                transition: 0.3s;
            }
            .home-btn:hover {
                background: #bbb;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyfVtcUogZZ7LMhmB8aiXErQKr-7n8JST0jw&s" alt="Ảnh khách hàng" class="profile-img">
            <h2>Đăng Nhập Khách Hàng</h2>
            <form action="LoginCustServlet" method="post">
                <div class="input-group">
                    <label for="name">Tên khách hàng</label>
                    <input type="text" id="name" name="txtname" required>
                </div>
                <div class="input-group">
                    <label for="phone">Số điện thoại</label>
                    <input type="text" id="phone" name="txtphone" required>
                </div>
                <button type="submit" class="login-btn">Đăng Nhập</button>

                <!-- Error message displayed below the button -->
            </form>
            <!-- Nút quay lại trang chủ -->
            <a href="index.html" class="home-btn">Trang Chủ</a>
            <div class="error-message"> 
                <%
                    if (request.getAttribute("error") != null) { // bắt lỗi đăng nhập 
                        out.print(request.getAttribute("error"));
                    }
                %>
            </div>
        </div>
    </body>
</html>
