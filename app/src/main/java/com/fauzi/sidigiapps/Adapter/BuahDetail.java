package com.fauzi.sidigiapps.Adapter;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fauzi.sidigiapps.R;
import com.squareup.picasso.Picasso;

public class BuahDetail extends AppCompatActivity {
    public static String EXTRA_NAME = "place_name";
    public static String EXTRA_DETAIL = "place_detail";
    public static String EXTRA_IMG = "place_img";
    TextView placeName, placeDetail;
    ImageView placeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layanan);

        int img = getIntent().getIntExtra(EXTRA_IMG, 0);
        String name = getIntent().getStringExtra(EXTRA_NAME);
        String detail = getIntent().getStringExtra(EXTRA_DETAIL);

        placeImg = findViewById(R.id.img_place);
        placeName = findViewById(R.id.tv_item_name);
        placeDetail = findViewById(R.id.tv_item_detail);

//        Glide.with(this).load(img).into(placeImg);
        Picasso.get().load(img).into(placeImg);
        placeName.setText(name);
        placeDetail.setText(detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra(EXTRA_NAME));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}
