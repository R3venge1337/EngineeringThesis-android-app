package com.example.engineeringthesis

import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.engineeringthesis.model.Teacher
import com.example.engineeringthesis.viewmodel.TeacherViewModel
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test

class TeacherInstrumentedTest {

    private lateinit var teacherViewModel: TeacherViewModel
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.engineeringthesis", appContext.packageName)
    }

    @Test
    fun getTeacher() {
        teacherViewModel = TeacherViewModel(ApplicationProvider.getApplicationContext())
        var teach:Teacher = teacherViewModel.getTeacherWithAccountDetails("teacherTest")
        MatcherAssert.assertThat(teach, Matchers.notNullValue());
    }
    @Test
    fun getTeacherWithWrongAccountName() {
        teacherViewModel = TeacherViewModel(ApplicationProvider.getApplicationContext())
        var teach:Teacher = teacherViewModel.getTeacherWithAccountDetails("teacherTe")
        MatcherAssert.assertThat(teach, Matchers.nullValue());
    }
}