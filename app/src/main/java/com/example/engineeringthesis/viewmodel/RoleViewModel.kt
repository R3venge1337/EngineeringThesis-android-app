package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.engineeringthesis.model.Role
import com.example.engineeringthesis.repository.RoleRepository
import io.reactivex.rxjava3.core.Single

class RoleViewModel (application: Application) : AndroidViewModel(application) {
    private val roleRepository:RoleRepository

    init {
        roleRepository = RoleRepository(application)
    }
    fun getRoleByName(roleName:String): Role
    {
        return roleRepository.getRoleByName(roleName).blockingGet();
    }

}