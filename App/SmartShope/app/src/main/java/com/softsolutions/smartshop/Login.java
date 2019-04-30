package com.softsolutions.smartshop;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
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
import static com.softsolutions.smartshop.utilities.uid;

public class Login extends AppCompatActivity {

    EditText user_email, user_pass;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();


        user_email = (EditText) findViewById(R.id.edt_login_email);
        user_pass = (EditText) findViewById(R.id.edt_login_password);


        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.SEND_SMS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,


        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


        findViewById(R.id.link_forget_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
            }
        });
        findViewById(R.id.txt_register_here).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OkHttpClient client = new OkHttpClient();
                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson)).build();
                ApiService service = retrofit.create(ApiService.class);
                final User user = new User();
                user.setEmail(user_email.getText().toString());
                user.setPassword(user_pass.getText().toString());
                user.setKey("login");

                Call<User> call = service.loginData(user.getEmail(), user.getPassword(), user.getKey());
                //Call<JSONObject> call = service.loginData(user.getEmail(), user.getPassword(), user.getKey());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User u = response.body();
                        //JSONObject obj=response.body();
                        Log.i("response", "" + response);
                        if (response.isSuccessful()) {

                            String result = u.status;
                            String id = "";
                            if (!result.trim().equals("failed")) {
                                id = u.id;

                            }
                            if (!result.equals("failed")) {
                                uid = id;
                                Toast.makeText(Login.this, "Login Success", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), Home.class));
                            } else {
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                        user_email.setText("");
                        user_pass.setText("");
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Log.i("Hello", "" + t);
                        Toast.makeText(Login.this, "Throwable" + t, Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}

