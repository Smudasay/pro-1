package com.app7.say.application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;


public class kutbah_2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kutbah_2);
        // TextView surah_ans1 = (TextView)this.findViewById(id)
        //ใสไฟล์ PDF
        PDFView pdfView=(PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset ("Gentlemen submissive subject 1").load();

    }
}