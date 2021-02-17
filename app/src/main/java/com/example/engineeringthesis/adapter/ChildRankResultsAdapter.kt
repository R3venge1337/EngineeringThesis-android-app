package com.example.engineeringthesis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.R
import com.example.engineeringthesis.model.GameplayResult
import java.util.*

open class ChildRankResultsAdapter(private val monChildRankResultsItemListener: ChildRankResultsAdapter.OnChildRankResultsItemListener): RecyclerView.Adapter<ChildRankResultsAdapter.ViewHolder>() {
    var childRankResultsNames: List<GameplayResult> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildRankResultsAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.select_results_rank_child, parent, false)
        return ChildRankResultsAdapter.ViewHolder(itemView, monChildRankResultsItemListener)
    }

    override fun onBindViewHolder(holder: ChildRankResultsAdapter.ViewHolder, position: Int) {
        holder.childGameRankData.text = "Gra: "+ childRankResultsNames[position].gameplayId.gameId.gameName + "\n" +
        "Liczba błędów: " + childRankResultsNames[position].statisticResultsId.statisticResults + "\n" +
                "Czas w grze: " +childRankResultsNames[position].statisticResultsId.statisticResults
    }

    override fun getItemCount(): Int {
        return childRankResultsNames.size
    }
    fun getItem(position: Int):GameplayResult
    {
        return  childRankResultsNames.get(position)
    }

    fun setChildRankResultsNamesList(catAdapterlist: List<GameplayResult>) {
        childRankResultsNames = catAdapterlist
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View, onCategoryListener: OnChildRankResultsItemListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var childGameRankData:TextView
        var onChildGameResultListener: OnChildRankResultsItemListener
        override fun onClick(view: View) {
            onChildGameResultListener.onChildRankResultsItemClick(adapterPosition)
        }

        init {
            childGameRankData = itemView.findViewById<View>(R.id.childGameRankData) as TextView
            this.onChildGameResultListener = onCategoryListener
            itemView.setOnClickListener(this)
        }
    }

    interface OnChildRankResultsItemListener {
        fun onChildRankResultsItemClick(position: Int)
    }
}