package com.example.engineeringthesis

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.adapter.SelectLanguageAdapter
import com.example.engineeringthesis.adapter.SelectLanguageAdapter.OnLanguageListener
import com.example.engineeringthesis.viewmodel.LanguageViewModel

class SelectLanguageActivity : AppCompatActivity(), OnLanguageListener {
    private var languageViewModel: LanguageViewModel? = null
    var languagesFlag = arrayOf(R.drawable.ang_icon, R.drawable.niem_icon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_language)
        languageViewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)
        val recyclerView = findViewById<RecyclerView>(R.id.languageRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val languageAdapter = SelectLanguageAdapter(this)
        recyclerView.adapter = languageAdapter
        languageViewModel!!.allLanguages.observe(this, { languages -> languageAdapter.setLanguagesList(languages) })
    }

    override fun onLanguageClick(position: Int) {
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
    }
}