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
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {

                $("button").click(function ()
                {
                        var text = $(this).val();
                        $.ajax({
                        url: "ajax_updateStock.jsp",
                        data: { id: text},
                        success: function (result)
                                {
                                    $("#div1").html(result);
                                }
                    });
                });
            });
        </script>

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
                <h3><span>View Products </span></h3>
                <p></p>
                <div class="new_grids">

                    <!--.............-->
                    <center>

                        <table class="table-bordered" width="100%">
            <%
                String str = "SELECT p.*,s.stock FROM `products` p,`stock` s WHERE p.`pid`=s.`pid`";
                Iterator itr = con.getData(str).iterator();
                if (itr.hasNext()) {
            %>
            <tr><th>Name</th><th>Category</th><th>Rate</th><th>Unit</th><th>Rack</th><th>Stock</th><th>Image</th><th>Stock</th></tr>

            <%
                while (itr.hasNext()) {
                    Vector v = (Vector) itr.next();
            %>
            <tr><td><%=v.get(1)%></td><td><%=v.get(2)%></td><td><%=v.get(3)%></td><td><%=v.get(4)%></td><td><%=v.get(5)%></td><td><%=v.get(8)%></td><td><img src="product_images/<%=v.get(6)%>" height="100px" width="100px"></td><td><button value="<%=v.get(0)%>">Stock</button></td></tr>  
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
