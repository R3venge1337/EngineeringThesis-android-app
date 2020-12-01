package com.example.engineeringthesis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.R
import com.example.engineeringthesis.model.Teacher
import java.util.*

open class TeacherAdapter(private val monTeacherListener: TeacherAdapter.OnTeacherListener) : RecyclerView.Adapter<TeacherAdapter.ViewHolder>() {
    var teacherNamesList: List<Teacher> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.select_teacher_list, parent, false)
        return TeacherAdapter.ViewHolder(itemView, monTeacherListener)
    }

    override fun onBindViewHolder(holder: TeacherAdapter.ViewHolder, position: Int) {
        val teacher = teacherNamesList[position]
        val teacherDataString = teacher.teacherName + " " + teacher.teacherSurname + " " + teacher.teacherCity + " "  + teacher.teacherYearBirth
        holder.teacherName.text = teacherDataString
    }

    override fun getItemCount(): Int {
        return teacherNamesList.size
    }

    fun getItem(position: Int):Teacher
    {
        return teacherNamesList.get(position)
    }

    @JvmName("setTeacherNamesList1")
    fun setTeacherNamesList(adapterList : List<Teacher>)
    {
        this.teacherNamesList = adapterList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, onTeacherListener: TeacherAdapter.OnTeacherListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var teacherName: TextView
        var onTeacherListener: TeacherAdapter.OnTeacherListener

        override fun onClick(view: View) {
            onTeacherListener.onTeacherClick(adapterPosition)
        }

        init {
            teacherName = itemView.findViewById<View>(R.id.teacherData) as TextView
            this.onTeacherListener = onTeacherListener
            itemView.setOnClickListener(this)
        }
    }

    interface OnTeacherListener {
        fun onTeacherClick(position: Int)
    }

}