package com.fauzi.sidigiapps.Model;

import com.fauzi.sidigiapps.R;

import java.util.ArrayList;

public class BuahContent {
    public static String[]namabuah ={
            "BRANDING DESIGN","WEB DEVELOPMENT","IKLANKAN BISNIS ANDA"
    };

    public static String[]manfaat={
            "Apel adalah jenis buah-buahan, atau buah yang dihasilkan dari pohon buah apel. Buah apel biasanya berwarna merah kulitnya jika masak dan (siap dimakan), namun bisa juga kulitnya berwarna hijau atau kuning. Kulit buahnya agak lembek, daging buahnya keras. Buah ini memiliki beberapa biji di dalamnya.",
            "Bengkuang atau bengkoang (Pachyrhizus erosus) dikenal dari umbi (cormus) putihnya yang bisa dimakan sebagai komponen rujak dan asinan atau dijadikan masker untuk menyegarkan wajah dan memutihkan kulit. Tumbuhan yang berasal dari Benua Amerika ini termasuk dalam suku polong-polongan atau Fabaceae.",
            "Jeruk atau limau adalah semua tumbuhan berbunga anggota marga Citrus dari suku Rutaceae (suku jeruk-jerukan). Anggotanya berbentuk pohon dengan buah yang berdaging dengan rasa masam yang segar, meskipun banyak di antara anggotanya yang memiliki rasa manis. Rasa masam berasal dari kandungan asam sitrat yang memang menjadi terkandung pada semua anggotanya."
    };

    private static int[] buahImage= {
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon
    };

    public static ArrayList<Buah> getListData() {
        ArrayList<Buah> list = new ArrayList<>();
        for (int position = 0; position < namabuah.length; position++) {
            Buah place = new Buah();
            place.setName(namabuah[position]);
            place.setManfaat(manfaat[position]);
            place.setPhoto(buahImage[position]);
            list.add(place);
        }
        return list;
    }


}
