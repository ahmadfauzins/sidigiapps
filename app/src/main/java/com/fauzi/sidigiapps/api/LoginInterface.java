package com.fauzi.sidigiapps.api;


import com.fauzi.sidigiapps.Model.User;
import com.fauzi.sidigiapps.api.config.Config;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {

//    @FormUrlEncoded
//    @POST(Config.API_LOGIN)
//    Call<User> login(
//            @Field("email") EditText email,
//            @Field("password") EditText password);

    @FormUrlEncoded
    @POST(Config.API_LOGIN)
    Call<User> login(
            @Field("email") String email,
            @Field("password") String password);

}
