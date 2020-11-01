package com.example.engineeringthesis


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.Token
import com.example.engineeringthesis.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Retrofit


class LoginActivity : AppCompatActivity() {
    private var accountViewModel: AccountViewModel? = null
    var token: Token? = null
    private var retrofit: Retrofit = RetrofitClient.retrofit!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        logOnButton.setOnClickListener {

            val editTextUsername = editText_user_name.text.toString()
            val editTextloginPassword = loginPasswordEditText.text.toString()
            val editTextloginPasswordRepeat = loginPasswordRepeatEditText.text.toString()

            if (editTextUsername.isEmpty()) {
                editText_user_name.setError("Nazwa użytkownika jest błędna")
            }
            if (editTextloginPassword.isEmpty()) {
                loginPasswordEditText.setError("Hasło jest błędne")
            }
            if (editTextloginPassword != editTextloginPasswordRepeat || editTextloginPassword.isEmpty() || editTextloginPasswordRepeat.isEmpty()) {
                loginPasswordRepeatEditText.setError("Hasło nie zgadza się z hasłem powyżej")
            }
            if (editTextloginPassword == editTextloginPasswordRepeat && editTextUsername.isNotEmpty() && editTextloginPassword.isNotEmpty() && editTextloginPasswordRepeat.isNotEmpty()) {
                accountViewModel!!.authenticate(editTextUsername, editTextloginPassword)
                token = accountViewModel!!.getToken()
                Toast.makeText(this, token.toString(), Toast.LENGTH_LONG).show()
                /*
                OkhttpClient.addNetworkInterceptor { chain ->
                    val original: Request = chain.request()
                    // Request customization: add request headers
                    val requestBuilder: Request.Builder = original.newBuilder()
                            .header("Authorization", "Bearer $token") // <-- this is the important line
                    val request: Request = requestBuilder.build()
                    chain.proceed(request) }.build()
            }

     */
            }

        }
    }
}