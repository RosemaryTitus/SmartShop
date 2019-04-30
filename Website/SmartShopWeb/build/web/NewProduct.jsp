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
                <h3><span>New Product </span></h3>
                <p></p>
                <div class="new_grids">

                    <!--.............-->
                    <center>

                         <form method="post" action="process/add_product.jsp" enctype="multipart/form-data">
                             <table class="table-condensed" width="50%">
                
                <tbody>
                    <tr>
                        <td>Item name</td>
                        <td><input type="text" name="item" value="" class="form-control" placeholder="Item name"/></td>
                    </tr>
                    <tr>
                        <td>Category</td>
                        <td><select name="category" class="form-control">
                                <option value="Fruits">Fruits</option>
                                <option value="Vegetables">Vegetables</option>
                                <option value="Grocery">Grocery</option>
                                <option value="Bakery">Bakery</option>
                                <option value="Meat&Fish">Meat&Fish</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Rate</td>
                        <td><input type="number" min="1" max="10000" class="form-control" step="1" name="rate" value="" /></td>
                    </tr>
                    <tr>
                        <td>Unit</td>
                        <td><input type="radio" name="unit" value="nos." >nos.  
                            <input type="radio" name="unit" value="Kg" >Kg<br>
                        </td>
                    </tr>
                    <tr>
                        <td>Stock</td>
                        <td><input type="number" min="1" max="1000" step="1" name="stock" class="form-control"/></td>
                    </tr>
                    <tr>
                        <td>Rack</td>
                        <td><input type="number" min="1" max="500" step="1" name="rackno" class="form-control"/></td>
                    </tr>
                    <tr>
                        <td>Image</td>
                        <td><input type="file" name="image" class="form-control"/></td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Submit"  class="btn-lg"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
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
                    <h2><a href="index.html"><img src="images/logo3.jpg" alt=" " /></a></h2>

                </div>
            </div>

            <p class="copy-right"> Smart Shop. | Lcc Edu</p>
        </div>

        <!-- //footer -->
       
    </body>
</html>
