package com.example.engineeringthesis

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.adapter.CategoryTeacherAdapter
import com.example.engineeringthesis.model.CategoryTeacher
import com.example.engineeringthesis.model.Teacher
import com.example.engineeringthesis.viewmodel.CategoryTeacherViewModel
import com.example.engineeringthesis.viewmodel.CategoryViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class CategoryActivity : DaggerAppCompatActivity(), CategoryTeacherAdapter.OnCategoryListener {

    lateinit var categoryViewModel: CategoryViewModel
    lateinit var categoryTeacherViewModel: CategoryTeacherViewModel
    lateinit var recyclerView: RecyclerView
   // lateinit var categoryAdapter:CategoryAdapter
    lateinit var categoryAdapter:CategoryTeacherAdapter
    @Inject
    lateinit var sharedPreferences: SharedPreferences;
    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor;
    @Inject
    lateinit var jacksonMapper:ObjectMapper
    var categoriesIcon = arrayOf(R.drawable.pilka, R.drawable.elektronika, R.drawable.muzyka)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        categoryTeacherViewModel = ViewModelProvider(this).get(CategoryTeacherViewModel::class.java)
        buildRecyclerView()
        var teacherReturned: String =  sharedPreferences.getString("teacher_selected","null").toString()
        var teacher:Teacher = jacksonMapper.readValue(teacherReturned,Teacher::class.java)
        categoryTeacherViewModel.getAllCategoriesByTeacher(teacher.teacherId).observe(this, { categories -> categoryAdapter.setCategoryNamesList(categories) })

    }

    fun buildRecyclerView()
    {
        recyclerView = findViewById(R.id.categoryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        categoryAdapter = CategoryTeacherAdapter(this)
        recyclerView.adapter = categoryAdapter
    }

    override fun onCategoryClick(position: Int) {
        var cat: CategoryTeacher = categoryAdapter.getItem(position);
        var JsonObject = jacksonMapper.writeValueAsString(cat)
        sharedPreferencesEditor.putString("category_selected",JsonObject).apply()
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)

        //Toast.makeText(this, "Kategoria = " + cat.toString() , Toast.LENGTH_LONG).show()
    }
}