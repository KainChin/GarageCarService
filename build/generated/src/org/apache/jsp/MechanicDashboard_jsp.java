package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import model.ServiceTicket;
import dao.ServiceMechanicDAO;
import model.ServiceMechanic;
import model.Mechanic;
import model.Service;
import java.util.List;
import dao.ServiceDAO;

public final class MechanicDashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    // Kiểm tra đăng nhập
    Mechanic kq = (Mechanic) session.getAttribute("user");
    if (kq == null) {
        request.setAttribute("error", "You must login");
        request.getRequestDispatcher("mechanicLogin.jsp").forward(request, response);
        return;
    }

    // Kiểm tra xem người dùng đã nhấn "View Service" chưa
    String view = request.getParameter("view");
    List<Service> services = new ArrayList<>();
    if ("true".equals(view)) {
        ServiceDAO dao = new ServiceDAO();
        services = dao.getList();
    }
    //Hiển thị danh sách service ticket 
    List<ServiceMechanic> listSM = (List<ServiceMechanic>) request.getAttribute("dataSM");


      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Mechanic Dashboard</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <div class=\"container mt-5\">\n");
      out.write("            <div class=\"d-flex justify-content-between align-items-center mb-3\">\n");
      out.write("                <span class=\"navbar-brand\">Welcome, ");
      out.print( kq.getMechanicName());
      out.write("!</span>\n");
      out.write("                <a class=\"btn btn-danger\" href=\"LogoutServlet?role=mechanic\">Đăng Xuất</a>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <h1 class=\"text-center\">Mechanic Dashboard</h1>\n");
      out.write("            \n");
      out.write("            <ul>\n");
      out.write("\n");
      out.write("                <div class=\"d-flex justify-content-center gap-3 mb-3\">\n");
      out.write("                    <a href=\"MechanicDashboard.jsp?view=true\" class=\"btn btn-primary btn-custom\">View Service</a>\n");
      out.write("                    <a href=\"ServiceTicketServlet?view=tickets\" class=\"btn btn-primary btn-custom\">Search service ticket </a>\n");
      out.write("                    <a href=\"ListServiceMechanic?mechanicID=");
      out.print( kq.getMechanicID());
      out.write("\" class=\"btn btn-primary\">View Service Ticket</a>\n");
      out.write("                </div>\n");
      out.write("            </ul>\n");
      out.write("                \n");
      out.write("            <!-- hiển thị thông báo khi thêm dịch vụ thành công hay lỗi -->\n");
      out.write("            ");
 if (session.getAttribute("successMessage") != null) {
      out.write("\n");
      out.write("            <p style=\"color: green;\">");
      out.print( session.getAttribute("successMessage"));
      out.write("</p>\n");
      out.write("            ");
 session.removeAttribute("successMessage"); 
      out.write("\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("            <!-- hiển thị thông báo khi dịch vụ không tồn tại-->\n");
      out.write("            ");
 if (session.getAttribute("errorMessage") != null) {
      out.write("\n");
      out.write("            <p style=\"color: red;\">");
      out.print( session.getAttribute("errorMessage"));
      out.write("</p>\n");
      out.write("            ");
 session.removeAttribute("errorMessage"); 
      out.write("\n");
      out.write("            ");
 }
      out.write("\n");
      out.write("\n");
      out.write("            <!-- Hiển thị thông báo khi addservice -->\n");
      out.write("            <p style=\"color: red;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${error}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("            <p style=\"color: green;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${success}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <!-- Hiển thị danh sách Service -->\n");
      out.write("            ");
 if (services != null && !services.isEmpty()) { 
      out.write("\n");
      out.write("            <h2 class=\"mt-4\">Danh Sách Dịch Vụ</h2>\n");
      out.write("            <table class=\"table table-bordered text-center\">\n");
      out.write("                <thead class=\"table-dark\">\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Mã Dịch Vụ</th>\n");
      out.write("                        <th>Tên Dịch Vụ</th>\n");
      out.write("                        <th>Giá (VNĐ/giờ)</th>\n");
      out.write("                        <th>Trạng Thái</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");
 for (Service c : services) {
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print( c.getServiceID());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( c.getServiceName());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( c.getHourlyRate());
      out.write(" VNĐ</td>\n");
      out.write("                        <td>\n");
      out.write("                            <a href=\"addService.jsp\" class=\"btn btn-success btn-custom\">\n");
      out.write("                                <i class=\"bi bi-plus-circle\"></i> Add Service\n");
      out.write("                            </a>\n");
      out.write("                            <a href=\"UpdateServiceController?serviceID=");
      out.print( c.getServiceID());
      out.write("\" class=\"btn btn-primary btn-sm\">Cập Nhật</a>\n");
      out.write("                            <form action=\"DeleteServiceController\" method=\"post\" style=\"display:inline;\" \n");
      out.write("                                  onsubmit=\"return confirm('Bạn có chắc chắn muốn xóa dịch vụ này?')\">\n");
      out.write("                                <input type=\"hidden\" name=\"serviceID\" value=\"");
      out.print( c.getServiceID());
      out.write("\">\n");
      out.write("                                <button type=\"submit\" class=\"btn btn-danger btn-sm\">Xóa</button>\n");
      out.write("                            </form>\n");
      out.write("                        </td>\n");
      out.write("\n");
      out.write("                    </tr>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("            ");
 } else if ("true".equals(view)) { 
      out.write("\n");
      out.write("            <p class=\"text-center text-danger\">Không có dịch vụ nào!</p>\n");
      out.write("            ");
 }
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            <!-- Hiển thị danh sách Service Mechanic -->\n");
      out.write("            ");
 if (listSM != null && !listSM.isEmpty()) { 
      out.write("\n");
      out.write("            <h2 class=\"mt-4\">Danh Sách Service Mechanic</h2>\n");
      out.write("            <table class=\"table table-hover table-bordered\">\n");
      out.write("                <thead>\n");
      out.write("                    <tr class=\"text-center\">\n");
      out.write("                        <th>Ticket ID</th>\n");
      out.write("                        <th>Service ID</th>\n");
      out.write("                        <th>Mechanic ID</th>\n");
      out.write("                        <th>Hours</th>\n");
      out.write("                        <th>Comment</th>\n");
      out.write("                        <th>Rate</th>\n");
      out.write("                        <th>Action</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("            ");
 } else if (listSM != null) { 
      out.write("\n");
      out.write("            <p class=\"text-center text-danger\">Không có service ticket nào!</p>\n");
      out.write("            ");
 }
      out.write("\n");
      out.write("\n");
      out.write("            ");
 if ("tickets".equals(view)) { 
      out.write("\n");
      out.write("            <h2><i class=\"bi bi-tools\"></i>Danh sách vé dịch vụ</h2>\n");
      out.write("\n");
      out.write("            <!-- Form tìm kiếm -->\n");
      out.write("            <form action=\"ServiceTicketServlet\" method=\"GET\" class=\"search-form\">\n");
      out.write("                <input type=\"hidden\" name=\"view\" value=\"tickets\">\n");
      out.write("\n");
      out.write("                <label for=\"custID\">Customer ID:</label>\n");
      out.write("                <input type=\"text\" name=\"custID\" id=\"custID\">\n");
      out.write("\n");
      out.write("                <label for=\"carID\">Car ID:</label>\n");
      out.write("                <input type=\"text\" name=\"carID\" id=\"carID\">\n");
      out.write("\n");
      out.write("                <label for=\"dateReceived\">Date Received:</label>\n");
      out.write("                <input type=\"date\" name=\"dateReceived\" id=\"dateReceived\">\n");
      out.write("\n");
      out.write("                <button type=\"submit\">Tìm kiếm</button>\n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("            <div class=\"table-responsive\">\n");
      out.write("                <table class=\"table table-hover table-bordered text-center\">\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Service Ticket ID</th>\n");
      out.write("                        <th>Date Received</th>\n");
      out.write("                        <th>Date Returned</th>\n");
      out.write("                        <th>Customer ID</th>\n");
      out.write("                        <th>Car ID</th>\n");
      out.write("                    </tr>\n");
      out.write("                    ");

                        List<ServiceTicket> list = (List<ServiceTicket>) request.getAttribute("listTickets");
                        if (list != null && !list.isEmpty()) {
                            for (ServiceTicket ticket : list) {
                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print( ticket.getServiceTicketID());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( ticket.getDateReceived());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( ticket.getDateReturned());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( ticket.getCustID());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( ticket.getCarID());
      out.write("</td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");

                        }
                    } else {
                    
      out.write("\n");
      out.write("                    <tr><td colspan=\"6\">Không tìm thấy kết quả phù hợp.</td></tr>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                </table>\n");
      out.write("                ");
 }
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
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

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dataSM}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("l");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                        <tr>\n");
          out.write("                            <td class=\"text-center\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${l.serviceTicketID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("                            <td class=\"text-center\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${l.serviceID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("                            <td class=\"text-center\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${l.mechanicID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("                            <td class=\"text-center\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${l.hours}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("                            <td class=\"text-center\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${l.comment}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("                            <td class=\"text-center\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${l.rate}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write(" VNĐ</td>\n");
          out.write("                            <td class=\"text-center\">\n");
          out.write("                                <a href=\"UpdateServiceMechanic?serviceTicketID=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${l.serviceTicketID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("&serviceID=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${l.serviceID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" \n");
          out.write("                                   class=\"btn btn-primary btn-sm btn-action\">\n");
          out.write("                                    <i class=\"bi bi-pencil-square\"></i> Update\n");
          out.write("                                </a>\n");
          out.write("                            </td>\n");
          out.write("                        </tr>\n");
          out.write("                    ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
