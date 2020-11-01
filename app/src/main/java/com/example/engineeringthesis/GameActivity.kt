package com.example.engineeringthesis

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.adapter.GameAdapter
import com.example.engineeringthesis.viewmodel.GameViewModel

class GameActivity : AppCompatActivity(),GameAdapter.onGameListener {
    private var gameViewModel: GameViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        val recyclerView = findViewById<RecyclerView>(R.id.gameRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val gameAdapter = GameAdapter(this)
        recyclerView.adapter = gameAdapter
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        gameViewModel!!.allGames().observe(this, { game -> gameAdapter.setGameList(game) })
    }

    override fun onGameClick(position: Int) {
        if(position == 0)
        {
            val selectFindoutPicture = Intent(this, FindOutPictureActivity::class.java)
            startActivity(selectFindoutPicture)
        }
        else if(position == 1) Toast.makeText(this,"Wybrałes Find Out Vocabulary",Toast.LENGTH_SHORT).show()
        else if(position == 2) Toast.makeText(this,"Wybrałes Drag And Drop",Toast.LENGTH_SHORT).show()
        else if(position == 2) Toast.makeText(this,"Wybrałes Memory Game",Toast.LENGTH_SHORT).show()
        else if(position == 4) Toast.makeText(this,"Wybrałes Select And Adjust ",Toast.LENGTH_SHORT).show()
    }
}