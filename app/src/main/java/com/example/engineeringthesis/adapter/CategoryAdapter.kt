package com.example.engineeringthesis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.R
import com.example.engineeringthesis.model.Category
import java.util.*

open class CategoryAdapter(private val monCategoryListener: OnCategoryListener) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var categoryNames: List<Category> = ArrayList()
    //lateinit var categoryIcon: Array<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.select_category_list, parent, false)
        return ViewHolder(itemView, monCategoryListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (_, categoryName) = categoryNames[position]
        holder.nameCat.text = categoryName
        //holder.iconCat.setImageResource(categoryIcon[position]);
    }

    override fun getItemCount(): Int {
        return categoryNames.size
    }


    fun setCategoryNamesList(catAdapterlist: List<Category>) {
        categoryNames = catAdapterlist
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, onCategoryListener: OnCategoryListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var nameCat: TextView
        var iconCat: ImageView
        var onCategoryListener: OnCategoryListener
        override fun onClick(view: View) {
            onCategoryListener.onCategoryClick(adapterPosition)
        }

        init {
            iconCat = itemView.findViewById<View>(R.id.categoryIcon) as ImageView
            nameCat = itemView.findViewById<View>(R.id.categoryName) as TextView
            this.onCategoryListener = onCategoryListener
            itemView.setOnClickListener(this)
        }
    }

    interface OnCategoryListener {
        fun onCategoryClick(position: Int)
    }
}