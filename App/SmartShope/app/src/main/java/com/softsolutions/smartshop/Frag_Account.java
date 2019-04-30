package com.softsolutions.smartshop;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softsolutions.smartshop.R;
import com.softsolutions.smartshop.utilities;

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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Frag_Account extends Fragment {

    EditText edt_cardno, edt_cardcvv, edt_cardpin, edt_cardbal;
    Button accbtn;
    String HTTP_JSON_URL = utilities.URL + "/SmartShopWeb/android/controller.jsp";//?key=getProducts
    String cartno, cname, ccc, cpin, bal;
    String Scardnum, Scvv, Spin, Sbal;

    public Frag_Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag__account, container, false);

        edt_cardno = (EditText) view.findViewById(R.id.edt_register_cardno);
        edt_cardcvv = (EditText) view.findViewById(R.id.edt_register_cvv);
        edt_cardpin = (EditText) view.findViewById(R.id.edt_register_pin);
        edt_cardbal = (EditText) view.findViewById(R.id.edt_register_balance);


        new async_getBankDetails().execute();


        accbtn = (Button) view.findViewById(R.id.addaccbtn);

        Toast.makeText(getContext(), "uid is " + utilities.uid, Toast.LENGTH_SHORT).show();


        accbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Scardnum = edt_cardno.getText().toString().trim();
                Scvv = edt_cardcvv.getText().toString().trim();
                Spin = edt_cardpin.getText().toString().trim();
                Sbal = edt_cardbal.getText().toString().trim();

                if ((edt_cardno.getText().toString().isEmpty())) {

                    edt_cardno.setError("Number");
                } else if ((edt_cardcvv.getText().toString().isEmpty())) {

                    edt_cardcvv.setError("CVV Please");
                } else if ((edt_cardpin.getText().toString().isEmpty())) {

                    edt_cardpin.setError("PIN required");
                } else if ((edt_cardbal.getText().toString().isEmpty())) {

                    edt_cardbal.setError("Enter Balance");
                } else {

                    //Toast.makeText(getContext(), "ividethi", Toast.LENGTH_SHORT).show();

                    volley_call_addACcount();
                }


            }
        });


        return view;
    }

    // Kemosabe Get location from DB

    public void volley_call_addACcount() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, HTTP_JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);

                // Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();

                try {
                    if (!response.trim().equals("failed")) {

                        String data = response;

                        Toast.makeText(getContext(), "ok", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
//                SharedPreferences sp=getSharedPreferences("booking_info", Context.MODE_PRIVATE);
                map.put("key", "add_account");
                map.put("cardnum", Scardnum);
                map.put("cvv", Scvv);
                map.put("pin", Spin);
                map.put("balance", Sbal);
                map.put("uid", utilities.uid);


                return map;
            }
        };
        queue.add(request);
    }

    //................................fetch bank details .....................................kemosabe................


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


                edt_cardno.setText(cartno);
                edt_cardpin.setText(cpin);
                edt_cardcvv.setText(ccc);
                edt_cardbal.setText(bal);

            } else {
                Toast.makeText(getContext(), "Something went wrong..!!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
