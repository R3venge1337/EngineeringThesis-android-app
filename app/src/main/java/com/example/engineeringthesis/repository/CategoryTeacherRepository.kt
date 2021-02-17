package com.example.engineeringthesis.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.engineeringthesis.dao.CategoryTeacherDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.CategoryTeacher
import io.reactivex.schedulers.Schedulers.newThread
import retrofit2.Retrofit
import javax.inject.Inject

class CategoryTeacherRepository @Inject constructor(application: Application?) {
    private var categoryTeacherDAO: CategoryTeacherDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        categoryTeacherDAO = retrofitClient!!.create(CategoryTeacherDAO::class.java)
    }

    fun getAllCategoriesTeacher(teacherId:Int): LiveData<List<CategoryTeacher>>
    {
        return LiveDataReactiveStreams.fromPublisher(categoryTeacherDAO.getAllCategoriesTeacher(teacherId).subscribeOn(newThread()))
    }

    fun saveCategoryTeacher(categoryTeacher: CategoryTeacher)
    {
        categoryTeacherDAO.saveCategoryTeacher(categoryTeacher).subscribeOn(newThread()).blockingAwait()
    }
    fun getAllCategoriesTeacherSingle(teacherId:Int): List<CategoryTeacher>
    {
        return categoryTeacherDAO.getAllCategoriesTeacher(teacherId).subscribeOn(newThread()).blockingSingle()
    }


}