package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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
    fun getTeachersByLanguageName(languageName: String) : LiveData<List<Teacher>>
    {
       return teacherRepository.getTeachersByLanguageName(languageName)
    }

    fun getTeacherWithAccountDetails(accountName : String) : Teacher
    {
       return  teacherRepository.getTeacherWithAccountDetails(accountName)
    }

    fun updateTeacher(teacher:Teacher,teacherId:Int)
    {
        teacherRepository.updateTeacher(teacher,teacherId)
    }


}