package com.example.engineeringthesis

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.model.Category
import com.example.engineeringthesis.model.CategoryTeacher
import com.example.engineeringthesis.utils.GlobalValues
import com.example.engineeringthesis.viewmodel.CategoryTeacherViewModel
import com.example.engineeringthesis.viewmodel.CategoryViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_teacher_add_category.*

class TeacherAddCategoryActivity : DaggerAppCompatActivity() {
    lateinit var categoryViewModel: CategoryViewModel
    lateinit var categoryTeacherViewModel: CategoryTeacherViewModel
    var arrayCategories:List<Category>? = null
    var arrayCategoriesTeacher:List<CategoryTeacher>? = null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_add_category)

        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryTeacherViewModel = ViewModelProvider(this).get(CategoryTeacherViewModel::class.java)
        //arrayCategories =  categoryViewModel.allCategories().value
        //arrayCategoriesTeacher = categoryTeacherViewModel.getAllCategoriesByTeacher(GlobalValues.currentTeacher!!.teacherId).value
        teacher_add_category_button.setOnClickListener()
        {
            if(editText_teacher_category.text.isEmpty() || editText_teacher_category.length() == 0)
            {
                editText_teacher_category.setError("Pole nazwa jest puste")
            }
            else if(editText_teacher_category.text.isNotEmpty())
            {
                val editTextValue = editText_teacher_category.text.toString()
                val cat = Category(categoryName = editTextValue, languageId = GlobalValues.currentTeacher!!.languageTeacherId, isNew = true, isAccepted = false)
                categoryViewModel.saveCategory(cat)
                val retrivedCat: Category = categoryViewModel.getCategory(editTextValue)
                val catTeacher  = CategoryTeacher(retrivedCat, GlobalValues.currentTeacher!!, true, false)
                categoryTeacherViewModel.saveCategoryTeacher(catTeacher)
                Toast.makeText(this, "kategoria zostala dodana", Toast.LENGTH_SHORT).show()
            }
        }
    }
}