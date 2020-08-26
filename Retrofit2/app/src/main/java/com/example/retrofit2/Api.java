package com.example.retrofit2;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("jsonResponse")
    Call<ResponseBody> createUser(
            @Field("name") String name,
            @Field("sex") String sex

    );
}
