package com.example.stdmanagement.Retrofit;

import org.w3c.dom.Comment;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetroApi {
    @FormUrlEncoded
    @POST("Register.php")
    Call<ResponseBody> register(@Field("stdID") String id, @Field("stdPassword") String pwd, @Field("stdPhone") String phone);

    @GET("CheckID.php")
    Call<ResponseBody> CheckID(@Query("stdID") String id);

    @FormUrlEncoded
    @POST("Login.php")
    Call<ResponseBody> Login(@Field("stdID") String id,@Field("stdPassword") String pwd);

    @GET("FoodData.php")
    Call<ResponseBody> FoodData (@QueryMap Map<String,Object> jsonData);

    @GET("FoodRating.php")
    Call<ResponseBody> FoodRating (@Query("id") String foodID,@Query("stdID") String id,@Query("rating") String rating);

    @GET("UserRatingInfo.php")
    Call<ResponseBody> RatingInfo(@Query("id") String FoodID,@Query("stdID") String id);

    @GET("Comment.php")
    Call<ResponseBody> Comment(@Query("id") String id);

    @GET("InsertComment.php")
    Call<ResponseBody> InsertComment(@Query("id") String foodID,@Query("stdID") String id,@Query("comment") String comment);
}

