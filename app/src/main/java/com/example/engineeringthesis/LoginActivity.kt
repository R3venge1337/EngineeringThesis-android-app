package com.example.engineeringthesis


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.Token
import com.example.engineeringthesis.utils.GlobalValues
import com.example.engineeringthesis.viewmodel.AccountViewModel
import com.example.engineeringthesis.viewmodel.ChildViewModel
import com.example.engineeringthesis.viewmodel.TeacherViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Retrofit


class LoginActivity : DaggerAppCompatActivity() {
    private var accountViewModel: AccountViewModel? = null
    private var childViewModel: ChildViewModel? = null
    private var teacherViewModel: TeacherViewModel? = null
    var token: Token? = null
    private var retrofit: Retrofit = RetrofitClient.retrofit!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        childViewModel = ViewModelProvider(this).get(ChildViewModel::class.java)
        teacherViewModel = ViewModelProvider(this).get(TeacherViewModel::class.java)
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
            if (editTextloginPassword == editTextloginPasswordRepeat && editTextUsername.isNotEmpty() && editTextloginPassword.isNotEmpty()
                    && editTextloginPasswordRepeat.isNotEmpty()) {
                accountViewModel!!.authenticate(editTextUsername, editTextloginPassword)
                token = accountViewModel!!.getToken()
                //Toast.makeText(this, token.toString(), Toast.LENGTH_LONG).show()
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
                GlobalValues.currentAccount = accountViewModel!!.getAccountByName(editTextUsername)
                //Toast.makeText(this,GlobalValues.currentAccount.toString(),Toast.LENGTH_SHORT).show()
                if(GlobalValues.currentAccount!!.role.roleName.equals("ROLE_dziecko"))
                {
                    GlobalValues.currentChild =  childViewModel!!.getChildWithAccountDetails(GlobalValues.currentAccount!!.accountName)
                    val mainActivity = Intent(this, MainActivity::class.java)
                    startActivity(mainActivity)
                }
                else if(GlobalValues.currentAccount!!.role.roleName.equals("ROLE_nauczyciel")) {

                    GlobalValues.currentTeacher = teacherViewModel!!.getTeacherWithAccountDetails(GlobalValues.currentAccount!!.accountName)
                    val mainActivity = Intent(this, MainActivity::class.java)
                    startActivity(mainActivity)
                }
                else
                {
                    Toast.makeText(this, "Witaj Adminie", Toast.LENGTH_SHORT).show()
                    val mainActivity = Intent(this, MainActivity::class.java)
                    startActivity(mainActivity)
                }


            }

        }
    }
}