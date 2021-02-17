package com.example.engineeringthesis

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.model.Category
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.utils.GlobalValues
import com.example.engineeringthesis.viewmodel.*
import kotlinx.android.synthetic.main.activity_teacher_add_audio.*
import kotlinx.android.synthetic.main.activity_teacher_add_image.*
import kotlinx.android.synthetic.main.activity_teacher_add_image.teacher_spinner_category_list
import kotlinx.android.synthetic.main.activity_teacher_add_image.teacher_spinner_word_list
import java.io.File
import java.io.IOException
import java.util.*

class TeacherAddAudioActivity : AppCompatActivity() {
    private val PICK_AUDIO = 2
    lateinit var wordViewModel: WordViewModel
    lateinit var categoryViewModel: CategoryViewModel
    lateinit var categoryTeacherViewModel: CategoryTeacherViewModel
    lateinit var audioViewModel: AudioViewModel
    var mutableArrayCategories: ArrayList<String>? = ArrayList()
    var mutableArrayWords: ArrayList<String>? = ArrayList()
    lateinit var dialog: Dialog
    lateinit var editTextSpinner: EditText
    lateinit var listViewSpinner: ListView
    var selectedCategorySpinner:String = ""
    var selectedWordSpinner:String = ""
    lateinit var selectedCategory: Category
    lateinit var selectedWord: Word
    lateinit var selectedImage: File
    var audioUri: Uri? = null
    var bitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_add_audio)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryTeacherViewModel = ViewModelProvider(this).get(CategoryTeacherViewModel::class.java)
        audioViewModel = ViewModelProvider(this).get(AudioViewModel::class.java)
        // Category get
        categoryTeacherViewModel.getAllCategoriesByTeacher(GlobalValues.currentTeacher!!.teacherId).observe(this,
                { categories ->
                    categories.forEach {
                        //Toast.makeText(this@TeacherAddWordActivity, it.toString(), Toast.LENGTH_SHORT).show()
                        mutableArrayCategories!!.add(it.categoryId.categoryName)
                    }
                })

        teacher_spinner_category_list.setOnClickListener {
            dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_category_searchable_spinner)
            dialog.window!!.setLayout(650, 800)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            val adap = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableArrayCategories!!)
            listViewSpinner = dialog.findViewById(R.id.dialog_listview_spinner)
            editTextSpinner = dialog.findViewById(R.id.dialog_edittext_spinner)
            listViewSpinner.adapter = adap
            editTextSpinner.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    adap.filter.filter(s)
                }
            })
            listViewSpinner.onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(adapter: AdapterView<*>?, v: View?, position: Int, id: Long) {
                    teacher_spinner_category_list.text = adap.getItem(position)
                    selectedCategorySpinner = teacher_spinner_category_list.text.toString()
                    Toast.makeText(this@TeacherAddAudioActivity, "Kategoria:  $selectedCategorySpinner", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
        }
        teacher_spinner_word_list.setOnClickListener {
            mutableArrayWords!!.clear()
            wordViewModel.getWordsFromCategory(selectedCategorySpinner, 0, Integer.MAX_VALUE).iterator()
                    .forEach {mutableArrayWords!!.add(it.wordName)}
            dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_word_searchable_spinner)
            dialog.window!!.setLayout(650, 800)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            val adap = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableArrayWords!!)
            listViewSpinner = dialog.findViewById(R.id.dialog_word_listview_spinner)
            editTextSpinner = dialog.findViewById(R.id.dialog_word_edittext_spinner)
            listViewSpinner.adapter = adap
            editTextSpinner.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    adap.filter.filter(s)
                }
            })
            listViewSpinner.onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(adapter: AdapterView<*>?, v: View?, position: Int, id: Long) {
                    teacher_spinner_word_list.text = adap.getItem(position)
                    selectedWordSpinner = teacher_spinner_word_list.text.toString()
                    Toast.makeText(this@TeacherAddAudioActivity, "Słowo:  $selectedWordSpinner", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
        }
        teacher_audio_select_button.setOnClickListener { //create the intent for ImageGallery
            val intent = Intent()
            intent.type = "audio/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Wybierz plik audio"), PICK_AUDIO)
        }
        teacher_add_audio_button.setOnClickListener{

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_AUDIO) {
            audioUri = data!!.data!!

            //Toast.makeText(this,"To jest imageUri: "+imageUri.toString(),Toast.LENGTH_SHORT).show()
            //Toast.makeText(this,"T o jest imageURiPath: "+imageUri!!.path,Toast.LENGTH_SHORT).show()

            if (Build.VERSION.SDK_INT >= 29) {
                val source: ImageDecoder.Source = ImageDecoder.createSource(applicationContext.contentResolver, audioUri!!)
                try {
                    bitmap = ImageDecoder.decodeBitmap(source)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, audioUri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            // Toast.makeText(this, "to jest bitmap: " + bitmap.toString(), Toast.LENGTH_SHORT).show()
            teacher_selected_audio.show()
        }
    }
}