package com.example.engineeringthesis

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.adapter.GameAdapter
import com.example.engineeringthesis.model.Game
import com.example.engineeringthesis.viewmodel.GameViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class GameActivity : DaggerAppCompatActivity(),GameAdapter.onGameListener {

    lateinit var gameViewModel: GameViewModel
    lateinit var recyclerView:RecyclerView
    lateinit var gameAdapter:GameAdapter
    lateinit var game: Game
    @Inject
    lateinit var sharedPreferences: SharedPreferences;
    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        buildRecyclerView()
        gameViewModel.allGames().observe(this, { game -> gameAdapter.setGameList(game) })
    }

    fun buildRecyclerView()
    {
        recyclerView = findViewById(R.id.gameRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        gameAdapter = GameAdapter(this)
        recyclerView.adapter = gameAdapter
    }

    override fun onGameClick(position: Int) {
        when(position)
        {
            0 -> {
                game =  gameAdapter.getItem(position)
                val selectFindoutPicture = Intent(this, FindOutPictureActivity::class.java)
                startActivity(selectFindoutPicture)
                //Toast.makeText(this,game.toString(),Toast.LENGTH_SHORT).show()
            }
            1 -> {
                game =  gameAdapter.getItem(position)
                val selectFindOutVocabulary = Intent(this, FindOutVocabularyActvity::class.java)
                startActivity(selectFindOutVocabulary)
                //Toast.makeText(this,game.toString(),Toast.LENGTH_SHORT).show()
            }
            2 -> {
                game =  gameAdapter.getItem(position)
                val selectDragAndDrop = Intent(this, DragAndDropActivity::class.java)
                startActivity(selectDragAndDrop)
                //Toast.makeText(this,game.toString(),Toast.LENGTH_SHORT).show()
            }
            3 -> {
                game =  gameAdapter.getItem(position)
                Toast.makeText(this,game.toString(),Toast.LENGTH_SHORT).show()
                //Toast.makeText(this,"Wybrałes Memory Game",Toast.LENGTH_SHORT).show()
            }
            4 -> {
                game =  gameAdapter.getItem(position)
                Toast.makeText(this,game.toString(),Toast.LENGTH_SHORT).show()
                //Toast.makeText(this,"Wybrałes Select And Adjust ",Toast.LENGTH_SHORT).show()
            }

        }
        sharedPreferencesEditor.putString("game_selected",game.gameName).apply()
    }
}