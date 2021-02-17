package com.example.engineeringthesis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.R
import com.example.engineeringthesis.model.GameplayResult
import java.util.*

open class ChildResultsAdapter(private val monChildGameResultListener: ChildResultsAdapter.OnChildResultsItemListener): RecyclerView.Adapter<ChildResultsAdapter.ViewHolder>() {
    var childResultsNames: List<GameplayResult> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildResultsAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.select_results_child, parent, false)
        return ChildResultsAdapter.ViewHolder(itemView, monChildGameResultListener)
    }

    override fun onBindViewHolder(holder: ChildResultsAdapter.ViewHolder, position: Int) {
        holder.childGameData.text = "Gra: "+ childResultsNames[position].gameplayId.gameId.gameName + "\n" +
        "Liczba błędów: " + childResultsNames[position].statisticResultsId.statisticResults + "\n" +
                "Czas w grze: " +childResultsNames[position].statisticResultsId.statisticResults
    }

    override fun getItemCount(): Int {
        return childResultsNames.size
    }
    fun getItem(position: Int):GameplayResult
    {
        return  childResultsNames.get(position)
    }

    fun setchildResultsNamesList(catAdapterlist: List<GameplayResult>) {
        childResultsNames = catAdapterlist
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, onCategoryListener: OnChildResultsItemListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var childGameData:TextView
        var onChildGameResultListener: OnChildResultsItemListener
        override fun onClick(view: View) {
            onChildGameResultListener.onChildResultsItemClick(adapterPosition)
        }

        init {
            childGameData = itemView.findViewById<View>(R.id.childGameData) as TextView
            this.onChildGameResultListener = onCategoryListener
            itemView.setOnClickListener(this)
        }
    }

    interface OnChildResultsItemListener {
        fun onChildResultsItemClick(position: Int)
    }
}