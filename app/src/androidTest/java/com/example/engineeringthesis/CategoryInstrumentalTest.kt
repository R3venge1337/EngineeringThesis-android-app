package com.example.engineeringthesis

import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.engineeringthesis.model.Category
import com.example.engineeringthesis.model.CategoryTeacher
import com.example.engineeringthesis.model.Language
import com.example.engineeringthesis.model.Teacher
import com.example.engineeringthesis.viewmodel.CategoryTeacherViewModel
import com.example.engineeringthesis.viewmodel.CategoryViewModel
import com.example.engineeringthesis.viewmodel.LanguageViewModel
import com.example.engineeringthesis.viewmodel.TeacherViewModel
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test

class CategoryInstrumentalTest {
    private lateinit var catViewModel: CategoryViewModel
    private lateinit var langViewModel:LanguageViewModel
    private lateinit var categoryTeacherViewModel:CategoryTeacherViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.engineeringthesis", appContext.packageName)
    }

    @Test
    fun shouldGetListCategories() {
        catViewModel = CategoryViewModel(ApplicationProvider.getApplicationContext())
        var cat:List<Category>? = catViewModel.allCategories().value

        if (cat != null) {
            MatcherAssert.assertThat(cat, Matchers.notNullValue());
        }
        MatcherAssert.assertThat(cat, Matchers.nullValue());
    }
    @Test
    fun shouldGetCategory()
    {
        catViewModel = CategoryViewModel(ApplicationProvider.getApplicationContext())
        var cat: Category? = catViewModel.getCategory("Sport")

        if (cat != null) {
            MatcherAssert.assertThat(cat, Matchers.notNullValue());
        }
        MatcherAssert.assertThat(cat, Matchers.nullValue())
    }

    @Test
    fun shouldSaveCategory()
    {
        langViewModel = LanguageViewModel(ApplicationProvider.getApplicationContext())
        var lang:Language? = langViewModel.getLanguageByName("Angielski")
        var catbject = Category(0,"Esport",lang!!,false,false)
        catViewModel = CategoryViewModel(ApplicationProvider.getApplicationContext())
        catViewModel.saveCategory(catbject)
    }
    @Test
    fun shouldGetAllCategoriesByTeacher(teacherId:Int)
    {
        categoryTeacherViewModel = CategoryTeacherViewModel(ApplicationProvider.getApplicationContext())
        var categoriesTeacher:List<CategoryTeacher>? = categoryTeacherViewModel.getAllCategoriesByTeacher(1).value
        MatcherAssert.assertThat(categoriesTeacher, Matchers.notNullValue())
    }
    @Test
    fun saveCategoryTeacher(categoryTeacher: CategoryTeacher)
    {

        catViewModel = CategoryViewModel(ApplicationProvider.getApplicationContext())
        var cat: Category? = catViewModel.getCategory("Sport")
        teacherViewModel = TeacherViewModel(ApplicationProvider.getApplicationContext())
        var teach: Teacher = teacherViewModel.getTeacherWithAccountDetails("teacherTest")
        var categoryTeacherObj = CategoryTeacher(0,cat!!,teach,false,false)
        categoryTeacherViewModel = CategoryTeacherViewModel(ApplicationProvider.getApplicationContext())
        categoryTeacherViewModel.saveCategoryTeacher(categoryTeacherObj)
    }
}
