package com.example.engineeringthesis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.R
import com.example.engineeringthesis.model.Language
import com.example.engineeringthesis.utils.LiveDataErrorHandling
import java.util.*

class SelectLanguageAdapter(private val monLanguageListener: OnLanguageListener) : RecyclerView.Adapter<SelectLanguageAdapter.ViewHolder>() {
    var languageNames: List<Language> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.select_language_list, parent, false)
        return ViewHolder(itemView, monLanguageListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (_, languageName) = languageNames[position]
        holder.nameLang.text = languageName
        // holder.iconLang.setImageResource(languageImages[position]);
    }

    override fun getItemCount(): Int {
        return languageNames.size
    }

    fun setLanguagesList(langAdapterlist: List<Language>) {
        languageNames = langAdapterlist
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, onLanguageListener: OnLanguageListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var nameLang: TextView
        var iconLang: ImageView
        var onLanguageListener: OnLanguageListener

        override fun onClick(view: View) {
            onLanguageListener.onLanguageClick(adapterPosition)
        }

        init {
            nameLang = itemView.findViewById<View>(R.id.languageName) as TextView
            iconLang = itemView.findViewById<View>(R.id.languageIcon) as ImageView
            this.onLanguageListener = onLanguageListener
            itemView.setOnClickListener(this)
        }
    }

    interface OnLanguageListener {
        fun onLanguageClick(position: Int)
    }
}