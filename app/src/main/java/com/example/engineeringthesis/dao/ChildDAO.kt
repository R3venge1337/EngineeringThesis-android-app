package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Child
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

@Dao
interface ChildDAO {

    @POST("children" )
    fun saveChild(@Body child : Child): Completable

    @GET("children/accounts")
    fun getChildWithAccount(@Query("accountName") accountName : String): Single<Child>

    @PUT("children/{childId}" )
    fun updateChild(@Body child : Child,
                    @Path("childId") childId : Int): Completable
}