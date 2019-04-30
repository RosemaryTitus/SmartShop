<%-- 
    Document   : ajax_updateStock
    Created on : 16 Nov, 2018, 12:02:01 PM
    Author     : TeamJava
--%>

<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.stream.Stream"%>
<jsp:useBean id="con1" class="connection.dbconnection"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
       <%
       String id=request.getParameter("id");
       System.out.println(id);
       
       String str1 = "SELECT p.`name`,s.stock FROM `products` p,`stock` s WHERE p.`pid`=s.`pid` AND p.pid='"+id+"'";
                Iterator itr1 = con1.getData(str1).iterator();
                if (itr1.hasNext()) {
                    Vector v1 = (Vector) itr1.next();
                
                    
                
       %>
        
       <form action="process/StockUpdate.jsp">
           <br><br>
           <h3> Add Stock Details</h3>
           <input type="hidden" name="pid" value="<%=id%>"> 
           <input type="text" value="<%=v1.get(0) %>" disabled=""> 
           <input type="number" value="<%=v1.get(1) %> " min="1" max="10000" step="1" name="stock"> 
           <input type="submit" value="Update Stock">
       </form>
  
           <%}

%>
    </body>
</html>
