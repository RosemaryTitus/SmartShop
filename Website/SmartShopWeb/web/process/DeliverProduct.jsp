<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<jsp:useBean id="con" class="connection.dbconnection"/>
<%
    String cartid = request.getParameter("cartid");
    String str = "UPDATE `cart` SET `delstatus`='1' WHERE `cartid`='" + cartid + "'";
    System.out.println(str);
    if (con.putData(str) > 0) {
        System.out.println("Successs");
        response.sendRedirect("../ViewBookings.jsp");
    } else {
        System.out.println("failed");
        response.sendRedirect("../ViewBookings.jsp");
    }

%>