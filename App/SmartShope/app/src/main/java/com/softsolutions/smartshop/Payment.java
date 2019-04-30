package com.softsolutions.smartshop;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Payment extends AppCompatActivity {
    ImageView img;
    EditText card, name, cvv, pin, balance;
    String cartno, cname, ccc, cpin, bal;
    Button pay, amt;
    String HTTP_JSON_URL = utilities.URL + "/SmartShopWeb/android/controller.jsp";//?key=getProducts
    String myuid;
     String CARD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        myuid = utilities.uid;
        Toast.makeText(getApplicationContext(), "uid is " + myuid, Toast.LENGTH_SHORT).show();

        img = (ImageView) findViewById(R.id.pay_img_logo_cart);
        card = (EditText) findViewById(R.id.pay_edt_cardno_cart);
        name = (EditText) findViewById(R.id.pay_edt_name_cart);
        cvv = (EditText) findViewById(R.id.pay_edt_cvv_cart);
        pin = (EditText) findViewById(R.id.pay_edt_pin_cart);
        balance = (EditText) findViewById(R.id.pay_edt_balance);
        pay = (Button) findViewById(R.id.btn_payment_cart);

        amt = (Button) findViewById(R.id.btn_amount_cart);

//        .......





//        .......


        Bitmap bm = ((BitmapDrawable) img.getDrawable()).getBitmap();
        Bitmap bm1 = RoundedImageView.getCroppedBitmap(bm, 520);
        img.setImageBitmap(bm1);

        amt.setText("Amount to be paid  : " + utilities.cart_amount);
        amt.setClickable(false);
        balance.setText(utilities.cart_amount);
        balance.setFocusableInTouchMode(false);

        new async_getBankDetails().execute();
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CARD = card.getText().toString().trim();
                final String CVV = cvv.getText().toString().trim();
                final String PIN = pin.getText().toString().trim();

                if (!CARD.equals(cartno)) {
                    card.requestFocus();
                    card.setError("Cardno not match");
                } else if (!CVV.equals(ccc)) {
                    cvv.requestFocus();
                    cvv.setError("CVV not match");
                } else if (!PIN.equals(cpin)) {
                    pin.requestFocus();
                    pin.setError("Pin not match");
                } else if (Double.parseDouble(utilities.cart_amount) > Double.parseDouble(bal)) {
                    Toast.makeText(Payment.this, "insufficient balance in your bank account", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Home.class));
                } else {
                    new async_payment().execute("payment_cart", CARD, CVV, PIN, utilities.uid, utilities.cart_amount);
                }


            }
        });


    }

    public class async_payment extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str = "";
            List<NameValuePair> pdat = new ArrayList<NameValuePair>(6);

            pdat.add(new BasicNameValuePair("key", params[0]));
            pdat.add(new BasicNameValuePair("card", params[1]));
            pdat.add(new BasicNameValuePair("cvv", params[2]));
            pdat.add(new BasicNameValuePair("pin", params[3]));
            pdat.add(new BasicNameValuePair("uid", params[4]));
            pdat.add(new BasicNameValuePair("amount", params[5]));


            HttpClient client = new DefaultHttpClient();
            HttpPost mypdat = new HttpPost(HTTP_JSON_URL);
            try {
                mypdat.setEntity(new UrlEncodedFormEntity(pdat));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                HttpResponse re = client.execute(mypdat);
                HttpEntity entity = re.getEntity();
                str = EntityUtils.toString(entity);
                int status = re.getStatusLine().getStatusCode();
                if (status == 200) {
                    return str;
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (!result.trim().equals("failed")) {

                if (result.trim().equals("No account details found")) {

                } else if (result.trim().equals("No Sufficient balance")) {

                } else {

                    Toast.makeText(Payment.this, "booking success", Toast.LENGTH_SHORT).show();
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                    builder.setSmallIcon(android.R.drawable.ic_dialog_alert);

                    long[] v = {500, 1000};
                    builder.setVibrate(v);

                    Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    builder.setSound(uri);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.journaldev.com/"));
                   /* getIntent().setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(Payment.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);
*/

                    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                    builder.setContentTitle("Payment Successful");
                    builder.setContentText("Your payment of amount " + utilities.cart_amount + " successful");
                    //builder.setSubText("Tap to view the website.");
                    builder.setPriority(Notification.PRIORITY_MAX);

                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(1, builder.build());

                 //   showCustomDialog();

                    Intent i = new Intent(getApplicationContext(), Receipt.class);
                    i.putExtra("ac",CARD);
                    i.putExtra("price",utilities.cart_amount);
                startActivity(i);



                }

            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong..!!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public class async_getBankDetails extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str = "";
            List<NameValuePair> pdat = new ArrayList<NameValuePair>(6);

            pdat.add(new BasicNameValuePair("key", "getBankDetails"));
            pdat.add(new BasicNameValuePair("uid", utilities.uid));


            HttpClient client = new DefaultHttpClient();
            HttpPost mypdat = new HttpPost(HTTP_JSON_URL);
            try {
                mypdat.setEntity(new UrlEncodedFormEntity(pdat));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                HttpResponse re = client.execute(mypdat);
                HttpEntity entity = re.getEntity();
                str = EntityUtils.toString(entity);
                int status = re.getStatusLine().getStatusCode();
                if (status == 200) {
                    return str;
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("result##", result);
            if (!result.trim().equals("failed")) {
                Log.d("result##", result);
                String ar[] = result.trim().split("#");
                cartno = ar[0];
                cname = ar[1];
                ccc = ar[2];
                cpin = ar[3];
                bal = ar[4];

            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong..!!", Toast.LENGTH_LONG).show();
            }
        }
    }

//    protected void showCustomDialog() {
//        // TODO Auto-generated method stub
//        final Dialog dialog = new Dialog(Payment.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.customdialog_receipt);
//
//
//
//        final Button ok = (Button) dialog.findViewById(R.id.img_cu_dilg_receipt_btnok);
//        final TextView ac = (TextView) dialog.findViewById(R.id.img_cu_dilg_receipt_ac);
//        final TextView price = (TextView) dialog.findViewById(R.id.img_cu_dilg_receipt_price);
//
//        ac.setText(cartno);
//        price.setText(utilities.cart_amount);
//
//
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                takeScreenshot();
//
////                Intent i = new Intent(getApplicationContext(), Home.class);
////                startActivity(i);
////                finish();
//            }
//        });
//        dialog.show();
//    }





}
