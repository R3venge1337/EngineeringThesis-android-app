package com.example.engineeringthesis

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.adapter.TeacherAdapter
import com.example.engineeringthesis.model.Language
import com.example.engineeringthesis.viewmodel.TeacherViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class TeacherSelectionActivity : DaggerAppCompatActivity() , TeacherAdapter.OnTeacherListener {

    private lateinit var teacherViewModel: TeacherViewModel
    lateinit var recyclerView:RecyclerView
    lateinit var teacherAdapter:TeacherAdapter
    @Inject
    lateinit var sharedPreferences: SharedPreferences;
    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor;
    @Inject
    lateinit var jacksonMapper: ObjectMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_selection)
        teacherViewModel = ViewModelProvider(this).get(TeacherViewModel::class.java)
        buildRecyclerView()
        var languageReturned =  sharedPreferences.getString("language_selected","null").toString()
        var langObject : Language = jacksonMapper.readValue(languageReturned,Language::class.java)
        teacherViewModel.getTeachersByLanguageName(langObject.languageName).observe(this,{teacher -> teacherAdapter.setTeacherNamesList(teacher)})
    }

    fun buildRecyclerView()
    {
        recyclerView = findViewById<RecyclerView>(R.id.teacherRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        teacherAdapter = TeacherAdapter(this)
        recyclerView.adapter = teacherAdapter
    }

    override fun onTeacherClick(position: Int) {
        var teacher = teacherAdapter.getItem(position)
        var JsonObject = jacksonMapper.writeValueAsString(teacher)
        sharedPreferencesEditor.putString("teacher_selected",JsonObject).apply()
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
        //Toast.makeText(this,teacher.toString(),Toast.LENGTH_SHORT).show()
    }
}