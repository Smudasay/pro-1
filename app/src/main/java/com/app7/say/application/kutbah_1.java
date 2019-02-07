package com.app7.say.application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

/**
 * Created by user on 3/10/2561.
 */

public class kutbah_1 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kutbah_1);
        // TextView surah_ans1 = (TextView)this.findViewById(id)
        //ใสไฟล์ PDF
        PDFView pdfView=(PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset ("Gentlemen submissive subject 1.pdf").load();

    }
}
