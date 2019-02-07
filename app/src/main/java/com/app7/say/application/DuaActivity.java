package com.app7.say.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class DuaActivity extends AppCompatActivity {

    TextAdapter adapter;
    RecyclerView recyclerView;

    List<String> thList;
    List<String> arList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua);

        recyclerView = findViewById(R.id.recycler_view);

        thList = Arrays.asList("ดูอาก่อนนอน","ดุอาตื่นนอน","ดูอาก่อนรับประทานอาหาร","ดูอาหลังรับประทานอาหารและดื่มนม");
        arList = Arrays.asList("بِاسْمِكَ الّلهُمَّ أَحْيَي وَأمُوْتُِِ", "الْحَمْد لِلّهِ الَّذِىْ أَحْيَا نَا بَعْدَ مَا أَمَاتَنَاوَإِلَيْهِ النُّشُوْر",
                "اللّهُمَّ بَارِكْ لَنَا فِيْمَا رَزَقْتَنَا وَقِنَاعَذاَبَ النَّار", "الْحَمْدُ لِلّهِ الّذِى أَطْعَمَنَا وَسَقَانَاوَجَعَلَنَامُسْلِمِيْن");

        adapter = new TextAdapter(thList,arList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
