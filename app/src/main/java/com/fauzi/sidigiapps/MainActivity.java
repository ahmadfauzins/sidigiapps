package com.fauzi.sidigiapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.fauzi.sidigiapps.Adapter.BuahAdapter;
import com.fauzi.sidigiapps.Adapter.BuahDetail;
import com.fauzi.sidigiapps.Adapter.LayananAdapter;
import com.fauzi.sidigiapps.Model.Buah;
import com.fauzi.sidigiapps.Model.BuahContent;
import com.fauzi.sidigiapps.Model.DataLayanan;
import com.fauzi.sidigiapps.Model.ResponseModel;
import com.fauzi.sidigiapps.Model.User;
import com.fauzi.sidigiapps.Screen.DetailLayanan;
import com.fauzi.sidigiapps.Screen.Goal;
import com.fauzi.sidigiapps.Screen.Keunggulan;
import com.fauzi.sidigiapps.Screen.Layanan;
import com.fauzi.sidigiapps.Screen.MapsActivity;
import com.fauzi.sidigiapps.Screen.SlideActivity;
import com.fauzi.sidigiapps.Screen.Tentang;
import com.fauzi.sidigiapps.api.ApiClient;
import com.fauzi.sidigiapps.api.ApiInterface;
import com.fauzi.sidigiapps.utility.PrefUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class MainActivity extends AppCompatActivity {

//    private RecyclerView rvPlace;
//    private ArrayList<Buah> list = new ArrayList<>();
    ImageSlider imageSlider;
    TextView namauser;
    String usernama,alamat,email,pekerjan,idd;
    private RecyclerView rvDataLayanan;
    private RecyclerView.Adapter rvAdata;
    private RecyclerView.LayoutManager lvManager;
    private List<DataLayanan> listData = new ArrayList<>();
    private ApiInterface apiInterface ;
    LayananAdapter.RecyclerViewClickListener listener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
        namauser =  findViewById(R.id.namauser);
        namauser.setText("Selamat Datang "+user.getData().getNama());

        imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://sasanadigital.com/wp-content/uploads/2020/10/5-Channel-Digital-Marketing-e1601911140396.jpg","Kenali Bisnis Digital"));
        slideModels.add(new SlideModel("https://i0.wp.com/projasaweb.com/wp-content/uploads/2019/03/strategi-digital-marketing.png","Strategi Digital Marketing"));
        slideModels.add(new SlideModel("https://i0.wp.com/projasaweb.com/wp-content/uploads/2018/09/digital-marketing.png","Apa Sih Digital Marketing? "));
        slideModels.add(new SlideModel("https://i0.wp.com/projasaweb.com/wp-content/uploads/2019/03/cara-bisnis-online-image.png","Cara Online kan Bisnis Mu"));
        imageSlider.setImageList(slideModels,true);

        ImageView imLogout = findViewById(R.id.logout);
        imLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        ImageView iprofil = findViewById(R.id.profil);
        iprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Tentang.class);
                startActivity(intent);
                customType(MainActivity.this,"fadein-to-fadeout");

            }
        });
        ImageView ialamat = findViewById(R.id.tim);
        ialamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                customType(MainActivity.this,"fadein-to-fadeout");

            }
        });
        ImageView iunggul = findViewById(R.id.alamat);
        iunggul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Keunggulan.class);
                startActivity(intent);
                customType(MainActivity.this,"fadein-to-fadeout");
//*left-to-right
//                        *right-to-left
//                        *bottom-to-up
//                        *up-to-bottom
//                        *fadein-to-fadeout
//                        *rotateout-to-rotatein
            }
        });
        ImageView konsul = findViewById(R.id.konsultasi);
        konsul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kirimWA();
                customType(MainActivity.this,"fadein-to-fadeout");
            }
        });
        ImageView layanan = findViewById(R.id.layanan);
        layanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Layanan.class);
                startActivity(intent);customType(MainActivity.this,"fadein-to-fadeout");


            }
        });
        ImageView goal = findViewById(R.id.goal);
        goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Goal.class);
                startActivity(intent);customType(MainActivity.this,"fadein-to-fadeout");

            }
        });

        
