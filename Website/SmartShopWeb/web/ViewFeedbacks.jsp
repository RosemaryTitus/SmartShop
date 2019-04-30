<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<jsp:useBean id="con" class="connection.dbconnection"/>
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
    <head>
        <title>Smart Shop</title>
        <!-- for-mobile-apps -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Smart Shop Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
            function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- //for-mobile-apps -->
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <!-- pignose css -->
        <link href="css/pignose.layerslider.css" rel="stylesheet" type="text/css" media="all" />


        <!-- //pignose css -->
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <!-- js -->
        <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
        <!-- //js -->
        <!-- cart -->
        <script src="js/simpleCart.min.js"></script>
        <!-- cart -->
        <!-- for bootstrap working -->
        <script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
        <!-- //for bootstrap working -->
        <link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='//fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,900,900italic,700italic' rel='stylesheet' type='text/css'>
        <script src="js/jquery.easing.min.js"></script>
    </head>
    <body>
        <!-- header -->


        <!-- //header -->
        <!-- header-bot -->
        <%@include file="AdminHeader.jsp" %>
        <!-- //header-bot -->
        <!-- banner -->
        
        <!-- //banner-top -->
        <!-- banner -->
       
        <!-- //banner -->
        <!-- content -->

        <div class="new_arrivals">
            <div class="container">
                <h3><span>View FeedBacks </span></h3>
                <p></p>
                <div class="new_grids">

                    <!--.............-->
                    <center>

                        <table class="table-bordered" width="100%">
            <%
                String str = "SELECT * FROM `feedback` f,`register` r WHERE f.`uid`=r.`rid`";
                Iterator itr = con.getData(str).iterator();
                if (itr.hasNext()) {
            %>
            <tr><th>Name</th><th>email</th><th>phone</th><th>Date</th><th>Feedback</th><th>Rating</th></tr>

            <%
                while (itr.hasNext()) {
                    Vector v = (Vector) itr.next();
            %>
            <tr><td><%=v.get(6)%></td><td><%=v.get(7)%></td><td><%=v.get(8)%></td><td><%=v.get(4)%></td><td><%=v.get(3)%></td><td><%=v.get(2)%></td></tr>  
                    <%
                            }
                        }

                    %>
        </table>
        
        <br>
        <div id="div1">

        </div>
                    </center>    
                    <!--.............-->
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <!-- //content -->

        <!-- content-bottom -->

        <!-- //content-bottom -->
        <!-- product-nav -->


        <!-- //product-nav -->

        <!-- footer -->
        <div class="footer">
            <div class="container">
                <div class="col-md-3 footer-left">
                    <h2><a href=""><img src="images/logo3.jpg" alt=" " /></a></h2>

                </div>
            </div>

            <p class="copy-right"> Smart Shop. | Lcc Edu</p>
        </div>

        <!-- //footer -->
       
    </body>
</html>
