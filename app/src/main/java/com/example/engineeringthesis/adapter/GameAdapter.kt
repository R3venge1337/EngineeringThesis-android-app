package com.example.engineeringthesis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.R
import com.example.engineeringthesis.model.Game
import java.util.*

open class GameAdapter(private val monGameListener: GameAdapter.onGameListener) : RecyclerView.Adapter<GameAdapter.ViewHolder>(){
    var gameNames: List<Game> = ArrayList()
    lateinit var gameIcon: Array<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.select_game_list, parent, false)
        return GameAdapter.ViewHolder(itemView, monGameListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (_, gameName) = gameNames[position]
        holder.nameGame.text = gameName
    }

    override fun getItemCount(): Int {
        return gameNames.size
    }

    
    fun setGameList(gamAdapterlist: List<Game>) {
        gameNames = gamAdapterlist
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, onGameListener: GameAdapter.onGameListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var nameGame: TextView
        var iconGame: ImageView
        var onGameListener: GameAdapter.onGameListener

        override fun onClick(view: View) {
            onGameListener.onGameClick(adapterPosition)
        }

        init {
            iconGame = itemView.findViewById<View>(R.id.gameImageView) as ImageView
            nameGame = itemView.findViewById<View>(R.id.gameTextView) as TextView
            this.onGameListener = onGameListener
            itemView.setOnClickListener(this)
        }
    }
    interface onGameListener {
        fun onGameClick(position: Int)
    }

}