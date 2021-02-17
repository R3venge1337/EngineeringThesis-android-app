package com.example.engineeringthesis

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.adapter.ChildRankResultsAdapter
import com.example.engineeringthesis.viewmodel.GameplayResultViewModel
import dagger.android.support.DaggerAppCompatActivity

class ChildRankGameActivity : DaggerAppCompatActivity(), ChildRankResultsAdapter.OnChildRankResultsItemListener {
    private var gameName:String? = ""
    lateinit var gameplayResultViewModel: GameplayResultViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var childRankResultsAdapter:ChildRankResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_rank_game)
        buildRecyclerView()
        gameplayResultViewModel = ViewModelProvider(this).get(GameplayResultViewModel::class.java)
        val extras = intent.extras
        if (extras != null) {
            gameName = extras.getString("GAME_NAME")
        }
        gameplayResultViewModel.getGameplayResultByGameName(gameName!!).observe(this,
                { resultList -> childRankResultsAdapter.setChildRankResultsNamesList(resultList)})
    }
    fun buildRecyclerView()
    {
        recyclerView = findViewById(R.id.childRankResultsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        childRankResultsAdapter = ChildRankResultsAdapter(this)
        recyclerView.adapter = childRankResultsAdapter
    }

    override fun onChildRankResultsItemClick(position: Int) {

    }
}