//        rvPlace = findViewById(R.id.rv_buah);
//        rvPlace.setHasFixedSize(true);
//        list.addAll(BuahContent.getListData());
//        showRecyclerList();
        rvDataLayanan = findViewById(R.id.rv_data);
        lvManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvDataLayanan.setLayoutManager(lvManager);


        ambilData();
        listener = new LayananAdapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {

                Intent intent = new Intent(MainActivity.this, DetailLayanan.class);
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

    private void kirimWA() {

        User user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
        usernama = user.getData().getNama();
        alamat = user.getData().getAlamat();
        email = user.getData().getEmail();
        pekerjan = user.getData().getPekerjaan();
        idd = String.valueOf(user.getData().getId());

        String pesan;
        String layanan = "Saya Ingin Konsultasi Mengenai Layanan Bisnis Digital di Siandong Digital";

        pesan = "Nama      : "+usernama+ "\n" +
                "Alamat    : "+alamat+ "\n" +
                "Email     : "+email+ "\n" +
                "Pekerjaan : "+pekerjan+ "\n" +"\n"+
                layanan+ "\n\n" +"\n"+
                "No. Room  : WA058"+idd;

        Intent sendWA = new Intent(Intent.ACTION_SEND);
        sendWA.setType("text/plain");
        sendWA.putExtra(Intent.EXTRA_TEXT, pesan);
        sendWA.putExtra("jid","6281223395224"+"@s.whatsapp.net");
        sendWA.setPackage("com.whatsapp");

        startActivity(sendWA);

    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Keluar dari Sidigi Apps?");
        alertDialogBuilder
                .setMessage("Layanan Digital Bantu Kembangkan Bisnis Anda")
                .setIcon(R.mipmap.icon)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        logout();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void logout() {
        PrefUtil.clear(this);
        startActivity(new Intent(getBaseContext(), Splash.class));
        finish();
    }

    public void ambilData(){

        ApiInterface apiData = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseModel> ambildata = apiData.getData();

        ambildata.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
//                Toast.makeText(Layanan.this,"Berhasil Ambil Data",Toast.LENGTH_SHORT).show();

                listData = response.body().getData();

                rvAdata  = new LayananAdapter(MainActivity.this,listData,listener);
                rvDataLayanan.setAdapter(rvAdata);
                rvAdata.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Koneksi Gagal !",Toast.LENGTH_SHORT).show();
            }
        });


    }


//    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.menu_main, menu);
////        return super.onCreateOptionsMenu(menu);
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
////        switch (item.getItemId()) {
////            case R.id.profile:
////                Intent intent = new Intent(MainActivity.this, SlideActivity.class);
////                startActivity(intent);
////                break;
////        }
////        return super.onOptionsItemSelected(item);
////    }
////
////    private void showRecyclerList() {
////        rvPlace.setLayoutManager(new LinearLayoutManager(this));
////        BuahAdapter lishBuahAdapter = new BuahAdapter(list);
////        rvPlace.setAdapter(lishBuahAdapter);
////
////        lishBuahAdapter.setOnItemClickCallback(new BuahAdapter.OnItemClickCallback(){
////            @Override
////            public void onItemClicked(Buah data) {
////                showSelectedPlace(data);
////            }
////        });
////    }
////
////    private void showSelectedPlace(Buah data) {
////        Intent intent = new Intent(MainActivity.this, DetailLayanan.class);
////        intent.putExtra(BuahDetail.EXTRA_NAME, data.getName());
////        intent.putExtra(BuahDetail.EXTRA_IMG, data.getPhoto());
////        intent.putExtra(BuahDetail.EXTRA_DETAIL, data.getManfaat());
////        startActivity(intent);
////    }
}
