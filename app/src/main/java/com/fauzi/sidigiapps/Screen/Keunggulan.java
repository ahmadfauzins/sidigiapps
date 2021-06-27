package com.fauzi.sidigiapps.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fauzi.sidigiapps.MainActivity;
import com.fauzi.sidigiapps.R;

import static maes.tech.intentanim.CustomIntent.customType;

public class Keunggulan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keunggulan);

        ImageView kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Keunggulan.this, MainActivity.class);
                startActivity(intent);
                customType(Keunggulan.this,"fadein-to-fadeout");
            }
        });
    }
}
