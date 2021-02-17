package com.example.engineeringthesis


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.utils.GlobalValues
import com.example.engineeringthesis.utils.GlobalValues.JwtToken
import com.example.engineeringthesis.utils.GlobalValues.currentChild
import com.example.engineeringthesis.utils.GlobalValues.currentTeacher
import com.example.engineeringthesis.viewmodel.AccountViewModel
import com.example.engineeringthesis.viewmodel.ChildViewModel
import com.example.engineeringthesis.viewmodel.TeacherViewModel
import dagger.android.support.DaggerAppCompatActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Retrofit


class LoginActivity : DaggerAppCompatActivity() {
    private var accountViewModel: AccountViewModel? = null
    private var childViewModel: ChildViewModel? = null
    private var teacherViewModel: TeacherViewModel? = null
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
              accountViewModel!!.authenticate(editTextUsername, editTextloginPassword).toString()

                JwtToken = "Bearer "+ accountViewModel!!.getToken().toString()
                //Toast.makeText(this, JwtToken.toString(), Toast.LENGTH_LONG).show()

                GlobalValues.currentAccount = accountViewModel!!.getAccountByName(editTextUsername)
                //Toast.makeText(this,GlobalValues.currentAccount.toString(),Toast.LENGTH_SHORT).show()
                if(GlobalValues.currentAccount!!.role.roleName.equals("ROLE_dziecko"))
                {
                    currentChild =  childViewModel!!.getChildWithAccountDetails(GlobalValues.currentAccount!!.accountName)
                    Toasty.info(this, "Witaj "+ currentChild!!.childName, Toast.LENGTH_SHORT).show()
                    val mainActivity = Intent(this, MainActivity::class.java)
                    startActivity(mainActivity)
                }
                else if(GlobalValues.currentAccount!!.role.roleName.equals("ROLE_nauczyciel")) {

                    currentTeacher = teacherViewModel!!.getTeacherWithAccountDetails(GlobalValues.currentAccount!!.accountName)
                    Toasty.info(this, "Witaj "+ currentTeacher!!.teacherName, Toast.LENGTH_SHORT).show()
                    val mainActivity = Intent(this, MainActivity::class.java)
                    startActivity(mainActivity)
                }
                else
                {
                    Toasty.info(this, "Witaj Adminie", Toast.LENGTH_SHORT).show()
                    val mainActivity = Intent(this, MainActivity::class.java)
                    startActivity(mainActivity)
                }
            }
        }
    }
}