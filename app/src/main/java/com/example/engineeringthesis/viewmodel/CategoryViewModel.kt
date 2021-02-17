package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.engineeringthesis.model.Category
import com.example.engineeringthesis.repository.CategoryRepository

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val categoryRepository: CategoryRepository
    init {
        categoryRepository = CategoryRepository(application)
    }

    fun allCategories(): LiveData<List<Category>> {
        return categoryRepository.allCategories()
    }
    fun saveCategory(category: Category)
    {
        return categoryRepository.saveCategory(category)
    }
    fun getCategory(categoryName:String):Category
    {
        return categoryRepository.getCategory(categoryName)
    }
}