package com.softsolutions.smartshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.softsolutions.smartshop.adapter.ProductAdapter;
import com.softsolutions.smartshop.pojo.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.softsolutions.smartshop.utilities.URL;
import static com.softsolutions.smartshop.utilities.uid;

public class ViewProducts extends AppCompatActivity {

    RecyclerView recyclerView;
    JsonArrayRequest RequestOfJSonArray;
    RequestQueue requestQueue;
    Products p = new Products();
    JSONArray resarray = null;

    //json keys
    String pid = "pid";
    String name = "name";
    String category = "category";
    String rate = "rate";
    String unit = "unit";
    String rackno = "rackno";
    String image = "image";

    //List object
    List<Products> ListOfdataAdapter = new ArrayList<>();

    String HTTP_JSON_URL = utilities.URL + "/SmartShopWeb/android/controller.jsp?key=getProducts";

    //Adapter Object
    ProductAdapter recyclerViewadapter;

    //to get the clicked views value
    ArrayList<String> click_val;
    ArrayList<String> click_val_image;
    ArrayList<String> click_val_price;

    View view;
    int RecyclerViewItemPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        getSupportActionBar().hide();
        click_val = new ArrayList<>();
        click_val_image = new ArrayList<>();
        click_val_price = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        //gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView

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
        RequestOfJSonArray = new JsonArrayRequest(HTTP_JSON_URL,
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
        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            Products obj = new Products();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                click_val.add(json.getString(pid));
                click_val_image.add(json.getString(image));
                click_val_price.add(json.getString(rate));


                obj.setPid(json.getString(pid));
                obj.setName(json.getString(name));
                obj.setCategory(json.getString(category));
                obj.setRate(json.getString(rate));
                obj.setUnit(json.getString(unit));
                obj.setRackno(json.getString(rackno));
                obj.setImage(json.getString(image));

                ListOfdataAdapter.add(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        recyclerViewadapter = new ProductAdapter(ListOfdataAdapter, this);
        recyclerView.setAdapter(recyclerViewadapter);
    }
}
