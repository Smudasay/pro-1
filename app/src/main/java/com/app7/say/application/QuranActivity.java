package com.app7.say.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class QuranActivity extends AppCompatActivity {
    TextView surah_nas3;
    TextView surah_falak3;
    TextView surah_ikhalas3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);
        surah_nas3 = (TextView)findViewById(R.id.surah_nas3);
        surah_falak3 = (TextView)findViewById(R.id.surah_falak3);
        surah_ikhalas3 = (TextView)findViewById(R.id.surah_ikhalas3);

        surah_nas3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuranActivity.this,surah_ans1Ativity.class);
                startActivity(i);
            }
        });
        surah_falak3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuranActivity.this,surah_falak1Activity.class);
                startActivity(i);
            }
        });
        surah_ikhalas3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuranActivity.this,surah_ikhalas1Activity.class);
                startActivity(i);
            }
        });
    }

}
