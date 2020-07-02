package com.example.engineeringthesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.engineeringthesis.Adapter.CategoryAdapter;
import com.example.engineeringthesis.Adapter.SelectLanguageAdapter;

public class CategoryActivity extends AppCompatActivity {
    ListView lv;
    String categories[] = {"Sport","Elektronika","Muzyka"};
    Integer categoriesIcon[]={R.drawable.pilka,R.drawable.elektronika,R.drawable.muzyka};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        lv = findViewById(R.id.selectCategoryListView);
        CategoryAdapter selectCategoryAdapter = new CategoryAdapter(CategoryActivity.this,categories,categoriesIcon);
        lv.setAdapter(selectCategoryAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent = new Intent(CategoryActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });
    }
}