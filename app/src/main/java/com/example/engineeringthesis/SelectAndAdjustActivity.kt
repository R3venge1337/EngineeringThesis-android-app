package com.example.engineeringthesis

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.model.CategoryTeacher
import com.example.engineeringthesis.model.Teacher
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.utils.GlobalValues.pageNumberSAA
import com.example.engineeringthesis.utils.GlobalValues.sizeSAA
import com.example.engineeringthesis.viewmodel.CategoryTeacherViewModel
import com.example.engineeringthesis.viewmodel.CategoryViewModel
import com.example.engineeringthesis.viewmodel.WordViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_select_and_adjust.*
import javax.inject.Inject

class SelectAndAdjustActivity : DaggerAppCompatActivity() {

    lateinit var categoryViewModel: CategoryViewModel
    lateinit var categoryTeacherViewModel: CategoryTeacherViewModel
    private var wordViewModel: WordViewModel? = null
    private var shotsCounter = 0
    private var correctAnswers = 0

    private lateinit  var firstWordsList : List<Word>
    private lateinit var secondWordsList : List<Word>
    private lateinit var MergedWordsList : ArrayList<Word>
    private lateinit var  wordDropped : Word
    private lateinit var savedCategory: CategoryTeacher
    private var secondCategory: CategoryTeacher? = null
    private lateinit var teacherReturned:String
    private var teacherObject:Teacher? = null
    var categoryNames: List<CategoryTeacher> = java.util.ArrayList()

