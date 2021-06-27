package com.fauzi.sidigiapps.api;


import com.fauzi.sidigiapps.Model.DataLayanan;
import com.fauzi.sidigiapps.Model.Pengguna;
import com.fauzi.sidigiapps.Model.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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

//    @FormUrlEncoded
//    @GET("get_layanan.php")
//    Call<List<DataLayanan>> getLayanan(
//            @Field("id_layanan") String id_layanan);

    @FormUrlEncoded
    @POST("get_layanan.php")
    Call<List<DataLayanan>> getLayanan();

    @FormUrlEncoded
    @POST("get_cell.php")
    Call<List<DataLayanan>> getCellular(
            @Field("is_approved") String id);


    @GET
    ("get_layanan.php")
    Call<ResponseModel> getData();
}

