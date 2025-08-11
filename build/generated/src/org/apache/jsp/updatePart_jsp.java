package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Parts;
import dao.PartDAO;
import model.Saler;

public final class updatePart_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");

    // Lấy partID từ request
    String partID = request.getParameter("partID");

    // Lấy thông tin bộ phận từ DAO
    PartDAO partDAO = new PartDAO();
    Parts part = partDAO.getPartById(partID);

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <title>Update Part</title>\n");
      out.write("        <style>\n");
      out.write("            .update-part-form {\n");
      out.write("                width: 400px;\n");
      out.write("                margin: 30px auto;\n");
      out.write("                padding: 20px;\n");
      out.write("                background: white;\n");
      out.write("                border-radius: 8px;\n");
      out.write("                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.15);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .update-part-form h2 {\n");
      out.write("                text-align: center;\n");
      out.write("                color: #333;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .update-part-form label {\n");
      out.write("                display: block;\n");
      out.write("                font-weight: 500;\n");
      out.write("                margin-top: 10px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .update-part-form input {\n");
      out.write("                width: 100%;\n");
      out.write("                padding: 8px;\n");
      out.write("                margin-top: 5px;\n");
      out.write("                border: 1px solid #ccc;\n");
      out.write("                border-radius: 5px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .update-part-form input[type=\"submit\"] {\n");
      out.write("                background: #28a745;\n");
      out.write("                color: white;\n");
      out.write("                cursor: pointer;\n");
      out.write("                margin-top: 15px;\n");
      out.write("                border: none;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .update-part-form input[type=\"submit\"]:hover {\n");
      out.write("                background: #218838;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .update-part-form .btn {\n");
      out.write("                display: inline-block;\n");
      out.write("                text-align: center;\n");
      out.write("                background: #6c757d;\n");
      out.write("                color: white;\n");
      out.write("                padding: 8px 16px;\n");
      out.write("                margin-top: 10px;\n");
      out.write("                text-decoration: none;\n");
      out.write("                border-radius: 5px;\n");
      out.write("                transition: background 0.3s ease-in-out;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .update-part-form .btn:hover {\n");
      out.write("                background: #5a6268;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            // Kiểm tra đăng nhập
            Saler kq = (Saler) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("saleLogin.jsp").forward(request, response);
                return;
            }
        
      out.write("\n");
      out.write("\n");
      out.write("        <h2>Update Part Details</h2>\n");
      out.write("        <form action=\"UpdatePartServlet\" method=\"post\" class=\"update-part-form\">\n");
      out.write("\n");
      out.write("            <input type=\"hidden\" name=\"partID\" value=\"");
      out.print( part.getPartID());
      out.write("\" required>\n");
      out.write("\n");
      out.write("            <label>Part Name:</label>\n");
      out.write("            <input type=\"text\" name=\"partName\" value=\"");
      out.print( part.getPartName());
      out.write("\" required>\n");
      out.write("\n");
      out.write("            <label>Purchase Price:</label>\n");
      out.write("            <input type=\"text\" name=\"purchasePrice\" value=\"");
      out.print( part.getPurchasePrice());
      out.write("\" required>\n");
      out.write("\n");
      out.write("            <label>Retail Price:</label>\n");
      out.write("            <input type=\"text\" name=\"retailPrice\" value=\"");
      out.print( part.getRetailPrice());
      out.write("\" required>\n");
      out.write("\n");
      out.write("            <input type=\"submit\" value=\"Update Part\">\n");
      out.write("\n");
      out.write("            <a href=\"SaleDashboard.jsp\" class=\"btn\">Back</a>\n");
      out.write("        </form>\n");
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
