package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.InvoiceDetail2;
import model.ServiceTicket;
import model.Customer;
import java.util.ArrayList;
import java.util.List;
import model.InvoiceDetail;
import model.Invoice;

public final class CustomerDashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <title>Customer Dashboard</title>\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("    </head>\n");
      out.write("    <body class=\"bg-light\">\n");
      out.write("        ");

            Customer kq = (Customer) session.getAttribute("user");
            if (kq == null) {
                request.setAttribute("error", "You must login");
                request.getRequestDispatcher("customerLogin.jsp").forward(request, response);
                return;
            }
        
      out.write("\n");
      out.write("        <nav class=\"navbar navbar-expand-lg navbar-dark bg-primary p-3\">\n");
      out.write("            <div class=\"container-fluid\">\n");
      out.write("                <span class=\"navbar-brand\">Welcome, ");
      out.print( kq.getCustName());
      out.write("!</span>\n");
      out.write("                <div class=\"navbar-nav ms-auto\">\n");
      out.write("                    <a class=\"nav-link text-white\" href=\"ServiceTicketCustServlet\">View Service Ticket</a>\n");
      out.write("                    <a class=\"nav-link text-white\" href=\"InvoiceCustServlet\">View Invoices</a>\n");
      out.write("                    <a class=\"nav-link text-white\" href=\"UpdateProfile.jsp\">Change Profile</a>\n");
      out.write("                    <a class=\"btn btn-danger ms-3\" href=\"LogoutServlet?role=customer\">Logout</a> \n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <div class=\"container mt-4\">\n");
      out.write("            <h1 class=\"text-center\">Service Ticket & Invoice</h1>\n");
      out.write("            <!-- Hiển thị thông báo khi không có dịch vụ hoặc phụ tùng -->\n");
      out.write("            ");

                String message = (String) session.getAttribute("message");
                if (message != null) {
            
      out.write("\n");
      out.write("            <div class=\"alert alert-success text-center\" role=\"alert\">");
      out.print( message);
      out.write("</div>\n");
      out.write("            ");

                    session.removeAttribute("message");
                }
            
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            ");
 ArrayList<Invoice> list = (ArrayList<Invoice>) request.getAttribute("listInvoice");
                if (list != null && !list.isEmpty()) { 
      out.write("\n");
      out.write("            <table class=\"table table-bordered table-striped mt-4\">\n");
      out.write("                <thead class=\"table-primary text-center\">\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Invoice ID</th>\n");
      out.write("                        <th>Invoice Date</th>\n");
      out.write("                        <th>Serial Number</th>\n");
      out.write("                        <th>Model</th>\n");
      out.write("                        <th>Colour</th>\n");
      out.write("                        <th>Year</th>\n");
      out.write("                        <th>Total Price</th>\n");
      out.write("                        <th>Action</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");
 for (Invoice invoice : list) {
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( invoice.getInvoiceID());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( invoice.getInvoiceDate());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( invoice.getSerialNumber());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( invoice.getModel());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( invoice.getColour());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( invoice.getYear());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( String.format("%,.0f", invoice.getTotalPrice()));
      out.write(" VND</td>\n");
      out.write("                        <td class=\"text-center\">\n");
      out.write("                            <a href=\"InvoiceDetailServlet?invoiceID=");
      out.print( invoice.getInvoiceID());
      out.write("\" class=\"btn btn-info\">Details</a>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("            ");

                List<InvoiceDetail> invoices = (List<InvoiceDetail>) request.getAttribute("serviceDetails");
                List<InvoiceDetail2> invoices2 = (List<InvoiceDetail2>) request.getAttribute("partDetails");

                if (invoices != null && !invoices.isEmpty()) {
            
      out.write("\n");
      out.write("            <h2 class=\"mt-5\">Service Used</h2>\n");
      out.write("            <table class=\"table table-bordered table-striped\">\n");
      out.write("                <thead class=\"table-primary text-center\">\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Service Name</th>\n");
      out.write("                        <th>Hours</th>\n");
      out.write("                        <th>Hourly Rate</th>\n");
      out.write("                        <th>Service Cost</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");
 for (InvoiceDetail invoice : invoices) {
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print( invoice.getServiceName());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( invoice.getHours());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( String.format("%,.0f", invoice.getHourlyRate()));
      out.write(" VND</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( String.format("%,.0f", invoice.getServiceCost()));
      out.write(" VND</td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("            ");
 if (invoices2 != null && !invoices2.isEmpty()) { 
      out.write("\n");
      out.write("            <h2 class=\"mt-5\">Parts Used</h2>\n");
      out.write("            <table class=\"table table-bordered table-striped\">\n");
      out.write("                <thead class=\"table-primary text-center\">\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Part Name</th>\n");
      out.write("                        <th>Number Used</th>\n");
      out.write("                        <th>Price</th>\n");
      out.write("                        <th>Part Total Price</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");
 for (InvoiceDetail2 invoice : invoices2) {
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print( invoice.getPartName());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( invoice.getNumberUsed());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( String.format("%,.0f", invoice.getPrice()));
      out.write(" VND</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( String.format("%,.0f", invoice.getPartTotalPrice()));
      out.write(" VND</td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            ");
 ArrayList<ServiceTicket> list1 = (ArrayList<ServiceTicket>) request.getAttribute("listServiceTicket");
                if (list1 != null && !list1.isEmpty()) { 
      out.write("\n");
      out.write("            <table class=\"table table-bordered table-striped mt-4\">\n");
      out.write("                <thead class=\"table-primary text-center\">\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Service Ticket ID</th>\n");
      out.write("                        <th>Date Received</th>\n");
      out.write("                        <th>Date Returned</th>\n");
      out.write("                        <th>Car ID</th>\n");
      out.write("                        <th>Action</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");
 for (ServiceTicket in : list1) {
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( in.getServiceTicketID());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( in.getDateReceived());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( in.getDateReturned());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">");
      out.print( in.getCarID());
      out.write("</td>\n");
      out.write("                        <td class=\"text-center\">\n");
      out.write("                            <a href=\"#\" class=\"btn btn-info\">Details</a>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("            ");
 }
      out.write("\n");
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
