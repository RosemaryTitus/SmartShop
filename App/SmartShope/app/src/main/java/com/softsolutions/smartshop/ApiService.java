package com.softsolutions.smartshop;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("/SmartShopWeb/android/controller.jsp")
    Call<User> insertData(@Field("key") String key,@Field("name") String name, @Field("email") String email , @Field("password") String password, @Field("phone") String phone ,@Field("address") String address);


    @FormUrlEncoded
    @POST("/SmartShopWeb/android/controller.jsp")
    Call<User> loginData(@Field("email") String email , @Field("password") String password, @Field("key") String key);

    @FormUrlEncoded
    @POST("/SmartShopWeb/android/controller.jsp")
    Call<User> getPhone(@Field("email") String email ,  @Field("key") String key);

    @FormUrlEncoded
    @POST("/SmartShopWeb/android/controller.jsp")
    Call<User> updatePass(@Field("key") String key ,  @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/SmartShopWeb/android/controller.jsp")
    Call<String> checkemail(@Field("key") String key ,  @Field("email") String email);


    @FormUrlEncoded
    @POST("/SmartShopWeb/android/controller.jsp")
    Call<JSONObject> getProducts(@Field("key") String key);


}
