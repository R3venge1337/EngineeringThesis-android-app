package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Role
import io.reactivex.Flowable
import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Query


@Dao
interface RoleDAO {

    @GET("roles")
    fun allRoles(): Flowable<List<Role>>

    @GET("roles")
    fun getRoleByName(@Query("name") roleName : String): Single<Role>
}