<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="connection.dbconnection"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%@page import="org.apache.commons.fileupload.*" %>
<%@page import="org.apache.commons.fileupload.servlet.*" %>
<%@page import="org.apache.commons.fileupload.disk.*" %>
<%@page import="java.io.FileInputStream"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.File"%>

<jsp:useBean id="con" class="connection.dbconnection"/>
<%
//declaring a fileitem variable
    FileItem f_item = null;
    String file_name = "";
    String co = "";
    String encodedfile = "";
    dbconnection conn = new dbconnection();
    // String pid = session.getAttribute("uid").toString();
    String item = "", category = "", rate = "", unit = "", stock = "", rackno = "", file = "";
//checking if request cotains multipart data
    Date d = new Date();
    SimpleDateFormat ff = new SimpleDateFormat("YYYY-MM-dd");
    String date = ff.format(d).trim();
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);

    if (isMultipart) {

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        //declaring a list of form fields
        List items_list = null;

        //assigning fields to list 'items_list'
        try {
            items_list = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            out.println(ex);
        }

        //declaring iterator
        Iterator itr = items_list.iterator();

        //iterating through the list 'items_list'
        if (itr.hasNext()) {
            //typecasting next element in items_list as fileitem
            f_item = (FileItem) itr.next();

            //checking if 'f_item' contains a formfield(common controls like textbox,dropdown,radio buttonetc)
            while (f_item.isFormField()) {
                //getting fieldname and value

                String field = f_item.getFieldName();
                String value = f_item.getString();

                if (field.equalsIgnoreCase("item")) {
                    item = value;
                }
                if (field.equalsIgnoreCase("category")) {
                    category = value;
                }
                if (field.equalsIgnoreCase("rate")) {
                    rate = value;
                }
                if (field.equalsIgnoreCase("unit")) {
                    unit = value;
                }
                if (field.equalsIgnoreCase("stock")) {
                    stock = value;
                }
                if (field.equalsIgnoreCase("rackno")) {
                    rackno = value;
                }

                f_item = (FileItem) itr.next();
            }

            //else part does the image upload
            file_name = f_item.getName();

            //setting path to store image
            File proj_path = new File(config.getServletContext().getRealPath("/"));
            String file_path = proj_path.getParentFile().getParentFile().getPath() + "\\web\\product_images\\";

            //creating a file object
            File savedFile = new File(file_path + file_name);

            try {
                //writing the file object
                f_item.write(savedFile);
            } catch (Exception ex) {
                out.println(ex);
            }

        }

    }
    if (!file_name.equals("")) {
        File f = new File(connection.utility.Path + file_name);
        BufferedImage image = ImageIO.read(f);

        encodedfile = connection.utility.encodeToString(image, "jpeg");

        String str = "INSERT INTO `products`(`name`,`category`,`rate`,`unit`,`rackno`,`webimage`,`appimage`)  VALUES('" + item + "','" + category + "','" + rate + "','" + unit + "','" + rackno + "','" + file_name + "','" + encodedfile + "')";
        String str2 = "INSERT INTO `stock`(`pid`,`date`,`stock`) VALUES((SELECT MAX(`pid`) FROM `products`),'" + date + "','" + stock + "')";

        if (conn.putData(str) > 0 && conn.putData(str2) > 0) {

            String qry = "SELECT MAX(`pid`) FROM `products`";
            Iterator itr = con.getData(qry).iterator();
            if (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                String qrCodeText = v.get(0).toString().trim();

                String filePath = connection.utility.Path1 + item + ".png";

                int size = 125;

                String fileType = "png";

                File qrFile = new File(filePath);

                connection.utility.createQRImage(qrFile, qrCodeText, size, fileType);

%>
<script>
    alert("Succesfully added");
    window.location = "../NewProduct.jsp";
</script>
<%        } else {
%>
<script>
    alert("Failed ");
    window.location = "../NewProduct.jsp";
</script>
<%
            }
        }
    }

%>
