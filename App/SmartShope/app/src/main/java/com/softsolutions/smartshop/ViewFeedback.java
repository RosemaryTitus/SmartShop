package com.softsolutions.smartshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softsolutions.smartshop.adapter.CartAdapter;
import com.softsolutions.smartshop.adapter.FeedbackAdapter;
import com.softsolutions.smartshop.pojo.Cart;
import com.softsolutions.smartshop.pojo.Feedback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewFeedback extends AppCompatActivity {
    RecyclerView recyclerView;
    JsonArrayRequest RequestOfJSonArray;
    RequestQueue requestQueue;
    Feedback p = new Feedback();
    JSONArray resarray = null;
    TextView txtamount;
    Button btn;
    //json keys
    String uid = "uid";
    String fid = "fid";
    String name = "name";
    String email = "email";
    String phone = "phone";
    String feedback = "feedback";
    String date = "date";
    String rating = "rating";

    List<Feedback> ListOfdataAdapter = new ArrayList<>();
    String HTTP_JSON_URL = utilities.URL + "/SmartShopWeb/android/controller.jsp";//?key=getProducts

    FeedbackAdapter recyclerViewadapter;

    ArrayList<String> click_val;
    ArrayList<String> click_val_image;
    ArrayList<String> click_val_price;

    View view;
    int RecyclerViewItemPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        getSupportActionBar().hide();
        click_val = new ArrayList<>();
        click_val_image = new ArrayList<>();
        click_val_price = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_view_feedback);
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
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, HTTP_JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            ParseJSonResponse(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "view_feedback");

                return params;
            }
        };
        queue.add(postRequest);
    }

    public void ParseJSonResponse(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            Feedback obj = new Feedback();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                click_val.add(json.getString(fid));
                obj.setUid(json.getString(uid));
                obj.setFid(json.getString(fid));
                obj.setName(json.getString(name));
                obj.setEmail(json.getString(email));
                obj.setPhone(json.getString(phone));
                obj.setFeedback(json.getString(feedback));
                obj.setDate(json.getString(date));
                obj.setRating(json.getString(rating));

                ListOfdataAdapter.add(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        recyclerViewadapter = new FeedbackAdapter(ListOfdataAdapter, this);
        recyclerView.setAdapter(recyclerViewadapter);

    }
}
