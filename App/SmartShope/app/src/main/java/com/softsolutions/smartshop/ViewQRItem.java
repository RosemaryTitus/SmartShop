package com.softsolutions.smartshop;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ViewQRItem extends AppCompatActivity {
    String pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_item);

        try
        {
             pid = getIntent().getStringExtra("pid");
            Log.d("Valueeee",pid);
             new Itemview().execute("get_QR_item",pid);

        }catch (Exception e)
        {
            Log.d("Exception",e+"");
        }

    }

    public class Itemview extends AsyncTask<String, String, String>
    {
        ProgressDialog pd;
        protected void onPreExecute()
        {
            super.onPreExecute();
            pd=new ProgressDialog(ViewQRItem.this);
            pd.setCancelable(false);
            pd.setMessage("Getting..");
            pd.setTitle("Please wait");
            pd.show();
        }

        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str="";
            List<NameValuePair> pdat=new ArrayList<NameValuePair>(6);
            pdat.add(new BasicNameValuePair("key",params[0]));
            pdat.add(new BasicNameValuePair("value",params[1]));
            HttpClient client=new DefaultHttpClient();
            HttpPost mypdat=new HttpPost(utilities.URL + "/SmartShopWeb/android/controller.jsp");
            Log.d("iinback","inside inback");
            try
            {
                mypdat.setEntity(new UrlEncodedFormEntity(pdat));
                Log.d("post data","post data");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            try
            {
                HttpResponse re=client.execute(mypdat);
                HttpEntity entity=re.getEntity();
                str= EntityUtils.toString(entity);
                int status=re.getStatusLine().getStatusCode();
                if(status==200)
                { return str;
                }}
            catch (ClientProtocolException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }return null;
        }
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            Log.d("@@@@",result);
            pd.dismiss();

            if(!result.trim().equals("failed"))
            {
                String data[]=result.trim().split(":");
                String Pid=data[0].trim();
                String Rate=data[3].trim();
                String image=data[6].trim();

                showCustomDialog(image,Rate,Pid);
                Toast.makeText(ViewQRItem.this, Pid, Toast.LENGTH_SHORT).show();

            }
            else
            {

            }

        }
    }


    protected void showCustomDialog(String img, final String pri,final String Pid) {
        // TODO Auto-generated method stub
        final Dialog dialog = new Dialog(ViewQRItem.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.customdialog);

        final String pid=Pid;
        final ImageView image = (ImageView) dialog.findViewById(R.id.img_cu_dilg_item);
        final Button action_addcart = (Button) dialog.findViewById(R.id.btn_action_addcart);
        final Button action_buy = (Button) dialog.findViewById(R.id.btn_action_buy);
        final Button action_minus = (Button) dialog.findViewById(R.id.btn_action_minus);
        final Button action_plus = (Button) dialog.findViewById(R.id.btn_action_plus);
        final EditText quant = (EditText) dialog.findViewById(R.id.edt_cus_dlg_quantity);
        final EditText edtprice = (EditText) dialog.findViewById(R.id.edt_cus_dlg_price);

        quant.setText("1");
        edtprice.setText(pri);

        image.getLayoutParams().height = 400;

        image.getLayoutParams().width = 400;

        image.setScaleType(ImageView.ScaleType.FIT_XY);

        byte[] decodedString = android.util.Base64.decode(img, android.util.Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Bitmap decodedByte1 = RoundedImageView.getCroppedBitmap(decodedByte, 520);

        image.setImageBitmap(decodedByte);


        action_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new async_addtocart().execute("addtocart", utilities.uid, pid, quant.getText().toString(), edtprice.getText().toString());


            }
        });

        action_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quant.setText(Integer.toString(Integer.parseInt(quant.getText().toString()) + 1));
                edtprice.setText(Double.toString(Double.parseDouble(quant.getText().toString()) * Double.parseDouble(pri)));

            }
        });
        action_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((Integer.parseInt(quant.getText().toString())) > 1) {
                    quant.setText(Integer.toString(Integer.parseInt(quant.getText().toString()) - 1));
                    edtprice.setText(Double.toString(Double.parseDouble(quant.getText().toString()) * Double.parseDouble(pri)));
                }
            }
        });

        action_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                utilities.item_quantity=quant.getText().toString().trim();
                utilities.amount=edtprice.getText().toString().trim();
                utilities.pro_id=pid;
                //startActivity(new Intent(getApplicationContext(), DateAndTimeChooser.class));
            }
        });
        dialog.show();
    }


    public class async_addtocart extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str = "";
            List<NameValuePair> pdat = new ArrayList<NameValuePair>(6);
            pdat.add(new BasicNameValuePair("key", params[0]));
            pdat.add(new BasicNameValuePair("uid", params[1]));
            pdat.add(new BasicNameValuePair("pid", params[2]));
            pdat.add(new BasicNameValuePair("num", params[3]));
            pdat.add(new BasicNameValuePair("amount", params[4]));

            HttpClient client = new DefaultHttpClient();
            HttpPost mypdat = new HttpPost(utilities.URL+ "/SmartShopWeb/android/controller.jsp");
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
                Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(getApplicationContext(), Home.class));
            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong..!!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
