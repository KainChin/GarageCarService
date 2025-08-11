package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Customer;

public final class customerLogin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"vi\">\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Đăng Nhập Khách Hàng</title>\n");
      out.write("        <style>\n");
      out.write("            * {\n");
      out.write("                margin: 0;\n");
      out.write("                padding: 0;\n");
      out.write("                box-sizing: border-box;\n");
      out.write("            }\n");
      out.write("            body {\n");
      out.write("                font-family: Arial, sans-serif;\n");
      out.write("                background: #f4f4f9; /* Brighter background */\n");
      out.write("                display: flex;\n");
      out.write("                justify-content: center;\n");
      out.write("                align-items: center;\n");
      out.write("                height: 100vh;\n");
      out.write("            }\n");
      out.write("            .login-container {\n");
      out.write("                background: #ffffff; /* Bright white login box */\n");
      out.write("                padding: 30px;\n");
      out.write("                border-radius: 12px;\n");
      out.write("                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);\n");
      out.write("                width: 350px;\n");
      out.write("                text-align: center;\n");
      out.write("            }\n");
      out.write("            .login-container h2 {\n");
      out.write("                margin-bottom: 20px;\n");
      out.write("                color: #333; /* Darker text for contrast */\n");
      out.write("            }\n");
      out.write("            .input-group {\n");
      out.write("                margin-bottom: 15px;\n");
      out.write("                text-align: left;\n");
      out.write("            }\n");
      out.write("            .input-group label {\n");
      out.write("                font-size: 14px;\n");
      out.write("                font-weight: bold;\n");
      out.write("                color: #555; /* Slightly darker label */\n");
      out.write("                display: block;\n");
      out.write("                margin-bottom: 5px;\n");
      out.write("            }\n");
      out.write("            .input-group input {\n");
      out.write("                width: 100%;\n");
      out.write("                padding: 12px;\n");
      out.write("                border: 1px solid #ccc; /* Lighter input border */\n");
      out.write("                border-radius: 8px;\n");
      out.write("                font-size: 14px;\n");
      out.write("                background: #f9f9f9; /* Bright input background */\n");
      out.write("                color: #333; /* Darker text */\n");
      out.write("                transition: 0.3s;\n");
      out.write("            }\n");
      out.write("            .input-group input:focus {\n");
      out.write("                border-color: #4a90e2;\n");
      out.write("                outline: none;\n");
      out.write("            }\n");
      out.write("            .login-btn {\n");
      out.write("                background: #4a90e2;\n");
      out.write("                color: white;\n");
      out.write("                padding: 12px;\n");
      out.write("                border: none;\n");
      out.write("                border-radius: 8px;\n");
      out.write("                font-size: 16px;\n");
      out.write("                cursor: pointer;\n");
      out.write("                width: 100%;\n");
      out.write("                transition: 0.3s;\n");
      out.write("            }\n");
      out.write("            .login-btn:hover {\n");
      out.write("                background: #357abd;\n");
      out.write("            }\n");
      out.write("            .profile-img {\n");
      out.write("                width: 90px;\n");
      out.write("                height: 90px;\n");
      out.write("                border-radius: 50%;\n");
      out.write("                object-fit: cover;\n");
      out.write("                margin-bottom: 15px;\n");
      out.write("                border: 3px solid #4a90e2;\n");
      out.write("            }\n");
      out.write("            .error-message {\n");
      out.write("                color: red;\n");
      out.write("                font-size: 14px;\n");
      out.write("                margin-top: 10px;\n");
      out.write("                text-align: center;\n");
      out.write("            }\n");
      out.write("            .home-btn {\n");
      out.write("                display: block;\n");
      out.write("                text-align: center;\n");
      out.write("                margin-top: 10px;\n");
      out.write("                padding: 10px;\n");
      out.write("                background: #ddd;\n");
      out.write("                color: #333;\n");
      out.write("                border-radius: 8px;\n");
      out.write("                text-decoration: none;\n");
      out.write("                font-size: 14px;\n");
      out.write("                transition: 0.3s;\n");
      out.write("            }\n");
      out.write("            .home-btn:hover {\n");
      out.write("                background: #bbb;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"login-container\">\n");
      out.write("            <img src=\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyfVtcUogZZ7LMhmB8aiXErQKr-7n8JST0jw&s\" alt=\"Ảnh khách hàng\" class=\"profile-img\">\n");
      out.write("            <h2>Đăng Nhập Khách Hàng</h2>\n");
      out.write("            <form action=\"LoginCustServlet\" method=\"post\">\n");
      out.write("                <div class=\"input-group\">\n");
      out.write("                    <label for=\"name\">Tên khách hàng</label>\n");
      out.write("                    <input type=\"text\" id=\"name\" name=\"txtname\" required>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"input-group\">\n");
      out.write("                    <label for=\"phone\">Số điện thoại</label>\n");
      out.write("                    <input type=\"text\" id=\"phone\" name=\"txtphone\" required>\n");
      out.write("                </div>\n");
      out.write("                <button type=\"submit\" class=\"login-btn\">Đăng Nhập</button>\n");
      out.write("\n");
      out.write("                <!-- Error message displayed below the button -->\n");
      out.write("            </form>\n");
      out.write("            <!-- Nút quay lại trang chủ -->\n");
      out.write("            <a href=\"index.html\" class=\"home-btn\">Trang Chủ</a>\n");
      out.write("            <div class=\"error-message\"> \n");
      out.write("                ");

                    if (request.getAttribute("error") != null) { // bắt lỗi đăng nhập 
                        out.print(request.getAttribute("error"));
                    }
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <div class=\"error-message\">\n");
      out.write("                ");

                    String errorMessage = (String) session.getAttribute("error1"); //loi tu changeprogile
                    if (errorMessage != null) {
                        out.print(errorMessage);
                        session.removeAttribute("error1"); // Xóa lỗi sau khi hiển thị
                    }
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
