package com.example.engineeringthesis

import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_teacher_add_resources.*

class TeacherAddResourcesActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_add_resources)

        teacher_add_category_button.setOnClickListener()
        {
            val categoryAddActivity = Intent(this, TeacherAddCategoryActivity::class.java)
            startActivity(categoryAddActivity)
        }
        teacher_add_word_button.setOnClickListener()
        {
            val wordAddActivity = Intent(this, TeacherAddWordActivity::class.java)
            startActivity(wordAddActivity)
        }
        teacher_add_image_button.setOnClickListener()
        {
            val wordImageActivity = Intent(this, TeacherAddImageActivity::class.java)
            startActivity(wordImageActivity)
        }
        teacher_add_audio_button.setOnClickListener()
        {
            val wordAudioActivity = Intent(this, TeacherAddAudioActivity::class.java)
            startActivity(wordAudioActivity)
        }

    }
}