package com.example.engineeringthesis.utils

import com.example.engineeringthesis.model.Token
import dagger.Module
import dagger.Provides
import okhttp3.*
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Module
class JwtAuthenticationInterceptor @Inject constructor() : Interceptor {
    private var jwtToken: Token? = null

    fun setJwtToken(jwtToken: Token?) {
        this.jwtToken = jwtToken
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder: Request.Builder = original.newBuilder()
                .header("Authorization", "Bearer ${jwtToken.toString()}")
        //String.format("Bearer %s", jwtToken));
        val request: Request = builder.build()
        return chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache?, interceptor: JwtAuthenticationInterceptor?): OkHttpClient? {
        val client = OkHttpClient.Builder()
        client.addInterceptor(interceptor)
        client.cache(cache)
        return client.build()
    }

}