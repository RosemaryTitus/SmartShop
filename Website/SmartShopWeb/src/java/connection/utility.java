/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import sun.misc.BASE64Encoder;

/**
 *
 * @author admin
 */
public class utility {
    

       public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
 
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
 
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
 
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    public static String Path="E:\\hussain\\Amrita college\\SmartShop\\Website\\SmartShopWeb\\web\\product_images\\";
    public static String Path1="E:\\hussain\\Amrita college\\SmartShop\\Website\\SmartShopWeb\\web\\qr_images\\";
   
    public static void createQRImage(File qrFile, String qrCodeText, int size,
            String fileType) throws WriterException, IOException, com.google.zxing.WriterException {

        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable hintMap = new Hashtable();

        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,
                BarcodeFormat.QR_CODE, size, size, hintMap);

        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();

        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
                BufferedImage.TYPE_INT_RGB);

        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();

        graphics.setColor(Color.WHITE);

        graphics.fillRect(0, 0, matrixWidth, matrixWidth);

        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {

            for (int j = 0; j < matrixWidth; j++) {

                if (byteMatrix.get(i, j)) {

                    graphics.fillRect(i, j, 1, 1);

                }

            }

        }

        ImageIO.write(image, fileType, qrFile);

    }
}