    @Inject
    lateinit var sharedPreferences: SharedPreferences;
    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor;
    @Inject
    lateinit var jacksonMapper: ObjectMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_and_adjust)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        categoryTeacherViewModel = ViewModelProvider(this).get(CategoryTeacherViewModel::class.java)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        firstWordsList = emptyList()
        secondWordsList = emptyList()
        getDataFromSharedPref()
        getListOfCategories()

        categoryNames = categoryTeacherViewModel.getAllCategoriesTeacherSingle(teacherObject!!.teacherId)

        getSecondCategory()

        firstWordsList = wordViewModel!!.getWordsFromCategory(savedCategory.categoryId.categoryName, pageNumberSAA, sizeSAA)
        secondWordsList = wordViewModel!!.getWordsFromCategory(secondCategory!!.categoryId.categoryName, pageNumberSAA, sizeSAA)

        addAllWordsFromTwoCategories()
        setFieldsProperties()
        SelectAndAdjustWordsHolder.setText(MergedWordsList[15].wordName)
        SelectAndAdjustGameTime.start()
        SelectAndAdjustNumberOfWords.setText(MergedWordsList.size.toString())

    }
    fun onLongClickListener():View.OnLongClickListener {
        return View.OnLongClickListener {
            val clipText = "this is our cliptext"
            val item = ClipData.Item(clipText)
            val mimeType = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeType, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            }else{
                it.startDrag(data, dragShadowBuilder, it, 0)
            }
            it.visibility = View.VISIBLE
            true
        }
    }
    fun dragListener() : View.OnDragListener
    {
        return View.OnDragListener { view, dragEvent ->
            when(dragEvent.action)
            {
                DragEvent.ACTION_DRAG_STARTED -> {
                    true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    view.invalidate()
                    true
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    //Toast.makeText(this,"Action is DragEvent.ACTION_DRAG_LOCATION",Toast.LENGTH_SHORT).show()
                    //Log.d("Drager", "Action is DragEvent.ACTION_DRAG_LOCATION")
                    // val x_cord:Float = view.x
                    //val y_cord:Float = view.y
                    //Toast.makeText(this,"LOCATIONx: $x_cord , y: $y_cord",Toast.LENGTH_SHORT).show()
                    //view.invalidate()
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    view.invalidate()
                    true
                }
                DragEvent.ACTION_DROP -> {
                        wordDropped = MergedWordsList.stream().filter { word -> word.wordName == SelectAndAdjustWordsHolder.text }.findFirst().get()
                        when (view.id) {
                            SelectAndAdjustFirstField.id -> {
                                if (SelectAndAdjustFirstNameField.text == wordDropped.categoryId.categoryName) {
                                    increaseCorrectAnswers()
                                    SelectAndAdjustCorrectAnswers.setText(correctAnswers.toString())
                                   // Toast.makeText(this, "Słowo nalezy do kategorii: ${SelectAndAdjustFirstNameField.text}", Toast.LENGTH_SHORT).show()
                                } else {
                                    increaseShots()
                                    SelectAndAdjustShots.setText(shotsCounter.toString())
                                }
                            }
                            SelectAndAdjustSecondField.id -> {
                                if (SelectAndAdjustSecondNameField.text == wordDropped.categoryId.categoryName) {
                                    increaseCorrectAnswers()
                                    SelectAndAdjustCorrectAnswers.setText(correctAnswers.toString())
                                    //Toast.makeText(this, "Słowo nalezy do kategorii: ${SelectAndAdjustSecondNameField.text}", Toast.LENGTH_SHORT).show()
                                } else {
                                    increaseShots()
                                    SelectAndAdjustShots.setText(shotsCounter.toString())
                                }
                            }
                    }
                    view.invalidate()
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    if(MergedWordsList.size >= -1)
                    {
                        MergedWordsList.remove(wordDropped)
                        var sizeInt = MergedWordsList.size
                        if(sizeInt <= 0 )
                        {
                            SelectAndAdjustWordsHolder.text = ""
                            saveStatisticToShared()
                            val intent = Intent(this, WinViewActivity::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            SelectAndAdjustNumberOfWords.setText(" "+ Integer.toString(sizeInt))
                            SelectAndAdjustWordsHolder.text = MergedWordsList.get(--sizeInt).wordName

                        }
                        view.invalidate()
                    }
                    true
                }
                else -> false
            }
        }
    }
    fun increaseShots()
    {
        shotsCounter++
    }
    fun increaseCorrectAnswers()
    {
        correctAnswers++
    }

    fun setFieldsProperties()
    {
        SelectAndAdjustShots.setText(shotsCounter.toString())
        SelectAndAdjustCorrectAnswers.setText(correctAnswers.toString())
        SelectAndAdjustGameTime.setText("00:00")

        SelectAndAdjustFirstField.visibility =  View.VISIBLE
        SelectAndAdjustFirstField.setBackgroundColor(Color.parseColor("#C8C8C8"))
        SelectAndAdjustFirstNameField.setText(firstWordsList[0].categoryId.categoryName)

        SelectAndAdjustSecondField.visibility =  View.VISIBLE
        SelectAndAdjustSecondField.setBackgroundColor(Color.parseColor("#C8C8C8"))
        SelectAndAdjustSecondNameField.setText(secondWordsList[0].categoryId.categoryName)

        SelectAndAdjustWordsHolder.setOnLongClickListener(onLongClickListener())

        SelectAndAdjustFirstField.setOnDragListener(dragListener())
        SelectAndAdjustSecondField.setOnDragListener(dragListener())

    }

    fun getDataFromSharedPref() {
        savedCategory = jacksonMapper.readValue(sharedPreferences.getString("category_selected", ""), CategoryTeacher::class.java)
    }

    fun saveStatisticToShared()
    {
        var gameTimeObj = jacksonMapper.writeValueAsString(SelectAndAdjustGameTime.text)
        sharedPreferencesEditor.putString("saa_time",gameTimeObj).apply()
        sharedPreferencesEditor.putString("saa_shots",shotsCounter.toString()).apply()
    }

    fun getListOfCategories()
    {
        teacherReturned =  sharedPreferences.getString("teacher_selected", "null").toString()
        teacherObject  = jacksonMapper.readValue(teacherReturned, Teacher::class.java)
        //Toast.makeText(this,Integer.toString(teacherObject.teacherId),Toast.LENGTH_SHORT).show()
    }

    fun addAllWordsFromTwoCategories()
    {
        MergedWordsList = ArrayList()
        MergedWordsList.addAll(firstWordsList)
        MergedWordsList.addAll(secondWordsList)
        MergedWordsList.shuffle()
    }
    fun getSecondCategory()
    {
        secondCategory = categoryNames.random()
        while (secondCategory!!.categoryId.categoryName == savedCategory.categoryId.categoryName)
            secondCategory = categoryNames.random()
    }

}