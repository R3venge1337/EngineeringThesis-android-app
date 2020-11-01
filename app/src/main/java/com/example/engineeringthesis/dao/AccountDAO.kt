package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Account
import com.example.engineeringthesis.model.Token
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

@Dao
interface AccountDAO {

    @GET("accounts")
    fun allAccounts(): Flowable<List<Account>>

    @POST("accounts" )
    fun saveAccount(@Body account : Account): Completable

    @GET("accounts")
    fun getAccountByName(@Query("accountName") accountName : String): Single<Account>


    @GET("token")
    fun getToken(): Single<Token>

    @POST("authenticate" )
    fun authenticate(@Query("username") accountName : String,
                    @Query("password") accountPassword : String): Completable
}