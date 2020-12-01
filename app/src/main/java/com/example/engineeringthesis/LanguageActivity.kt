package com.example.engineeringthesis


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.adapter.SelectLanguageAdapter
import com.example.engineeringthesis.adapter.SelectLanguageAdapter.OnLanguageListener
import com.example.engineeringthesis.viewmodel.LanguageViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LanguageActivity : DaggerAppCompatActivity(), OnLanguageListener {

    lateinit var languageViewModel: LanguageViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var languageAdapter:SelectLanguageAdapter
    @Inject
    lateinit var sharedPreferences: SharedPreferences;
    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor;
    @Inject
    lateinit var jacksonMapper: ObjectMapper
    var languagesFlag = arrayOf(R.drawable.ang_icon, R.drawable.niem_icon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_language)
        languageViewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)
        buildRecyclerView()
        languageViewModel.allLanguages().observe(this, { languages -> languageAdapter.setLanguagesList(languages) })
    }

    fun buildRecyclerView()
    {
        recyclerView = findViewById<RecyclerView>(R.id.languageRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        languageAdapter = SelectLanguageAdapter(this)
        recyclerView.adapter = languageAdapter
    }

    override fun onLanguageClick(position: Int) {

        var lang =  languageAdapter.getItem(position)
        var JsonObject = jacksonMapper.writeValueAsString(lang)
        sharedPreferencesEditor.putString("language_selected",JsonObject).apply()
        val intent = Intent(this, TeacherSelectionActivity::class.java)
        startActivity(intent)
        //Toast.makeText(this,jsonExamp.toString(),Toast.LENGTH_SHORT).show()
    }

}