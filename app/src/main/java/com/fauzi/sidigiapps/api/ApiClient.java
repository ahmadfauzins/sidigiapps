package com.fauzi.sidigiapps.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //    private static final String BASE_URL = "http://fauproject.000webhostapp.com/AplikasiMahasiswa/android/";
//    private static final String BASE_URL = "http://192.168.38.27/api_sisfotel/";
    private static final String BASE_URL = "http://nadhirastore.com/api_sidigiapps/";
//    private static final String BASE_URL = "http://192.168.137.1/AplikasiMahasiswa/android/";

    private static Retrofit retrofit;

    public static Retrofit getApiClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
