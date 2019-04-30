package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Vector;
import java.util.Iterator;

public final class ztest_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/AdminHeader.jsp");
  }

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

      out.write('\n');
      out.write('\n');
      connection.dbconnection con = null;
      synchronized (_jspx_page_context) {
        con = (connection.dbconnection) _jspx_page_context.getAttribute("con", PageContext.PAGE_SCOPE);
        if (con == null){
          con = new connection.dbconnection();
          _jspx_page_context.setAttribute("con", con, PageContext.PAGE_SCOPE);
        }
      }
      out.write("\n");
      out.write("<!--\n");
      out.write("Author: W3layouts\n");
      out.write("Author URL: http://w3layouts.com\n");
      out.write("License: Creative Commons Attribution 3.0 Unported\n");
      out.write("License URL: http://creativecommons.org/licenses/by/3.0/\n");
      out.write("-->\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Smart Shop</title>\n");
      out.write("        <!-- for-mobile-apps -->\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("        <meta name=\"keywords\" content=\"Smart Shop Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, \n");
      out.write("              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design\" />\n");
      out.write("        <script type=\"application/x-javascript\"> addEventListener(\"load\", function() { setTimeout(hideURLbar, 0); }, false);\n");
      out.write("            function hideURLbar(){ window.scrollTo(0,1); } </script>\n");
      out.write("        <!-- //for-mobile-apps -->\n");
      out.write("        <link href=\"css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />\n");
      out.write("        <!-- pignose css -->\n");
      out.write("        <link href=\"css/pignose.layerslider.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />\n");
      out.write("\n");
      out.write("\n");
      out.write("        <!-- //pignose css -->\n");
      out.write("        <link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />\n");
      out.write("        <!-- js -->\n");
      out.write("        <script type=\"text/javascript\" src=\"js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <!-- //js -->\n");
      out.write("        <!-- cart -->\n");
      out.write("        <script src=\"js/simpleCart.min.js\"></script>\n");
      out.write("        <!-- cart -->\n");
      out.write("        <!-- for bootstrap working -->\n");
      out.write("        <script type=\"text/javascript\" src=\"js/bootstrap-3.1.1.min.js\"></script>\n");
      out.write("        <!-- //for bootstrap working -->\n");
      out.write("        <link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>\n");
      out.write("        <link href='//fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,900,900italic,700italic' rel='stylesheet' type='text/css'>\n");
      out.write("        <script src=\"js/jquery.easing.min.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <!-- header -->\n");
      out.write("\n");
      out.write("\n");
      out.write("        <!-- //header -->\n");
      out.write("        <!-- header-bot -->\n");
      out.write("        ");
      out.write("<div class=\"header-bot\">\n");
      out.write("\t<div class=\"container\">\n");
      out.write("\t\t<div class=\"col-md-3 header-left\">\n");
      out.write("\t\t\t<h1><a href=\"index.html\"><img src=\"images/logo3.jpg\"></a></h1>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-md-6 header-middle\">\n");
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-md-3 header-right footer-bottom\">\n");
      out.write("\t\t\t<ul>\n");
      out.write("<!--                            <li><a href=\"index.html\"  data-toggle=\"modal\" data-target=\"#myModal4\"><span>Logout</span></a>-->\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t</li>\n");
      out.write("                                <li><a  href=\"#\" hidden=\"\" ></a></li>\n");
      out.write("\t\t\t\t<li><a  href=\"#\" hidden=\"\"></a></li>\n");
      out.write("\t\t\t\t<li><a  href=\"#\"hidden=\"\"></a></li>\n");
      out.write("\t\t\t\t<li><a  href=\"#\" hidden=\"\"></a></li>\n");
      out.write("\t\t\t</ul>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"clearfix\"></div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"ban-top\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"top_nav_left\">\n");
      out.write("                    <nav class=\"navbar navbar-default\">\n");
      out.write("                        <div class=\"container-fluid\">\n");
      out.write("                            <!-- Brand and toggle get grouped for better mobile display -->\n");
      out.write("                            <div class=\"navbar-header\">\n");
      out.write("                                <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">\n");
      out.write("                                    <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                                    <span class=\"icon-bar\"></span>\n");
      out.write("                                    <span class=\"icon-bar\"></span>\n");
      out.write("                                    <span class=\"icon-bar\"></span>\n");
      out.write("                                </button>\n");
      out.write("                            </div>\n");
      out.write("                            <!-- Collect the nav links, forms, and other content for toggling -->\n");
      out.write("                            <div class=\"collapse navbar-collapse menu--shylock\" id=\"bs-example-navbar-collapse-1\">\n");
      out.write("                                <ul class=\"nav navbar-nav menu__list\">\n");
      out.write("                                    <li class=\"active menu__item menu__item--current\"><a class=\"menu__link\" href=\"index.html\">Home <span class=\"sr-only\">(current)</span></a></li>\n");
      out.write("\n");
      out.write("                                    <li class=\" menu__item\"><a class=\"menu__link\" href=\"ViewBookings.jsp\">View Booking</a></li>\n");
      out.write("                                    <li class=\" menu__item\"><a class=\"menu__link\" href=\"ViewProducts.jsp\">View Products</a></li>\n");
      out.write("                                    <li class=\" menu__item\"><a class=\"menu__link\" href=\"NewProduct.jsp\">New Products</a></li>\n");
      out.write("                                    <li class=\" menu__item\"><a class=\"menu__link\" href=\"index.html\">Logout</a></li>\n");
      out.write("                                    \n");
      out.write("                   \n");
      out.write(" \n");
      out.write("                                </ul>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </nav>\t\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"clearfix\"></div>\n");
      out.write("            </div>\n");
      out.write("        </div>");
      out.write("\n");
      out.write("        <!-- //header-bot -->\n");
      out.write("        <!-- banner -->\n");
      out.write("        \n");
      out.write("        <!-- //banner-top -->\n");
      out.write("        <!-- banner -->\n");
      out.write("       \n");
      out.write("        <!-- //banner -->\n");
      out.write("        <!-- content -->\n");
      out.write("\n");
      out.write("        <div class=\"new_arrivals\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <h3><span>View FeedBacks </span></h3>\n");
      out.write("                <p></p>\n");
      out.write("                <div class=\"new_grids\">\n");
      out.write("\n");
      out.write("                    <!--.............-->\n");
      out.write("                    <center>\n");
      out.write("\n");
      out.write("                        <table class=\"table-bordered\" width=\"100%\">\n");
      out.write("            ");

                String str = "SELECT * FROM `feedback` f,`register` r WHERE f.`uid`=r.`rid`";
                Iterator itr = con.getData(str).iterator();
                if (itr.hasNext()) {
            
      out.write("\n");
      out.write("            <tr><th>Name</th><th>email</th><th>phone</th><th>Date</th><th>Feedback</th><th>Rating</th></tr>\n");
      out.write("\n");
      out.write("            ");

                while (itr.hasNext()) {
                    Vector v = (Vector) itr.next();
            
      out.write("\n");
      out.write("            <tr><td>");
      out.print(v.get(6));
      out.write("</td><td>");
      out.print(v.get(7));
      out.write("</td><td>");
      out.print(v.get(8));
      out.write("</td><td>");
      out.print(v.get(3));
      out.write("</td><td>");
      out.print(v.get(2));
      out.write("</td><td>");
      out.print(v.get(1));
      out.write("</td></tr>  \n");
      out.write("                    ");

                            }
                        }

                    
      out.write("\n");
      out.write("        </table>\n");
      out.write("        \n");
      out.write("        <br>\n");
      out.write("        <div id=\"div1\">\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("                    </center>    \n");
      out.write("                    <!--.............-->\n");
      out.write("                    <div class=\"clearfix\"></div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- //content -->\n");
      out.write("\n");
      out.write("        <!-- content-bottom -->\n");
      out.write("\n");
      out.write("        <!-- //content-bottom -->\n");
      out.write("        <!-- product-nav -->\n");
      out.write("\n");
      out.write("\n");
      out.write("        <!-- //product-nav -->\n");
      out.write("\n");
      out.write("        <!-- footer -->\n");
      out.write("        <div class=\"footer\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"col-md-3 footer-left\">\n");
      out.write("                    <h2><a href=\"\"><img src=\"images/logo3.jpg\" alt=\" \" /></a></h2>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <p class=\"copy-right\"> Smart Shop. | Lcc Edu</p>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- //footer -->\n");
      out.write("       \n");
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
