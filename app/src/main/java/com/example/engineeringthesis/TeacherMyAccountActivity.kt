package com.example.engineeringthesis

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.model.Account
import com.example.engineeringthesis.model.Language
import com.example.engineeringthesis.model.Teacher
import com.example.engineeringthesis.utils.GlobalValues
import com.example.engineeringthesis.viewmodel.AccountViewModel
import com.example.engineeringthesis.viewmodel.LanguageViewModel
import com.example.engineeringthesis.viewmodel.RoleViewModel
import com.example.engineeringthesis.viewmodel.TeacherViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_teacher_my_account_actvity.*
import java.time.LocalDateTime

class TeacherMyAccountActivity : DaggerAppCompatActivity() {
    private var teacherViewModel: TeacherViewModel? = null
    private var accountViewModel: AccountViewModel? = null
    private var roleViewModel: RoleViewModel? = null
    private var languageViewModel: LanguageViewModel? = null
    private  lateinit var getLang : Language
    private  var  teacherAccount : Account? = null
    private  var  teacherDetails : Teacher? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_my_account_actvity)

        teacherViewModel = ViewModelProvider(this).get(TeacherViewModel::class.java)
        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        roleViewModel = ViewModelProvider(this).get(RoleViewModel::class.java)
        languageViewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)
        //langList = languageViewModel!!.allLanguages()
        insertDataToFields()
        update_teacher_Button.setOnClickListener {

            val teacherName = editText_teacher_name.text.toString()
            val teacherSurname = editText_teacher_surname.text.toString()
            val teacherYearBirth = editText_teacher_yearBirth.text.toString()
            val teacherCity = editText_teacher_city.text.toString()
            val teacherProfession = editText_teacher_profession.text.toString()
            val teacherLanguage = editText_teacher_language.text.toString()
            val teacherUserName =  editText_teacher_username.text.toString()
            val teacherPassword =  editText_teacher_password.text.toString()
            val teacherEmail = editText_teacher_email.text.toString()

            val accountDetailsUpdated = Account(teacherDetails!!.accountTeacherId.accountId,teacherUserName,teacherPassword, LocalDateTime.now(),teacherEmail,teacherAccount!!.role)
            accountViewModel!!.updateAccount(teacherDetails!!.accountTeacherId.accountId,accountDetailsUpdated)

            val retrievedLanguage = languageViewModel!!.getLanguageByName(teacherLanguage)

            val teacherObj = Teacher(teacherDetails!!.teacherId,teacherName,teacherSurname,teacherYearBirth.toShort(),teacherCity
                    ,teacherProfession,retrievedLanguage!!,accountDetailsUpdated)
            teacherViewModel!!.updateTeacher(teacherObj,teacherDetails!!.teacherId)
            Toast.makeText(this,"Nauczyciel zostal zaktualizowany",Toast.LENGTH_SHORT).show()
        }
    }
    fun insertDataToFields()
    {
        teacherAccount = GlobalValues.currentAccount
        teacherDetails = GlobalValues.currentTeacher
        editText_teacher_name.setText(teacherDetails!!.teacherName)
        editText_teacher_surname.setText(teacherDetails!!.teacherSurname)
        editText_teacher_yearBirth.setText(teacherDetails!!.teacherYearBirth.toString())
        editText_teacher_city.setText(teacherDetails!!.teacherCity)
        editText_teacher_profession.setText(teacherDetails!!.teacherProfession)
        editText_teacher_language.setText(teacherDetails!!.languageTeacherId.languageName)
        editText_teacher_username.setText(teacherAccount!!.accountName)
        editText_teacher_password.setText(null)
        editText_teacher_email.setText(teacherAccount!!.accountEmail)
    }
}