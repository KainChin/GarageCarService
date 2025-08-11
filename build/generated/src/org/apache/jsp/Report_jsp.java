package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Saler;
import model.BestUsedPart;
import model.CarSalesByYear;
import model.CarRevenueByYear;
import model.BestSellingCarModel;
import model.PartRevenue;
import java.util.List;
import model.TopMechanic;

public final class Report_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Car Sales Statistics</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\">\n");
      out.write("        <style>\n");
      out.write("            body {\n");
      out.write("                background-color: #e3f2fd; /* Nền xanh nhạt */\n");
      out.write("            }\n");
      out.write("            .container {\n");
      out.write("                margin-top: 20px;\n");
      out.write("            }\n");
      out.write("            .stats-container {\n");
      out.write("                display: grid;\n");
      out.write("                grid-template-columns: repeat(2, 1fr);\n");
      out.write("                gap: 15px;\n");
      out.write("            }\n");
      out.write("            .card {\n");
      out.write("                padding: 10px;\n");
      out.write("                font-size: 14px;\n");
      out.write("                background-color: #bbdefb; /* Xanh dương nhạt */\n");
      out.write("                border: 1px solid #64b5f6; /* Viền xanh đậm hơn */\n");
      out.write("                border-radius: 8px;\n");
      out.write("                box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);\n");
      out.write("            }\n");
      out.write("            .card h5 {\n");
      out.write("                color: #0d47a1; /* Xanh dương đậm */\n");
      out.write("            }\n");
      out.write("            .table-container {\n");
      out.write("                max-height: 250px;\n");
      out.write("                overflow-y: auto;\n");
      out.write("            }\n");
      out.write("            table {\n");
      out.write("                font-size: 12px;\n");
      out.write("            }\n");
      out.write("            th, td {\n");
      out.write("                padding: 5px;\n");
      out.write("                text-align: center;\n");
      out.write("            }\n");
      out.write("            th {\n");
      out.write("                background: #0d47a1; /* Xanh đậm */\n");
      out.write("                color: white;\n");
      out.write("            }\n");
      out.write("            tr:hover {\n");
      out.write("                background-color: #90caf9; /* Hiệu ứng hover */\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            Saler kq = (Saler) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("saleLogin.jsp").forward(request, response);
                return;
            }
        
      out.write("\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <h2 class=\"text-center mb-4\">Car Sales Statistics</h2>\n");
      out.write("            <div class=\"stats-container\">\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <h5 class=\"text-center\">Cars Sold by Year</h5>\n");
      out.write("                    <div class=\"table-container\">\n");
      out.write("                        <table class=\"table table-striped\">\n");
      out.write("                            <thead>\n");
      out.write("                                <tr><th>Year</th><th>Cars Sold</th></tr>\n");
      out.write("                            </thead>\n");
      out.write("                            <tbody>\n");
      out.write("                                ");
 List<CarSalesByYear> salesData = (List<CarSalesByYear>) request.getAttribute("salesData");
                                    if (salesData != null) {
                                        for (CarSalesByYear entry : salesData) {
      out.write("\n");
      out.write("                                <tr><td>");
      out.print( entry.getYear());
      out.write("</td><td>");
      out.print( entry.getTotalSold());
      out.write("</td></tr>\n");
      out.write("                                ");
 }
                                    } 
      out.write("\n");
      out.write("                            </tbody>\n");
      out.write("                        </table>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <h5 class=\"text-center\">Revenue by Year</h5>\n");
      out.write("                    <div class=\"table-container\">\n");
      out.write("                        <table class=\"table table-striped\">\n");
      out.write("                            <thead>\n");
      out.write("                                <tr><th>Year</th><th>Revenue (VND)</th></tr>\n");
      out.write("                            </thead>\n");
      out.write("                            <tbody>\n");
      out.write("                                ");
 List<CarRevenueByYear> revenueData = (List<CarRevenueByYear>) request.getAttribute("revenueData");
                                    if (revenueData != null) {
                                        for (CarRevenueByYear entry : revenueData) {
      out.write("\n");
      out.write("                                <tr><td>");
      out.print( entry.getYear());
      out.write("</td><td>");
      out.print( String.format("%.0f", entry.getRevenue()));
      out.write(" VND</td></tr>\n");
      out.write("                                ");
 }
                                    } 
      out.write("\n");
      out.write("                            </tbody>\n");
      out.write("                        </table>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <h5 class=\"text-center\">Best Selling Car Models</h5>\n");
      out.write("                    <div class=\"table-container\">\n");
      out.write("                        <table class=\"table table-striped\">\n");
      out.write("                            <thead>\n");
      out.write("                                <tr><th>Model</th><th>Total Sold</th></tr>\n");
      out.write("                            </thead>\n");
      out.write("                            <tbody>\n");
      out.write("                                ");
 List<BestSellingCarModel> bestSellingCars = (List<BestSellingCarModel>) request.getAttribute("bestSellingCars");
                                    if (bestSellingCars != null) {
                                        for (BestSellingCarModel entry : bestSellingCars) {
      out.write("\n");
      out.write("                                <tr><td>");
      out.print( entry.getModel());
      out.write("</td><td>");
      out.print( entry.getTotalSold());
      out.write("</td></tr>\n");
      out.write("                                ");
 }
                                    } 
      out.write("\n");
      out.write("                            </tbody>\n");
      out.write("                        </table>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <h5 class=\"text-center\">Most Used Parts</h5>\n");
      out.write("                    <div class=\"table-container\">\n");
      out.write("                        <table class=\"table table-striped\">\n");
      out.write("                            <thead>\n");
      out.write("                                <tr>\n");
      out.write("                                    <th>Part Name</th>\n");
      out.write("                                    <th>Total Used</th>\n");
      out.write("                                </tr>\n");
      out.write("                            </thead>\n");
      out.write("                            <tbody>\n");
      out.write("                                ");
 List<BestUsedPart> bestUsedParts = (List<BestUsedPart>) request.getAttribute("bestUsedParts");
                                    if (bestUsedParts != null) {
                                        for (BestUsedPart entry : bestUsedParts) {
      out.write("\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>");
      out.print( entry.getPartName());
      out.write("</td>\n");
      out.write("                                    <td>");
      out.print( entry.getTotalUsed());
      out.write("</td>\n");
      out.write("                                </tr>\n");
      out.write("                                ");
 }
                                    } 
      out.write("\n");
      out.write("                            </tbody>\n");
      out.write("                        </table>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <div class=\"card mt-3\">\n");
      out.write("                <h5 class=\"text-center\">Top 3 Mechanics</h5>\n");
      out.write("                <div class=\"table-container\">\n");
      out.write("                    <table class=\"table table-striped\">\n");
      out.write("                        <thead>\n");
      out.write("                            <tr><th>Mechanic ID</th><th>Mechanic Name</th></tr>\n");
      out.write("                        </thead>\n");
      out.write("                        <tbody>\n");
      out.write("                            ");
 List<TopMechanic> topMechanics = (List<TopMechanic>) request.getAttribute("topMechanics");
                                if (topMechanics != null) {
                                    for (TopMechanic entry : topMechanics) {
      out.write("\n");
      out.write("                            <tr><td>");
      out.print( entry.getMechanicID());
      out.write("</td><td>");
      out.print( entry.getMechanicName());
      out.write("</td></tr>\n");
      out.write("                            ");
 }
                                }
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"text-center mt-3\">\n");
      out.write("                <a href=\"SaleDashboard.jsp\" class=\"btn btn-primary\">Back</a>\n");
      out.write("            </div>\n");
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
