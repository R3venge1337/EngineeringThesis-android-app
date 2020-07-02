package com.example.engineeringthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.engineeringthesis.Adapter.SelectLanguageAdapter;

import java.nio.channels.InterruptedByTimeoutException;

public class SelectLanguageActivity extends AppCompatActivity {
        ListView lv;
        String languages[] = {"Angielski","Niemiecki"};
        Integer languagesFlag[]={R.drawable.ang_icon,R.drawable.niem_icon};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        lv = findViewById(R.id.selectLanguageListView);
        SelectLanguageAdapter selectLanguageAdapter = new SelectLanguageAdapter(SelectLanguageActivity.this,languages,languagesFlag);
        lv.setAdapter(selectLanguageAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent = new Intent(SelectLanguageActivity.this,CategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}