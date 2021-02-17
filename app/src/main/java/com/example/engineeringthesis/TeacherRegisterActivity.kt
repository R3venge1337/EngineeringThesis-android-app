package com.example.engineeringthesis

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.model.Account
import com.example.engineeringthesis.model.Language
import com.example.engineeringthesis.model.Role
import com.example.engineeringthesis.model.Teacher
import com.example.engineeringthesis.viewmodel.AccountViewModel
import com.example.engineeringthesis.viewmodel.LanguageViewModel
import com.example.engineeringthesis.viewmodel.RoleViewModel
import com.example.engineeringthesis.viewmodel.TeacherViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_teacher_register_actvity.*
import java.time.LocalDateTime

class TeacherRegisterActivity : DaggerAppCompatActivity() {
    private var teacherViewModel: TeacherViewModel? = null
    private var accountViewModel:AccountViewModel? = null
    private var roleViewModel:RoleViewModel? = null
    private var languageViewModel:LanguageViewModel? = null
    private var  langList: LiveData<List<Language>>? = null
    private  lateinit var getLang : Language
    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_register_actvity)

        teacherViewModel = ViewModelProvider(this).get(TeacherViewModel::class.java)
        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        roleViewModel = ViewModelProvider(this).get(RoleViewModel::class.java)
        languageViewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)
        langList = languageViewModel!!.allLanguages()



        register_teacher_Button.setOnClickListener {
            val teacherName = editText_teacher_name.text.toString()
            val teacherSurname = editText_teacher_surname.text.toString()
            val teacherYearBirth = editText_teacher_yearBirth.text.toString()
            val teacherCity = editText_teacher_city.text.toString()
            val teacherProfession = editText_teacher_profession.text.toString()
            val teacherLanguage = editText_teacher_language.text.toString()
            val teacherUserName =  editText_teacher_username.text.toString()
            val teacherPassword =  editText_teacher_password.text.toString()
            val teacherEmail = editText_teacher_email.text.toString()


            val role :Role = roleViewModel!!.getRoleByName("ROLE_"+"nauczyciel")
            val account = Account(teacherUserName,teacherPassword, LocalDateTime.now(),teacherEmail,role)
            accountViewModel!!.saveAccount(account)

           val lang : Language?  = langList?.value?.stream()?.filter { ls -> ls.equals(teacherLanguage)}?.findAny()?.get()
            if(lang == null)
            {
                val saveNew = Language(0, teacherLanguage, LocalDateTime.now(), true, true)
                languageViewModel!!.saveLanguage(saveNew)
                getLang = languageViewModel!!.getLanguageByName(teacherLanguage)!!
            }
            else
            {
                getLang  = languageViewModel!!.getLanguageByName(teacherLanguage)!!
            }

            val acc = accountViewModel!!.getAccountByName(teacherUserName)

            val teacherObj = Teacher(teacherName,teacherSurname,teacherYearBirth.toShort(),teacherCity,
                    teacherProfession,getLang,acc)
            Toast.makeText(this,teacherObj.toString(), Toast.LENGTH_LONG).show()
            teacherViewModel!!.saveTeacher(teacherObj)




        }
    }



}