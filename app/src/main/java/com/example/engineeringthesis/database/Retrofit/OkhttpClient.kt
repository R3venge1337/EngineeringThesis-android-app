package com.example.engineeringthesis.database.Retrofit

import com.example.engineeringthesis.utils.GlobalValues.JwtToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


object OkhttpClient {
  /*
    lateinit var jwtToken: Token

    fun setToken(tok: Token) {
        this.jwtToken = tok
    }
*/
    fun getUnsafeOkHttpClient(): OkHttpClient {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
        })

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory

        //Dla celow testowych pokazuje zadania http  pochodzace z retrofita
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .addInterceptor { chain ->
                    val org = chain.request()
                    var requestBuilder = org.newBuilder()
                                .header("Authorization", JwtToken!!)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .addInterceptor(logging)
                .hostnameVerifier { _, _ -> true }
                .build()
    }
}