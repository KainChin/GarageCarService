package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Customer;

public final class UpdateProfile_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <title>Update Profile</title>\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("        <style>\n");
      out.write("            body {\n");
      out.write("                background: linear-gradient(to right, #1e3c72, #2a5298);\n");
      out.write("                display: flex;\n");
      out.write("                justify-content: center;\n");
      out.write("                align-items: center;\n");
      out.write("                min-height: 100vh;\n");
      out.write("                margin: 0;\n");
      out.write("                font-family: 'Poppins', sans-serif;\n");
      out.write("            }\n");
      out.write("            .container {\n");
      out.write("                background: white;\n");
      out.write("                padding: 40px;\n");
      out.write("                border-radius: 12px;\n");
      out.write("                box-shadow: 0px 10px 30px rgba(0, 0, 0, 0.2);\n");
      out.write("                max-width: 500px;\n");
      out.write("                width: 100%;\n");
      out.write("                text-align: center;\n");
      out.write("            }\n");
      out.write("            .form-control {\n");
      out.write("                border-radius: 8px;\n");
      out.write("            }\n");
      out.write("            .btn-primary, .btn-danger {\n");
      out.write("                border-radius: 8px;\n");
      out.write("                padding: 12px;\n");
      out.write("                font-weight: bold;\n");
      out.write("                text-transform: uppercase;\n");
      out.write("            }\n");
      out.write("            .btn-primary:hover {\n");
      out.write("                background-color: #2a5298;\n");
      out.write("            }\n");
      out.write("            .btn-danger:hover {\n");
      out.write("                background-color: #b02a37;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        ");

            Customer kq = (Customer) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("customerLogin.jsp").forward(request, response);
            }
        
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <h2 class=\"text-primary mb-4\">Update Profile</h2>\n");
      out.write("            <form action=\"ChangeProfileCustServlet\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" name=\"custID\" value=\"");
      out.print( kq.getCustID());
      out.write("\">\n");
      out.write("\n");
      out.write("                <div class=\"mb-3 text-start\">\n");
      out.write("                    <label for=\"custName\" class=\"form-label\">Name:</label>\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"custName\" name=\"custName\" \n");
      out.write("                           value=\"");
      out.print( kq.getCustName());
      out.write("\" required\n");
      out.write("                           pattern=\"[A-Za-zÀ-Ỵà-ỵ\\s]+\" \n");
      out.write("                           title=\"Chỉ nhập chữ cái tiếng Việt, không chứa số hoặc ký tự đặc biệt\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                <div class=\"mb-3 text-start\">\n");
      out.write("                    <label for=\"custPhone\" class=\"form-label\">Phone:</label>\n");
      out.write("                    <input type=\"number\" class=\"form-control\" id=\"custPhone\" name=\"phone\" value=\"");
      out.print( kq.getPhone());
      out.write("\"required>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"mb-3 text-start\">\n");
      out.write("                    <label for=\"custSex\" class=\"form-label\">Gender:</label>\n");
      out.write("                    <select class=\"form-select\" id=\"custSex\" name=\"sex\">\n");
      out.write("                        <option value=\"M\" ");
      out.print( "M".equals(kq.getSex()) ? "selected" : "");
      out.write(">Male</option>\n");
      out.write("                        <option value=\"F\" ");
      out.print( "F".equals(kq.getSex()) ? "selected" : "");
      out.write(">Female</option>\n");
      out.write("                    </select>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"mb-3 text-start\">\n");
      out.write("                    <label for=\"custAddress\" class=\"form-label\">Address:</label>\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"custAddress\" name=\"custAddress\" value=\"");
      out.print( kq.getCustAddress());
      out.write("\" required>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"d-grid gap-2\">\n");
      out.write("                    <button type=\"submit\" class=\"btn btn-primary\">Update</button>\n");
      out.write("                    <a href=\"CustomerDashboard.jsp\" class=\"btn btn-danger\">Back</a>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n");
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
