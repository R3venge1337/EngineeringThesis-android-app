package com.example.engineeringthesis.database.Retrofit

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso


object PicassoClient {

    fun getPiccassoInstance(context: Context, okhttp :OkhttpClient): Picasso {
       return  Picasso.Builder(context)
                .downloader(OkHttp3Downloader(okhttp.getUnsafeOkHttpClient()))
                .build()
    }



}