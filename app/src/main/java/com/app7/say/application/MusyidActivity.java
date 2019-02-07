package com.app7.say.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

public class MusyidActivity extends AppCompatActivity {

    TextView kutbah1;
    TextView kutbah2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musyid);

        kutbah1 = (TextView)findViewById(R.id.musyid3);
        kutbah1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MusyidActivity.this,kutbah_1.class);
                startActivity(i);
            }
        });

        kutbah2 = (TextView)findViewById(R.id.musyid4);
        kutbah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MusyidActivity.this,kutbah_2.class);
                startActivity(i);
            }
        });

    }

}
