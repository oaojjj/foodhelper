package com.example.stdmanagement;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetroApi {
    @FormUrlEncoded
    @POST("Register.php")
    Call<ResponseBody> register(@Field("stdID") String id, @Field("stdPassword") String pwd, @Field("stdPhone") String phone);

    @GET("CheckID.php")
    Call<ResponseBody> CheckID(@Query("stdID") String id);

    @FormUrlEncoded
    @POST("Login.php")
    Call<ResponseBody> Login(@Field("stdID") String id,@Field("stdPassword") String pwd);
}

