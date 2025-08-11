package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Mechanic;
import model.Service;

public final class updateService_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Cập Nhật Dịch Vụ</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            Mechanic kq = (Mechanic) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("mechanicLogin.jsp").forward(request, response);
                return;
            }
            Service service = (Service) request.getAttribute("service");
        
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"container d-flex justify-content-center mt-5\">\n");
      out.write("            <div class=\"col-md-6 shadow p-4 rounded bg-white\">\n");
      out.write("                <h2 class=\"text-center mb-4 text-primary\">Cập Nhật Dịch Vụ</h2>\n");
      out.write("\n");
      out.write("                ");
 if (request.getAttribute("error") != null) {
      out.write("\n");
      out.write("                <div class=\"alert alert-danger\">");
      out.print( request.getAttribute("error"));
      out.write("</div>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("                ");
 if (request.getAttribute("success") != null) {
      out.write("\n");
      out.write("                <div class=\"alert alert-success\">");
      out.print( request.getAttribute("success"));
      out.write("</div>\n");
      out.write("                ");
 }
      out.write("\n");
      out.write("\n");
      out.write("                <form action=\"UpdateServiceController\" method=\"POST\">\n");
      out.write("                    <input type=\"hidden\" name=\"serviceID\" value=\"");
      out.print( service != null ? service.getServiceID() : "");
      out.write("\">\n");
      out.write("\n");
      out.write("                    <div class=\"mb-3\">\n");
      out.write("                        <label class=\"form-label fw-bold\">Tên Dịch Vụ</label>\n");
      out.write("                        <input type=\"text\" class=\"form-control\" name=\"serviceName\" value=\"");
      out.print( service != null ? service.getServiceName() : "");
      out.write("\" required>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"mb-3\">\n");
      out.write("                        <label class=\"form-label fw-bold\">Giá (VNĐ/giờ)</label>\n");
      out.write("                        <input type=\"text\" class=\"form-control\" name=\"hourlyRate\" value=\"");
      out.print( service != null ? service.getHourlyRate() : "");
      out.write("\" required>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <button type=\"submit\" class=\"btn btn-success w-100\">Cập Nhật</button>\n");
      out.write("                    <a href=\"MechanicDashboard.jsp\" class=\"btn btn-secondary w-100 mt-2\">Hủy</a>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n");
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
