package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.engineeringthesis.model.CategoryTeacher
import com.example.engineeringthesis.repository.CategoryTeacherRepository

class CategoryTeacherViewModel(application: Application) : AndroidViewModel(application) {
    private val categoryTeacherRepository: CategoryTeacherRepository

    init {
        categoryTeacherRepository = CategoryTeacherRepository(application)
    }
    fun getAllCategoriesByTeacher(teacherId:Int): LiveData<List<CategoryTeacher>>
    {
        return categoryTeacherRepository.getAllCategoriesTeacher(teacherId)
    }

    fun saveCategoryTeacher(categoryTeacher: CategoryTeacher)
    {
        categoryTeacherRepository.saveCategoryTeacher(categoryTeacher)
    }

    fun getAllCategoriesTeacherSingle(teacherId:Int): List<CategoryTeacher>
    {
        return  categoryTeacherRepository.getAllCategoriesTeacherSingle(teacherId)
    }
}