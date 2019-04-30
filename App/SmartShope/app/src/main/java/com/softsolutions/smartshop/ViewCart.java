package com.softsolutions.smartshop;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softsolutions.smartshop.adapter.CartAdapter;
import com.softsolutions.smartshop.pojo.Cart;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewCart extends AppCompatActivity {

    double totalcash = 0.0;
    RecyclerView recyclerView;
    JsonArrayRequest RequestOfJSonArray;
    RequestQueue requestQueue;
    Cart p = new Cart();
    JSONArray resarray = null;
    TextView txtamount;
    Button btn;
    //json keys
    String pid = "pid";
    String cartid = "cartid";
    String number = "number";
    String amount = "amount";
    String date = "date";
    String product = "product";
    String category = "category";
    String image = "image";

    List<Cart> ListOfdataAdapter = new ArrayList<>();

    String HTTP_JSON_URL = utilities.URL + "/SmartShopWeb/android/controller.jsp";//?key=getProducts

    //Adapter Object
    CartAdapter recyclerViewadapter;

    //to get the clicked views value
    ArrayList<String> click_val;
    ArrayList<String> click_val_image;
    ArrayList<String> click_val_price;

    View view;
    int RecyclerViewItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        txtamount = (TextView) findViewById(R.id.txt_cart_amount);
        btn = (Button) findViewById(R.id.btn_make_payment_cart);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Payment.class));
            }
        });
        getSupportActionBar().hide();
        click_val = new ArrayList<>();
        click_val_image = new ArrayList<>();
        click_val_price = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_view_cart);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager); // set LayoutManager to RecyclerView

        recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                view = rv.findChildViewUnder(e.getX(), e.getY());

                if (view != null && gestureDetector.onTouchEvent(e)) {

                    RecyclerViewItemPosition = rv.getChildAdapterPosition(view);

                    // Showing RecyclerView Clicked Item value using Toast.
                    //Toast.makeText(getApplicationContext(), click_val.get(RecyclerViewItemPosition), Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewCart.this);
                    alertDialogBuilder.setMessage("Do you want to delete Item from cart");
                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                    new DeleteCart().execute("deletecart", click_val.get(RecyclerViewItemPosition));

                                }
                            });

                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        JSON_HTTP_CALL();

    }

    public void JSON_HTTP_CALL() {
     /*   RequestOfJSonArray = new JsonArrayRequest(HTTP_JSON_URL,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ParseJSonResponse(response);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(RequestOfJSonArray);*/
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, HTTP_JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        if (!response.trim().equals("failed")) {
                            Log.d("Response", response);
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                ParseJSonResponse(jsonArray);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Your Cart is Empty", Toast.LENGTH_SHORT).show();
                            recyclerView.setVisibility(View.INVISIBLE);
                            findViewById(R.id.amount_section_layout).setVisibility(View.INVISIBLE);
                            findViewById(R.id.cart_empty_image).setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "getCart");
                params.put("uid", utilities.uid);

                return params;
            }
        };
        //requestQueue = Volley.newRequestQueue(getApplicationContext());
        queue.add(postRequest);
    }

    public void ParseJSonResponse(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            Cart obj = new Cart();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                click_val.add(json.getString(cartid));
                /*click_val_image.add(json.getString(image));
                click_val_price.add(json.getString(rate));*/


                obj.setPid(json.getString(pid));
                obj.setCategory(json.getString(category));
                obj.setProduct(json.getString(product));
                obj.setAmount(json.getString(amount));
                obj.setDate(json.getString(date));
                obj.setNumber(json.getString(number));
                obj.setCartid(json.getString(cartid));
                obj.setImage(json.getString(image));

                totalcash = totalcash + Double.parseDouble(json.getString(amount));
                ListOfdataAdapter.add(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        recyclerViewadapter = new CartAdapter(ListOfdataAdapter, this);
        recyclerView.setAdapter(recyclerViewadapter);

        utilities.cart_amount = totalcash + "";
        txtamount.setText(totalcash + "");
    }

    public class DeleteCart extends AsyncTask<String, String, String> {


        ProgressDialog pd;

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(ViewCart.this);
            pd.setCancelable(false);
            pd.setMessage("Deleting Item from Cart");
            pd.setTitle("Please wait");
            pd.show();

        }

        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str = "";
            //Log.d("inside inback leejo","inside inback");
            List<NameValuePair> pdat = new ArrayList<NameValuePair>(6);

            pdat.add(new BasicNameValuePair("key", params[0]));
            pdat.add(new BasicNameValuePair("cartid", params[1]));

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
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pd.dismiss();
            if (!result.trim().equals("failed")) {

                finish();
                startActivity(new Intent(getApplicationContext(), ViewCart.class));
            } else {

            }

        }

    }
}
