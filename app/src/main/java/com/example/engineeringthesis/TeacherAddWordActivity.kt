package com.example.engineeringthesis

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.model.Category
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.utils.GlobalValues.currentTeacher
import com.example.engineeringthesis.viewmodel.CategoryTeacherViewModel
import com.example.engineeringthesis.viewmodel.CategoryViewModel
import com.example.engineeringthesis.viewmodel.WordViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_teacher_add_word.*
import java.util.*


class TeacherAddWordActivity  : DaggerAppCompatActivity() {
    lateinit var wordViewModel: WordViewModel
    lateinit var categoryViewModel: CategoryViewModel
    lateinit var categoryTeacherViewModel: CategoryTeacherViewModel
    var mutableArrayOs: ArrayList<String>? = ArrayList()
    lateinit var dialog: Dialog
    lateinit var editTextSpinner:EditText
    lateinit var listViewSpinner:ListView
    lateinit var selectedCategorySpinner:String
    lateinit var selectedCategory:Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_add_word)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryTeacherViewModel = ViewModelProvider(this).get(CategoryTeacherViewModel::class.java)

         categoryTeacherViewModel.getAllCategoriesByTeacher(currentTeacher!!.teacherId).observe(this,
                { categories -> categories.forEach {
                        //Toast.makeText(this@TeacherAddWordActivity, it.toString(), Toast.LENGTH_SHORT).show()
                       mutableArrayOs!!.add(it.categoryId.categoryName)
                    }
                })

            teacher_spinner_category_list.setOnClickListener {
                dialog = Dialog(this@TeacherAddWordActivity)
                dialog.setContentView(R.layout.dialog_category_searchable_spinner)
                dialog.window!!.setLayout(650,800)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

                val adap = ArrayAdapter(this,android.R.layout.simple_list_item_1,mutableArrayOs!!)
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
                listViewSpinner.setOnItemClickListener(object:  AdapterView.OnItemClickListener {
                    override fun onItemClick(adapter: AdapterView<*>?, v: View?, position: Int, id: Long) {
                        teacher_spinner_category_list.setText(adap.getItem(position))
                        selectedCategorySpinner = teacher_spinner_category_list.text.toString()
                        //Toast.makeText(this@TeacherAddWordActivity, "Kategoria:  $selectedCategorySpinner", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                })
            }
            teacher_add_word_button.setOnClickListener()
            {
                selectedCategory = categoryViewModel.getCategory(selectedCategorySpinner)
                //Toast.makeText(this@TeacherAddWordActivity, "Kategoria pobrana:  $selectedCategory", Toast.LENGTH_SHORT).show()
                wordViewModel.saveWord(Word(0,editText_teacher_word.text.toString(),null,selectedCategory,
                currentTeacher!!.languageTeacherId,null,null))
                Toast.makeText(this@TeacherAddWordActivity, "Słowo zostało dodane do kategorii:  $selectedCategorySpinner", Toast.LENGTH_SHORT).show()
            }
        }
    }
