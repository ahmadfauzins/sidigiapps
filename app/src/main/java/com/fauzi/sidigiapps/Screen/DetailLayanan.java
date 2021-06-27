package com.fauzi.sidigiapps.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fauzi.sidigiapps.Adapter.LayananAdapter;
import com.fauzi.sidigiapps.MainActivity;
import com.fauzi.sidigiapps.Model.User;
import com.fauzi.sidigiapps.R;
import com.fauzi.sidigiapps.utility.PrefUtil;

import static maes.tech.intentanim.CustomIntent.customType;

public class DetailLayanan extends AppCompatActivity {

    TextView mJudul, mTittle, mDesc;
    Button ajukan;
    private int id;
    String usernama,alamat,email,pekerjan,idd,judull;
    private String judul, deskripsi, pic, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layanan);

        ImageView kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailLayanan.this, Layanan.class);
                startActivity(intent);
                customType(DetailLayanan.this, "fadein-to-fadeout");
            }
        });

        mJudul = findViewById(R.id.judul);
        mTittle = findViewById(R.id.titlemenu);
        mDesc = findViewById(R.id.descbrand);

        ajukan = findViewById(R.id.btn_layanan);
        ajukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               kirimWA();
            }
        });


        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        judul = intent.getStringExtra("nama");
        deskripsi = intent.getStringExtra("deskripsi");
        pic = intent.getStringExtra("pic");
        image = intent.getStringExtra("image");

        setDataFromIntentExtra();




    }

    private void setDataFromIntentExtra() {
        if (id != 0) {

            mJudul.setText(judul);
            mTittle.setText(judul);
            mDesc.setText(deskripsi);


        }
    }
    private void kirimWA() {

        User user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
        usernama = user.getData().getNama();
        alamat = user.getData().getAlamat();
        email = user.getData().getEmail();
        pekerjan = user.getData().getPekerjaan();
        idd = String.valueOf(user.getData().getId());



        String pesan;
        String layanan = "Saya Ingin Konsultasi Mengenai Layanan ";

        pesan = "Nama      : "+usernama+ "\n" +
                "Alamat    : "+alamat+ "\n" +
                "Email     : "+email+ "\n" +
                "Pekerjaan : "+pekerjan+ "\n" +"\n"+
                layanan+judul+ "\n\n" +"\n"+
                "No. Room  : WA058"+idd;


        Intent sendWA = new Intent(Intent.ACTION_SEND);
        sendWA.setType("text/plain");
        sendWA.putExtra(Intent.EXTRA_TEXT, pesan);
        sendWA.putExtra("jid","6281223395224"+"@s.whatsapp.net");
        sendWA.setPackage("com.whatsapp");

        startActivity(sendWA);

    }
}