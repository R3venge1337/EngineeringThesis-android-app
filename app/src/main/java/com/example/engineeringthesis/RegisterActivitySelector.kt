package com.example.engineeringthesis

import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_register_selector.*

class RegisterActivitySelector : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_selector)

        registerChildTextView.setOnClickListener {

            val childRegisterActivity = Intent(this, ChildRegisterActivity::class.java)
            startActivity(childRegisterActivity)
        }

        registerTeacherTextView.setOnClickListener {
            val teacherRegisterActivity = Intent(this, TeacherRegisterActivity::class.java)
            startActivity(teacherRegisterActivity)
        }

    }
}