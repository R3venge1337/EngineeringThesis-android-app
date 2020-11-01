package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.engineeringthesis.model.Teacher
import com.example.engineeringthesis.repository.TeacherRepository

class TeacherViewModel(application: Application) : AndroidViewModel(application) {
    private val teacherRepository: TeacherRepository

    init {
        teacherRepository = TeacherRepository(application)
    }

    fun saveTeacher(teacher: Teacher)
    {
         teacherRepository.saveTeachers(teacher)
    }
}