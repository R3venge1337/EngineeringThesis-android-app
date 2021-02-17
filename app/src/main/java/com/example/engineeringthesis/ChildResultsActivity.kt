package com.example.engineeringthesis

import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.adapter.ChildResultsAdapter
import com.example.engineeringthesis.viewmodel.GameplayResultViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ChildResultsActivity  : DaggerAppCompatActivity(), ChildResultsAdapter.OnChildResultsItemListener{
    lateinit var gameplayResultViewModel: GameplayResultViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var childResultsAdapter:ChildResultsAdapter
    @Inject
    lateinit var sharedPreferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_results)
        buildRecyclerView()
        gameplayResultViewModel = ViewModelProvider(this).get(GameplayResultViewModel::class.java)
        var code = sharedPreferences.getString("questUniqueId","")
        gameplayResultViewModel.getGameplayResultByGuestUUID(code!!).observe(this, { resultList -> childResultsAdapter.setchildResultsNamesList(resultList) })
    }

    fun buildRecyclerView()
    {
        recyclerView = findViewById(R.id.childResultsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        childResultsAdapter = ChildResultsAdapter(this)
        recyclerView.adapter = childResultsAdapter
    }

    override fun onChildResultsItemClick(position: Int) {

    }
}