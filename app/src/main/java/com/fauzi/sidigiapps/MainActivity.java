package com.fauzi.sidigiapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.fauzi.sidigiapps.Adapter.BuahAdapter;
import com.fauzi.sidigiapps.Adapter.BuahDetail;
import com.fauzi.sidigiapps.Model.Buah;
import com.fauzi.sidigiapps.Model.BuahContent;
import com.fauzi.sidigiapps.Model.User;
import com.fauzi.sidigiapps.Screen.DetailLayanan;
import com.fauzi.sidigiapps.utility.PrefUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPlace;
    private ArrayList<Buah> list = new ArrayList<>();
    ImageSlider imageSlider;
    TextView namauser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
        namauser =  findViewById(R.id.namauser);
        namauser.setText("Selamat Datang "+user.getData().getNama());

        imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://sasanadigital.com/wp-content/uploads/2020/10/5-Channel-Digital-Marketing-e1601911140396.jpg","1 New"));
        slideModels.add(new SlideModel("https://i0.wp.com/projasaweb.com/wp-content/uploads/2019/03/strategi-digital-marketing.png","2 New"));
        slideModels.add(new SlideModel("https://i0.wp.com/projasaweb.com/wp-content/uploads/2018/09/digital-marketing.png","3 New"));
        slideModels.add(new SlideModel("https://i0.wp.com/projasaweb.com/wp-content/uploads/2019/03/cara-bisnis-online-image.png","4 New"));
        imageSlider.setImageList(slideModels,true);

        ImageView imLogout = findViewById(R.id.logout);
        imLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });



        rvPlace = findViewById(R.id.rv_buah);
        rvPlace.setHasFixedSize(true);
        list.addAll(BuahContent.getListData());
        showRecyclerList();

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(MainActivity.this, SlideActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRecyclerList() {
        rvPlace.setLayoutManager(new LinearLayoutManager(this));
        BuahAdapter lishBuahAdapter = new BuahAdapter(list);
        rvPlace.setAdapter(lishBuahAdapter);

        lishBuahAdapter.setOnItemClickCallback(new BuahAdapter.OnItemClickCallback(){
            @Override
            public void onItemClicked(Buah data) {
                showSelectedPlace(data);
            }
        });
    }

    private void showSelectedPlace(Buah data) {
        Intent intent = new Intent(MainActivity.this, DetailLayanan.class);
        intent.putExtra(BuahDetail.EXTRA_NAME, data.getName());
        intent.putExtra(BuahDetail.EXTRA_IMG, data.getPhoto());
        intent.putExtra(BuahDetail.EXTRA_DETAIL, data.getManfaat());
        startActivity(intent);
    }
}
