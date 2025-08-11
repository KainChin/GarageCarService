package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Mechanic;

public final class addService_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Thêm Dịch Vụ</title>\n");
      out.write("        <style>\n");
      out.write("            body {\n");
      out.write("                font-family: Arial, sans-serif;\n");
      out.write("                background-color: #f0f2f5;\n");
      out.write("                display: flex;\n");
      out.write("                justify-content: center;\n");
      out.write("                align-items: center;\n");
      out.write("                height: 100vh;\n");
      out.write("                margin: 0;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .container {\n");
      out.write("                background: white;\n");
      out.write("                padding: 20px;\n");
      out.write("                border-radius: 10px;\n");
      out.write("                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);\n");
      out.write("                width: 380px;\n");
      out.write("                text-align: center;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            h2 {\n");
      out.write("                color: #333;\n");
      out.write("                margin-bottom: 15px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .form-group {\n");
      out.write("                margin-bottom: 12px;\n");
      out.write("                text-align: left;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            label {\n");
      out.write("                font-weight: bold;\n");
      out.write("                font-size: 14px;\n");
      out.write("                display: block;\n");
      out.write("                margin-bottom: 5px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            input[type=\"text\"],\n");
      out.write("            input[type=\"number\"] {\n");
      out.write("                width: calc(100% - 20px);\n");
      out.write("                padding: 8px;\n");
      out.write("                border: 1px solid #ccc;\n");
      out.write("                border-radius: 5px;\n");
      out.write("                font-size: 14px;\n");
      out.write("                transition: border-color 0.3s;\n");
      out.write("                display: block;\n");
      out.write("                margin: 0 auto;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            input:focus {\n");
      out.write("                border-color: #007bff;\n");
      out.write("                outline: none;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .btn {\n");
      out.write("                width: calc(100% - 20px);\n");
      out.write("                padding: 10px;\n");
      out.write("                margin-top: 8px;\n");
      out.write("                border: none;\n");
      out.write("                border-radius: 5px;\n");
      out.write("                font-size: 15px;\n");
      out.write("                cursor: pointer;\n");
      out.write("                font-weight: bold;\n");
      out.write("                transition: background-color 0.3s;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .btn-primary {\n");
      out.write("                background-color: #007bff;\n");
      out.write("                color: white;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .btn-primary:hover {\n");
      out.write("                background-color: #0056b3;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .btn-secondary {\n");
      out.write("                background-color: #6c757d;\n");
      out.write("                color: white;\n");
      out.write("                text-decoration: none;\n");
      out.write("                display: block;\n");
      out.write("                text-align: center;\n");
      out.write("                padding: 10px;\n");
      out.write("                border-radius: 5px;\n");
      out.write("                margin-top: 8px;\n");
      out.write("                font-size: 15px;\n");
      out.write("                font-weight: bold;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .btn-secondary:hover {\n");
      out.write("                background-color: #5a6268;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .message {\n");
      out.write("                margin-top: 10px;\n");
      out.write("                font-weight: bold;\n");
      out.write("                font-size: 14px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .error {\n");
      out.write("                color: red;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .success {\n");
      out.write("                color: green;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            // Kiểm tra đăng nhập
            Mechanic kq = (Mechanic) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("mechanicLogin.jsp").forward(request, response);
                return;
            }
        
      out.write("\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <h2>Thêm Dịch Vụ</h2>\n");
      out.write("            <form action=\"AddServiceController\" method=\"post\">\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"serviceID\">Mã Dịch Vụ:</label>\n");
      out.write("                    <input type=\"number\" id=\"serviceID\" name=\"serviceID\" required min=\"1\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"serviceName\">Tên Dịch Vụ:</label>\n");
      out.write("                    <input type=\"text\" id=\"serviceName\" name=\"serviceName\" required>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"hourlyRate\">Giá Theo Giờ:</label>\n");
      out.write("                    <input type=\"number\" id=\"hourlyRate\" name=\"hourlyRate\" required min=\"1\">\n");
      out.write("                </div>\n");
      out.write("                <button type=\"submit\" class=\"btn btn-primary\">Thêm Dịch Vụ</button>\n");
      out.write("                <a href=\"MechanicDashboard.jsp\" class=\"btn btn-secondary\">Hủy</a>\n");
      out.write("            </form>\n");
      out.write("            <p class=\"message error\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${error}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("            <p class=\"message success\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${success}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>");
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
