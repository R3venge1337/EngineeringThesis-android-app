package com.example.engineeringthesis

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.adapter.CategoryAdapter
import com.example.engineeringthesis.adapter.CategoryAdapter.OnCategoryListener
import com.example.engineeringthesis.viewmodel.CategoryViewModel

class CategoryActivity : AppCompatActivity(), OnCategoryListener {

    private var categoryViewModel: CategoryViewModel? = null
    var categoriesIcon = arrayOf(R.drawable.pilka, R.drawable.elektronika, R.drawable.muzyka)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val recyclerView = findViewById<RecyclerView>(R.id.categoryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val categoryAdapter = CategoryAdapter(this)
        recyclerView.adapter = categoryAdapter
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryViewModel!!.allCategories.observe(this, { categories -> categoryAdapter.setCategoryNamesList(categories) })
    }

    override fun onCategoryClick(position: Int) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }
}