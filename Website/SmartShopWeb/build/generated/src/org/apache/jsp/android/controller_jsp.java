package org.apache.jsp.android;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONArray;
import java.util.Vector;
import java.util.Iterator;
import org.json.simple.JSONObject;

public final class controller_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      connection.dbconnection con = null;
      synchronized (_jspx_page_context) {
        con = (connection.dbconnection) _jspx_page_context.getAttribute("con", PageContext.PAGE_SCOPE);
        if (con == null){
          con = new connection.dbconnection();
          _jspx_page_context.setAttribute("con", con, PageContext.PAGE_SCOPE);
        }
      }
      out.write('\n');


    String key = request.getParameter("key").toString().trim();
    System.out.println("****  " + key);
    if (key.equals("register")) {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String str = "INSERT INTO `register`(`name`,`email`,`phone`,`password`,`address`) "
                + "VALUES('" + name + "','" + email + "','" + phone + "','" + pass + "','" + address + "')";

        if (con.putData(str) > 0) {
            JSONObject object = new JSONObject();
            object.put("status", "success");
            System.out.println(object);
            out.println(object);
        } else {
            JSONObject object = new JSONObject();
            object.put("status", "failed");
            System.out.println(object);
            out.println(object);
        }
    }
    if (key.equals("login")) {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String str = "SELECT * FROM `register` WHERE `email`='" + email + "' AND `password`='" + pass + "'";
        System.out.println(str);
        Iterator itr = con.getData(str).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();
            JSONObject object = new JSONObject();
            object.put("status", "success");
            object.put("id", v.get(0).toString());
            System.out.println(object);
            out.println(object);
        } else {
            JSONObject object = new JSONObject();
            object.put("status", "failed");
            out.println(object);
        }

    }
    if (key.equals("getPhone")) {
        String email = request.getParameter("email");
        String str = "SELECT `phone` FROM `register` WHERE `email`='" + email + "'";
        System.out.println(str);
        Iterator itr = con.getData(str).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();
            JSONObject object = new JSONObject();
            object.put("status", "success");
            object.put("phone", v.get(0).toString());
            System.out.println(object);
            out.println(object);
        } else {
            JSONObject object = new JSONObject();
            object.put("status", "failed");
            out.println(object);
        }

    }
    if (key.equals("updatePass")) {
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String str = "UPDATE `register` SET `password`='" + password + "' WHERE `email`='" + email + "'";

        if (con.putData(str) > 0) {
            JSONObject object = new JSONObject();
            object.put("status", "success");
            System.out.println(object);
            out.println(object);
        } else {
            JSONObject object = new JSONObject();
            object.put("status", "failed");
            System.out.println(object);
            out.println(object);
        }
    }
    if (key.equals("getProducts")) {

        String str = "SELECT * FROM `products`";

        JSONArray array = new JSONArray();
        JSONObject resobj = new JSONObject();
        Iterator itr = con.getData(str).iterator();

        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                JSONObject obj = new JSONObject();

                obj.put("pid", v.get(0).toString().trim());
                obj.put("name", v.get(1).toString().trim());
                obj.put("category", v.get(2).toString().trim());
                obj.put("rate", v.get(3).toString().trim());
                obj.put("unit", v.get(4).toString().trim());
                obj.put("rackno", v.get(5).toString().trim());
                obj.put("image", v.get(7).toString().trim());

                array.add(obj);
            }
            resobj.put("response", array);
            out.println(array);
            System.out.println(array);
        } else {
            out.println("failed");
            System.out.println("failed");
        }

    }

    if (key.equals("get_QR_item")) {
        String data = "";
        String pid = request.getParameter("value");
        String str = "SELECT * FROM `products` WHERE `pid`='" + pid + "'";
        System.out.println(str);
        Iterator itr = con.getData(str).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();
            data += v.get(0) + ":" + v.get(1) + ":" + v.get(2) + ":" + v.get(3) + ":" + v.get(4) + ":" + v.get(5) + ":" + v.get(7);
            out.println(data);
        } else {
            out.println("failed");
        }

    }

    if (key.equals("addtocart")) {
        String pid = request.getParameter("pid");
        String uid = request.getParameter("uid");
        String num = request.getParameter("num");
        String amount = request.getParameter("amount");
        Date d = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("YYYY-MM-dd");
        String date = ff.format(d);

        String str = "INSERT INTO `cart`(`uid`,`pid`,`date`,`num`,`total`) "
                + "VALUES('" + uid + "','" + pid + "','" + date + "','" + num + "','" + amount + "')";
        System.out.println(str);
        if (con.putData(str) > 0) {
            out.println("success");
        } else {
            out.println("failed");
        }
    }

    if (key.equals("getCart")) {
        String uid = request.getParameter("uid").toString().trim();
        String str = "SELECT * FROM `cart` c "
                + "INNER JOIN `products` p ON c.`pid` = p.`pid` AND c.`uid`='" + uid + "' AND c.`status`='0'";

        JSONArray array = new JSONArray();
        JSONObject resobj = new JSONObject();
        Iterator itr = con.getData(str).iterator();

        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                JSONObject obj = new JSONObject();

                obj.put("cartid", v.get(0).toString().trim());
                obj.put("pid", v.get(2).toString().trim());
                obj.put("number", v.get(3).toString().trim());
                obj.put("amount", v.get(4).toString().trim());
                obj.put("date", v.get(5).toString().trim());
                obj.put("product", v.get(9).toString().trim());
                obj.put("category", v.get(10).toString().trim());
                obj.put("image", v.get(15).toString().trim());

                array.add(obj);
            }
            resobj.put("response", array);
            out.println(array);
            System.out.println(array);
        } else {
            out.println("failed");
            System.out.println("failed");
        }

    }

    if (key.equals("deletecart")) {
        String cartid = request.getParameter("cartid").toString().trim();
        System.out.println(cartid);
        String str = "DELETE FROM `cart` WHERE `cartid`='" + cartid + "'";

        if (con.putData(str) > 0) {
            out.print("success");
        } else {
            out.print("failed");
        }
    }

    if (key.equals("payment_cart")) {

        String uid = request.getParameter("uid");
        String card = request.getParameter("card");
        String cvv = request.getParameter("cvv");
        String pin = request.getParameter("pin");
        String amt = request.getParameter("amount");

        double amount = Double.parseDouble(amt);
        double balance = 0.0;
        double newbal = 0.0;
        double stock = 0.0;
        double newstock = 0.0;

        String str1 = "SELECT `balance` FROM `payment` WHERE `card_no`='" + card + "' AND `cvv_no`='" + cvv + "' AND `pin`='" + pin + "'";

        String qry1 = "SELECT `pid`,`num` FROM `cart` WHERE `status`='0' AND `uid`='" + uid + "'";
        System.out.println(qry1);
        Iterator qq1 = con.getData(qry1).iterator();
        while (qq1.hasNext()) {
            Vector qv1 = (Vector) qq1.next();
            String qry2 = "SELECT s.`stock` FROM `stock` s, `products` p WHERE s.`pid`=p.`pid` AND p.`pid`='" + qv1.get(0) + "'";
            System.out.println(qry2);
            Iterator qq2 = con.getData(qry2).iterator();
            if (qq2.hasNext()) {
                Vector qv2 = (Vector) qq2.next();
                newstock = Double.parseDouble(qv2.get(0).toString()) - Double.parseDouble(qv1.get(1).toString());
                
                String qry3 = "UPDATE `stock` SET `stock`='" + newstock + "' WHERE `pid`='" + qv1.get(0) + "'";
                System.out.println(qry3);
                con.putData(qry3);
            }
        }

        String str = "UPDATE `cart` SET `status`='1' WHERE `uid`='" + uid + "'";

        Iterator itr = con.getData(str1).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();

            balance = Double.parseDouble(v.get(0).toString().trim());
            if (balance > amount) {
                newbal = balance - amount;

                String str3 = "UPDATE `payment` SET `balance`='" + newbal + "' WHERE `card_no`='" + card + "' AND `cvv_no`='" + cvv + "' AND `pin`='" + pin + "'";
                if (con.putData(str) > 0 && con.putData(str3) > 0) {
                    out.println("success");
                } else {
                    out.println("failed");
                }
            } else {
                out.println("No sufficiant balance");
            }

        } else {
            out.println("No account details found");
        }
    }

    if (key.equals("getBooking")) {
        String uid = request.getParameter("uid").toString().trim();
        String str = "SELECT * FROM `cart` c "
                + "INNER JOIN `products` p ON c.`pid` = p.`pid` AND c.`uid`='" + uid + "' AND c.`status`='1'";

        JSONArray array = new JSONArray();
        JSONObject resobj = new JSONObject();
        Iterator itr = con.getData(str).iterator();

        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                JSONObject obj = new JSONObject();

                obj.put("cartid", v.get(0).toString().trim());
                obj.put("pid", v.get(2).toString().trim());
                obj.put("number", v.get(3).toString().trim());
                obj.put("amount", v.get(4).toString().trim());
                obj.put("date", v.get(5).toString().trim());
                obj.put("product", v.get(9).toString().trim());
                obj.put("category", v.get(10).toString().trim());
                obj.put("image", v.get(15).toString().trim());

                array.add(obj);
            }
            resobj.put("response", array);
            out.println(array);
            System.out.println(array);
        } else {
            out.println("failed");
            System.out.println("failed");
        }

    }

    if (key.trim().equals("setFeedback")) {
        String feeback = request.getParameter("feedback");
        String rating = request.getParameter("rating");
        String uid = request.getParameter("uid");
        Date d = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("YYYY-MM-dd");
        String date = ff.format(d);

        String str = "INSERT INTO `feedback`(`uid`,`rating`,`feedback`,`date`) "
                + "VALUES('" + uid + "','" + rating + "','" + feeback + "','" + date + "')";
        if (con.putData(str) > 0) {
            out.println("success");
        } else {
            out.println("failed");
        }
    }

    if (key.equals("view_feedback")) {
        String str = "SELECT * FROM  `feedback` f INNER JOIN `register` r ON r.`rid` = f.`uid`;";

        JSONArray array = new JSONArray();
        Iterator itr = con.getData(str).iterator();

        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                JSONObject obj = new JSONObject();

                obj.put("fid", v.get(0).toString().trim());
                obj.put("uid", v.get(1).toString().trim());
                obj.put("rating", v.get(2).toString().trim());
                obj.put("feedback", v.get(3).toString().trim());
                obj.put("date", v.get(4).toString().trim());
                obj.put("name", v.get(6).toString().trim());
                obj.put("email", v.get(7).toString().trim());
                obj.put("phone", v.get(8).toString().trim());

                array.add(obj);
            }
            out.println(array);
            System.out.println(array);
        }
        else
        {
            out.println("failed");
        }
    }
    


if (key.equals("checkemailid")) {
        String email = request.getParameter("email");
        String str = "SELECT * FROM `register` WHERE `email`='" + email + "'";
        System.out.println(str);
        Iterator itr = con.getData(str).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();
            
            out.println("success");
        } else {
            
            out.println("failed");
        }

    }

if (key.equals("getBankDetails")) {
       
        String str = "SELECT `card_no`,`type`,`cvv_no`,`pin`,`balance` FROM `payment`";
        System.out.println(str);
        String respo="";
        Iterator itr = con.getData(str).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();
            respo=v.get(0)+"#"+v.get(1)+"#"+v.get(2)+"#"+v.get(3)+"#"+v.get(4);
            out.println(respo);
        } else {
            
            out.println("failed");
        }

    }


      out.write('\n');
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
