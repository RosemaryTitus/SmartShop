package com.softsolutions.smartshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {

    RatingBar ratingBar;
    Button button;
    EditText editText;
    String HTTP_JSON_URL = utilities.URL + "/SmartShopWeb/android/controller.jsp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ratingBar=(RatingBar) findViewById(R.id.rating_feedback);
        editText=(EditText) findViewById(R.id.edt_feedback);
        button=(Button) findViewById(R.id.btn_feedback);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double rating=ratingBar.getRating();
                String feeback=editText.getText().toString().trim();

                volly_call_send_feedbck(Double.toString(rating),feeback);
            }
        });
    }

    private void volly_call_send_feedbck(final String rating, final String feedback)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, HTTP_JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        if (!response.trim().equals("failed")) {
                            Log.d("Response", response);
                            if(!response.trim().equals("failed"))
                            {
                                Toast.makeText(getApplicationContext(), "Feedback posted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Home.class));
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Something went wrong..", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Home.class));
                            }


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
                params.put("key", "setFeedback");
                params.put("rating", rating);
                params.put("feedback", feedback);
                params.put("uid", utilities.uid);

                return params;
            }
        };
        queue.add(postRequest);
    }
}
