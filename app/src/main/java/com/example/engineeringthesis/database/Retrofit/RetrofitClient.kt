package com.example.engineeringthesis.database.Retrofit



import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory


object RetrofitClient {
    const val BASE_URL = "https://192.168.1.24/api/"

    private var jwtToken:String? = null

     val retrofit: Retrofit? = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkhttpClient.getUnsafeOkHttpClient())
            .build()
}