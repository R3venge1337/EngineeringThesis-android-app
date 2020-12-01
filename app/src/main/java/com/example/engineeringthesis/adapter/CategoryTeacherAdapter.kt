package com.example.engineeringthesis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.R
import com.example.engineeringthesis.model.CategoryTeacher
import java.util.*

open class CategoryTeacherAdapter(private val monCategoryListener: OnCategoryListener) : RecyclerView.Adapter<CategoryTeacherAdapter.ViewHolder>() {
    var categoryNames: List<CategoryTeacher> = ArrayList()
    //lateinit var categoryIcon: Array<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.select_category_list, parent, false)
        return ViewHolder(itemView, monCategoryListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val (_, sd) = categoryNames[position]
        holder.nameCat.text = categoryNames.get(position).categoryId.categoryName
        //holder.iconCat.setImageResource(categoryIcon[position]);
    }

    override fun getItemCount(): Int {
        return categoryNames.size
    }

    fun getItem(position: Int):CategoryTeacher
    {
       return  categoryNames.get(position)
    }

    fun setCategoryNamesList(catAdapterlist: List<CategoryTeacher>) {
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