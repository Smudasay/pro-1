package com.app7.say.application;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app7.say.application.R;

@SuppressLint("Registered")
public class MainActivity3 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        String[] list = { "1" };
        int[] resId = {  R.drawable.image3,

        };


        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), list, resId);

        ListView listView = (ListView)findViewById(R.id.Clist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long row_id) {
//                TextView textView = (TextView)view.findViewById(R.id.textView1);
//                System.out.println( textView.getText());
                Toast.makeText(getApplicationContext(), "Clicked on item:" +  position , Toast.LENGTH_SHORT).show();
            }
        });
    }
}