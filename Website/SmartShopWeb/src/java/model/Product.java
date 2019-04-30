/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author TeamJava
 */
public class Product {
    
    String pid,stock,date;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

   
    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDate() {
     Date d=new Date();
    SimpleDateFormat ff=new SimpleDateFormat("YYYY-MM-dd");
    date=ff.format(d);
    return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
   
    
}
