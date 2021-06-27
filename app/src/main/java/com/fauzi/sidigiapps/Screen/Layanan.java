package com.fauzi.sidigiapps.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fauzi.sidigiapps.Adapter.BuahAdapter;
import com.fauzi.sidigiapps.Adapter.BuahDetail;
import com.fauzi.sidigiapps.Adapter.LayananAdapter;
import com.fauzi.sidigiapps.MainActivity;
import com.fauzi.sidigiapps.Model.Buah;
import com.fauzi.sidigiapps.Model.BuahContent;
import com.fauzi.sidigiapps.Model.DataLayanan;
import com.fauzi.sidigiapps.Model.Pengguna;
import com.fauzi.sidigiapps.Model.ResponseModel;
import com.fauzi.sidigiapps.Model.User;
import com.fauzi.sidigiapps.R;
import com.fauzi.sidigiapps.api.ApiClient;
import com.fauzi.sidigiapps.api.ApiInterface;
import com.fauzi.sidigiapps.utility.PrefUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class Layanan extends AppCompatActivity {

    private RecyclerView rvDataLayanan;
    private RecyclerView.Adapter rvAdata;
    private RecyclerView.LayoutManager lvManager;
    private List<DataLayanan> listData = new ArrayList<>();
    SwipeRefreshLayout swipe;
    ProgressBar pb;
    String usernama,alamat,email,pekerjan,idd;
    LayananAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan);

        swipe = findViewById(R.id.swp);
        pb = findViewById(R.id.pbar);


        ImageView kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Layanan.this, MainActivity.class);
                startActivity(intent);
                customType(Layanan.this, "fadein-to-fadeout");
            }
        });

        rvDataLayanan = findViewById(R.id.rv_data);
        lvManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDataLayanan.setLayoutManager(lvManager);


        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                ambilData();
                swipe.setRefreshing(false);
            }
        });
        listener = new LayananAdapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {

                Intent intent = new Intent(Layanan.this, DetailLayanan.class);
                intent.putExtra("id", listData.get(position).getId());
                intent.putExtra("nama", listData.get(position).getNama());
                intent.putExtra("deskripsi", listData.get(position).getDeskripsi());
                intent.putExtra("pic", listData.get(position).getPic());
                intent.putExtra("image", listData.get(position).getImage());
                startActivity(intent);
            }

            @Override
            public void onLoveClick(View view, int position) {

            }
        };

    }
        public void ambilData(){
        pb.setVisibility(View.VISIBLE);
        ApiInterface apiData = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseModel> ambildata = apiData.getData();

        ambildata.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
//                Toast.makeText(Layanan.this,"Berhasil Ambil Data",Toast.LENGTH_SHORT).show();

                listData = response.body().getData();

                rvAdata  = new LayananAdapter(Layanan.this,listData,listener);
                rvDataLayanan.setAdapter(rvAdata);
                rvAdata.notifyDataSetChanged();
                pb.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
            Toast.makeText(Layanan.this,"Koneksi Gagal !",Toast.LENGTH_SHORT).show();
            }
        });


        }

    @Override
    protected void onResume() {
        super.onResume();
        ambilData();
    }

}


