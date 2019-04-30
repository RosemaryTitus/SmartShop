package com.softsolutions.smartshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.softsolutions.smartshop.utilities.URL;

public class Register extends AppCompatActivity {

    EditText edt_email, edt_phone, edt_name, edt_pass, edt_address;
    Button btn_register;

    String vrfystatus = "y";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();
        edt_email = (EditText) findViewById(R.id.edt_register_email);
        edt_phone = (EditText) findViewById(R.id.edt_register_phone);
        edt_name = (EditText) findViewById(R.id.edt_register_name);
        edt_pass = (EditText) findViewById(R.id.edt_register_password);
        edt_address = (EditText) findViewById(R.id.edt_register_address);


        btn_register = (Button) findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ((edt_name.getText().toString().isEmpty())) {

                    edt_name.setError("name");
                } else if ((edt_phone.getText().toString().isEmpty())) {

                    edt_phone.setError("phone");
                } else if ((edt_email.getText().toString().isEmpty())) {

                    edt_email.setError("email");
                } else if ((edt_address.getText().toString().isEmpty())) {

                    edt_address.setError("address");
                } else if ((edt_pass.getText().toString().isEmpty())) {

                    edt_pass.setError("pass");
                }
                else if(edt_pass.length()<8)
                {
                    edt_pass.setError("password must have 8 characters");
                }
                    else if (vrfystatus.equals("y")) {

                    OkHttpClient client = new OkHttpClient();
                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .create();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(URL)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

                    ApiService service = retrofit.create(ApiService.class);


                    Call<String> call0 = service.checkemail("checkemailid", (edt_email.getText().toString()));
                    call0.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            if (response.isSuccessful()) {

                                if (response.body().equals("success")) {
                                    edt_email.requestFocus();
                                    edt_email.setError("already exist");

                                } else {
                                    vrfystatus = "no";
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                } else {


                    OkHttpClient client = new OkHttpClient();
                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .create();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(URL)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

                    ApiService service = retrofit.create(ApiService.class);
                    final User user = new User();
                    user.setName(edt_name.getText().toString());
                    user.setPhone(edt_phone.getText().toString());
                    user.setEmail(edt_email.getText().toString());
                    user.setPassword(edt_pass.getText().toString());
                    user.setAddress(edt_address.getText().toString());
                    user.setKey("register");

                    Call<User> call = service.insertData(user.getKey(), user.getName(), user.getEmail(), user.getPassword(), user.getPhone(), user.getAddress());

                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {


                            User u = response.body();
                            if (response.isSuccessful()) {
                                String result = u.status;

                                if (result.equals("success")) {
                                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                    edt_name.setText("");
                                    edt_email.setText("");
                                    edt_pass.setText("");
                                    edt_phone.setText("");
                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                } else {
                                    Toast.makeText(Register.this, "failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.i("Hello", "" + t);
                            Toast.makeText(Register.this, "Throwable" + t, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}
