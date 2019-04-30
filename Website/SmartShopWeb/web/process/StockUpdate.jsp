<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<jsp:useBean id="con" class="connection.dbconnection"/>
<jsp:useBean id="obj" class="model.Product"/>
<jsp:setProperty name="obj" property="*"/>
<%
    
   String str="UPDATE `stock` SET `stock`='"+obj.getStock()+"',`date`='"+obj.getDate()+"' WHERE `pid`='"+obj.getPid()+"'"; 
    System.out.println(str);
    if(con.putData(str)>0)
    {
        System.out.println("Successs");
        response.sendRedirect("../ViewProducts.jsp");
    }
    else
    {
        System.out.println("failedd");
        response.sendRedirect("../ViewProducts.jsp");
    }

%>