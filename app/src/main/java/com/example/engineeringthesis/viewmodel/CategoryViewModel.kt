package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.engineeringthesis.model.Category
import com.example.engineeringthesis.repository.CategoryRepository

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val categoryRepository: CategoryRepository
    val allCategories: LiveData<List<Category>>
        get() = categoryRepository.allCategories

    init {
        categoryRepository = CategoryRepository(application)
    }
}