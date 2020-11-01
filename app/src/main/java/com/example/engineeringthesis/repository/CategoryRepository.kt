package com.example.engineeringthesis.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.engineeringthesis.dao.CategoryDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.Category
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class CategoryRepository @Inject constructor(application: Application?) {
    private val categoryDAO: CategoryDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        categoryDAO = retrofitClient!!.create(CategoryDAO::class.java)
    }

    fun allCategories(): LiveData<List<Category>> {
        return LiveDataReactiveStreams.fromPublisher(categoryDAO.allCategories().subscribeOn(Schedulers.newThread()))
    }
}