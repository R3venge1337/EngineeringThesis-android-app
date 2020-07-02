package com.example.engineeringthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button selectLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectLanguage = findViewById(R.id.select_language_button);
        selectLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectLang = new Intent(MainActivity.this,SelectLanguageActivity.class);
                startActivity(selectLang);
            }
        });
    }
}