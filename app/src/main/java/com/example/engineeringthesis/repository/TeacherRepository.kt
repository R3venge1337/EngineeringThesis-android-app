package com.example.engineeringthesis.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.engineeringthesis.dao.TeacherDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.Teacher
import io.reactivex.schedulers.Schedulers.newThread
import retrofit2.Retrofit
import javax.inject.Inject

class TeacherRepository @Inject constructor(application: Application?) {
    private val teacherDAO: TeacherDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        teacherDAO = retrofitClient!!.create(TeacherDAO::class.java)
    }

    fun saveTeachers(teacher: Teacher)
    {
        teacherDAO.saveTeacher(teacher).subscribeOn(newThread()).blockingAwait()
    }

    fun getTeachersByLanguageName(languageName:String): LiveData<List<Teacher>>
    {
        return LiveDataReactiveStreams.fromPublisher(teacherDAO.getTeachersByLanguageName(languageName).subscribeOn(newThread()))
    }

    fun  getTeacherWithAccountDetails(accountName : String) : Teacher
    {
       return teacherDAO.getTeacherWithAccount(accountName).subscribeOn(newThread()).blockingGet()
    }

    fun updateTeacher(teacher: Teacher,teacherId:Int)
    {
        teacherDAO.updateTeacher(teacher,teacherId).subscribeOn(newThread()).blockingAwait()
    }



}