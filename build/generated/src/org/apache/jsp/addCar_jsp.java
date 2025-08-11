package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Saler;

public final class addCar_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("<style>\n");
      out.write("    .car-form {\n");
      out.write("        width: 400px;\n");
      out.write("        margin: 30px auto;\n");
      out.write("        padding: 20px;\n");
      out.write("        background: white;\n");
      out.write("        border-radius: 8px;\n");
      out.write("        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.15);\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .car-form label {\n");
      out.write("        display: block;\n");
      out.write("        font-weight: 500;\n");
      out.write("        margin-top: 10px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .car-form input {\n");
      out.write("        width: 100%;\n");
      out.write("        padding: 8px;\n");
      out.write("        margin-top: 5px;\n");
      out.write("        border: 1px solid #ccc;\n");
      out.write("        border-radius: 5px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .car-form input[type=\"submit\"] {\n");
      out.write("        background: #007bff;\n");
      out.write("        color: white;\n");
      out.write("        cursor: pointer;\n");
      out.write("        margin-top: 15px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .car-form input[type=\"submit\"]:hover {\n");
      out.write("        background: #0056b3;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .car-form .btn {\n");
      out.write("        display: inline-block;\n");
      out.write("        text-align: center;\n");
      out.write("        background: #6c757d; /* Màu xám nh?t */\n");
      out.write("        color: white;\n");
      out.write("        padding: 8px 16px;\n");
      out.write("        margin-top: 10px;\n");
      out.write("        text-decoration: none;\n");
      out.write("        border-radius: 5px;\n");
      out.write("        transition: background 0.3s ease-in-out;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .car-form .btn:hover {\n");
      out.write("        background: #5a6268; /* Màu xám ??m h?n khi hover */\n");
      out.write("    }\n");
      out.write("\n");
      out.write("</style>\n");

    Saler kq = (Saler) session.getAttribute("user");
    if (kq == null) {
        request.setAttribute("error", "You must login");
        request.getRequestDispatcher("saleLogin.jsp").forward(request, response);
        return;
    }

      out.write("\n");
      out.write("<form action=\"AddCarServlet\" method=\"post\" class=\"car-form\">\n");
      out.write("    <label>Car ID:</label>\n");
      out.write("    <input type=\"number\" name=\"carID\" required min=\"0\">\n");
      out.write("\n");
      out.write("    <label>Serial Number:</label>\n");
      out.write("    <input type=\"text\" name=\"serialNumber\" required>\n");
      out.write("\n");
      out.write("    <label>Model:</label>\n");
      out.write("    <input type=\"text\" name=\"model\" required>\n");
      out.write("\n");
      out.write("    <label>Colour:</label>\n");
      out.write("    <input type=\"text\" name=\"colour\" required>\n");
      out.write("\n");
      out.write("    <label>Year:</label>\n");
      out.write("    <input type=\"number\" name=\"year\" required>\n");
      out.write("\n");
      out.write("    <input type=\"submit\" value=\"Add Car\">\n");
      out.write("\n");
      out.write("    <a href=\"SaleDashboard.jsp\" class=\"btn\">Back</a>\n");
      out.write("</form>\n");
      out.write("\n");
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
