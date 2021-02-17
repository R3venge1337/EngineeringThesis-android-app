package com.example.engineeringthesis

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.model.Account
import com.example.engineeringthesis.model.Child
import com.example.engineeringthesis.utils.GlobalValues
import com.example.engineeringthesis.viewmodel.AccountViewModel
import com.example.engineeringthesis.viewmodel.ChildViewModel
import com.example.engineeringthesis.viewmodel.RoleViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_child_my_account.*
import kotlinx.android.synthetic.main.activity_child_register.editText_child_city
import kotlinx.android.synthetic.main.activity_child_register.editText_child_email
import kotlinx.android.synthetic.main.activity_child_register.editText_child_name
import kotlinx.android.synthetic.main.activity_child_register.editText_child_password
import kotlinx.android.synthetic.main.activity_child_register.editText_child_surname
import kotlinx.android.synthetic.main.activity_child_register.editText_child_username
import kotlinx.android.synthetic.main.activity_child_register.editText_child_yearBirth
import java.time.LocalDateTime

class ChildMyAccountActivity : DaggerAppCompatActivity() {

    private var childViewModel: ChildViewModel? = null
    private var accountViewModel: AccountViewModel? = null
    private var roleViewModel: RoleViewModel? = null
    private var  childAccount : Account? = null
    private var childDetails : Child? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_my_account)
        childViewModel = ViewModelProvider(this).get(ChildViewModel::class.java)
        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        roleViewModel = ViewModelProvider(this).get(RoleViewModel::class.java)
        insertDataToFields()
        update_child_Button.setOnClickListener()
        {
            val childName = editText_child_name.text.toString()
            val childSurname = editText_child_surname.text.toString()
            val childYearBirth = editText_child_yearBirth.text.toString()
            val childCity = editText_child_city.text.toString()
            val childUserName =  editText_child_username.text.toString()
            val childPassword =  editText_child_password.text.toString()
            val childEmail = editText_child_email.text.toString()

            val accountDetailsUpdated = Account(childDetails!!.accountChildId.accountId,childUserName,childPassword, LocalDateTime.now(),childEmail,childAccount!!.role)
            accountViewModel!!.updateAccount(childDetails!!.accountChildId.accountId,accountDetailsUpdated)

            val childObj = Child(childDetails!!.childId,childName,childSurname,childYearBirth.toShort(),childCity,accountDetailsUpdated,null)
            Log.i("childObj",childObj.toString())
            // Toast.makeText(this,acc.toString(),Toast.LENGTH_LONG).show()
            childViewModel!!.updateChild(childObj,childDetails!!.childId)
            Toast.makeText(this,"Dziecko zostało  zaaktualizowane", Toast.LENGTH_SHORT).show()
        }
    }

    fun insertDataToFields()
    {
        childAccount = GlobalValues.currentAccount
        childDetails = GlobalValues.currentChild
        editText_child_name.setText(childDetails!!.childName)
        editText_child_surname.setText(childDetails!!.childSurname)
        editText_child_yearBirth.setText(childDetails!!.childYearBirth.toString())
        editText_child_city.setText(childDetails!!.childCity)

        editText_child_username.setText(childAccount!!.accountName)
        editText_child_password.setText(null)
        editText_child_email.setText(childAccount!!.accountEmail)

    }
}