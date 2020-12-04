package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.engineeringthesis.model.Child
import com.example.engineeringthesis.repository.ChildRepository

class ChildViewModel(application: Application) : AndroidViewModel(application) {
    private val childRepository: ChildRepository

    init {
        childRepository = ChildRepository(application)
    }

    fun saveChild(child: Child)
    {
        childRepository.saveChild(child)
    }

    fun getChildWithAccountDetails(accountName:String) : Child
    {
        return childRepository.getChildWithAccountDetails(accountName)
    }

    fun updateChild(child: Child,childId:Int)
    {
        childRepository.updateChild(child,childId)
    }


}