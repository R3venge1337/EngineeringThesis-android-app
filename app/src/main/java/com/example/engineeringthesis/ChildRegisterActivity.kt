package com.example.engineeringthesis

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.model.Account
import com.example.engineeringthesis.model.Child
import com.example.engineeringthesis.viewmodel.AccountViewModel
import com.example.engineeringthesis.viewmodel.ChildViewModel
import com.example.engineeringthesis.viewmodel.RoleViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_child_register.*
import java.time.LocalDateTime

class ChildRegisterActivity : DaggerAppCompatActivity() {
    private var childViewModel: ChildViewModel? = null
    private var accountViewModel: AccountViewModel? = null
    private var roleViewModel: RoleViewModel? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_register)
        childViewModel = ViewModelProvider(this).get(ChildViewModel::class.java)
        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        roleViewModel = ViewModelProvider(this).get(RoleViewModel::class.java)
        val role = roleViewModel!!.getRoleByName("ROLE_"+"dziecko")
        register_child_Button.setOnClickListener {
            val childName = editText_child_name.text.toString()
            val childSurname = editText_child_surname.text.toString()
            val childYearBirth = editText_child_yearBirth.text.toString()
            val childCity = editText_child_city.text.toString()
            val childUserName =  editText_child_username.text.toString()
            val childPassword =  editText_child_password.text.toString()
            val childEmail = editText_child_email.text.toString()


            val account = Account(childUserName,childPassword, LocalDateTime.now(),childEmail,role)
            accountViewModel!!.saveAccount(account)

           val acc = accountViewModel!!.getAccountByName(childUserName)
            Log.i("acc",acc.toString())

            val childObj = Child(childName,childSurname,childYearBirth.toShort(),childCity,acc)
            Log.i("childObj",childObj.toString())
           // Toast.makeText(this,acc.toString(),Toast.LENGTH_LONG).show()
            childViewModel!!.saveChild(childObj)
            //Toast.makeText(this,childObj.toString(),Toast.LENGTH_LONG).show()


        }

    }
}