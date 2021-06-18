package com.fauzi.sidigiapps.api;


import com.fauzi.sidigiapps.Model.Pengguna;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("register.php")
    Call<Pengguna> Register(
            @Field("key") String key,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("email") String email,
            @Field("telepon") String telepon,
            @Field("pekerjaan") String pekerjaan,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<Pengguna> Login(
            @Field("email") String email,
            @Field("password") String password);


    @FormUrlEncoded
    @POST("get_user.php")
    Call<List<Pengguna>> getUser(
            @Field("user_id") String user_id);


}


