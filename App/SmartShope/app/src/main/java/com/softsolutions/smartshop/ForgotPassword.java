package com.softsolutions.smartshop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

import static com.softsolutions.smartshop.utilities.EMAIL;
import static com.softsolutions.smartshop.utilities.OTP;
import static com.softsolutions.smartshop.utilities.URL;

public class ForgotPassword extends AppCompatActivity {

    EditText email, otp, pass, conpass;
    Button btn, btn2;
    String message = "";
    CheckBox chechotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getSupportActionBar().hide();

        email = (EditText) findViewById(R.id.edt_forpass_email);
        otp = (EditText) findViewById(R.id.edt_forpass_otp);
        pass = (EditText) findViewById(R.id.edt_forpass_pass);
        conpass = (EditText) findViewById(R.id.edt_forpass_conpass);


        btn = (Button) findViewById(R.id.btn_reset_pass);
        btn2 = (Button) findViewById(R.id.btn_reset_pass_ok);


        chechotp = (CheckBox) findViewById(R.id.chec_forpass_otpcheckbox);

        chechotp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked){
                    pass.setEnabled(true);
                    conpass.setEnabled(true);
                    btn.setEnabled(true);

                }
            }
        });
        otp.setEnabled(false);
        pass.setEnabled(false);
        conpass.setEnabled(false);
        btn.setEnabled(false);


//        otp.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (!otp.getText().toString().trim().equals("")) {
//                    if (otp.getText().toString().trim().length() == 6) {
//                        if (otp.getText().toString().trim().equals(message)) {
//                            pass.setEnabled(true);
//                            conpass.setEnabled(true);
//                            btn.setEnabled(true);
//                        }
//                    }
//                }
//            }
//        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OkHttpClient client = new OkHttpClient();
                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson)).build();
                ApiService service = retrofit.create(ApiService.class);
                final User user = new User();
                user.setEmail(email.getText().toString());
                user.setKey("getPhone");

                Call<User> call = service.getPhone(user.getEmail(), user.getKey());

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {


                        User u = response.body();
                        if (response.isSuccessful()) {
                            String result = u.status;

                            if (result.equals("success"))
                            {
                                message = new String(OTP(6));
                                String phone = u.phone;
                                EMAIL = email.getText().toString().trim();
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(phone, null, "Use "+message+" as your verification code on SmartShop", null, null);
                                otp.setEnabled(true);
                                chechotp.setEnabled(true);
                            } else {
                                Toast.makeText(getApplicationContext(), "No account found", Toast.LENGTH_SHORT).show();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.i("Hello", "" + t);
                        Toast.makeText(ForgotPassword.this, "Throwable" + t, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String PASSWORD = pass.getText().toString().trim();
                final String CONFPASS = conpass.getText().toString().trim();

                if (PASSWORD.equals(CONFPASS)) {
                    OkHttpClient client = new OkHttpClient();
                    Gson gson = new GsonBuilder().setLenient().create();
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).client(client)
                            .addConverterFactory(GsonConverterFactory.create(gson)).build();
                    ApiService service = retrofit.create(ApiService.class);
                    final User user = new User();
                    user.setPassword(PASSWORD);
                    user.setEmail(EMAIL);
                    user.setKey("updatePass");

                    Call<User> call = service.updatePass(user.getKey(), user.getEmail(), user.getPassword());

                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {


                            User u = response.body();
                            if (response.isSuccessful()) {
                                String result = u.status;
                                if (result.equals("success")) {
                                    Toast.makeText(getApplicationContext(), "Updation successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Something went Wrong..!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.i("Hello", "" + t);
                            Toast.makeText(ForgotPassword.this, "Throwable" + t, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

    }


}
