package com.example.engineeringthesis

import android.content.*
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.database.Retrofit.OkhttpClient
import com.example.engineeringthesis.database.Retrofit.PicassoClient
import com.example.engineeringthesis.model.CategoryTeacher
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.utils.GlobalValues.pageNumberDAD
import com.example.engineeringthesis.utils.GlobalValues.sizeDAD
import com.example.engineeringthesis.viewmodel.WordViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_drag_and_drop.*
import javax.inject.Inject

class DragAndDropActivity : DaggerAppCompatActivity() {
    private var wordViewModel: WordViewModel? = null
    private var shotsCounter = 0
    private lateinit var wordsList : List<Word>
    private lateinit var selectedWord : Word
    private lateinit var  picasso : Picasso
    private lateinit var savedCategory: CategoryTeacher
    private  var fieldWithList = arrayListOf(false, false, false, false)
    private  var endGame:Boolean = false

    @Inject
    lateinit var sharedPreferences: SharedPreferences;
    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor;
    @Inject
    lateinit var jacksonMapper: ObjectMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_and_drop)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        getDataFromSharedPref()
        wordsList =  wordViewModel!!.getWordsFromCategory(savedCategory.categoryId.categoryName, pageNumberDAD, sizeDAD)
        picasso =  PicassoClient.getPiccassoInstance(applicationContext, OkhttpClient)
        getWordFromList()
        setFields()
        dragAndDropGameTime.start()
        setImagesIntoFields()
        setAmountOfWrongAnswers()
        dragAndDropFirstItem.setOnLongClickListener(onLongClickListener())
        dragAndDropSecondItem.setOnLongClickListener(onLongClickListener())
        dragAndDropThirdItem.setOnLongClickListener(onLongClickListener())
        dragAndDropFourItem.setOnLongClickListener(onLongClickListener())

        dragAndDropFirstField.setOnDragListener(dragListener())
        dragAndDropSecondField.setOnDragListener(dragListener())
        dragAndDropThirdField.setOnDragListener(dragListener())
        dragAndDropFourField.setOnDragListener(dragListener())


    }
    override fun onBackPressed() {
        val alertDialogBuilder = android.app.AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Czy chcesz wyjść z gry?")
                .setCancelable(false)
                .setPositiveButton("Tak", DialogInterface.OnClickListener { dialog, id ->
                    super.onBackPressed()
                })
                .setNegativeButton("Nie", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        val alert: android.app.AlertDialog? = alertDialogBuilder.create()
        alert!!.show()
    }

    fun getWordFromList()
    {
        selectedWord = wordsList.random()
    }

    fun increaseShots()
    {
        shotsCounter++
    }

    fun setFields()
    {
        dragAndDropFirstField.visibility =  View.VISIBLE
        dragAndDropFirstField.setBackgroundColor(Color.parseColor("#C8C8C8"))
        dragAndDropFirstField.setTag(wordsList[0].wordName)
        dragAndDropFirstNameField.setText(wordsList[0].wordName)
        dragAndDropFirstAudio

        dragAndDropSecondField.visibility = View.VISIBLE
        dragAndDropSecondField.setBackgroundColor(Color.parseColor("#C8C8C8"))
        dragAndDropSecondField.setTag(wordsList[1].wordName)
        dragAndDropSecondNameField.setText(wordsList[1].wordName)
        dragAndDropSecondAudio

        dragAndDropThirdField.visibility = View.VISIBLE
        dragAndDropThirdField.setBackgroundColor(Color.parseColor("#C8C8C8"))
        dragAndDropThirdField.setTag(wordsList[2].wordName)
        dragAndDropThirdNameField.setText(wordsList[2].wordName)
        dragAndDropThirdAudio

        dragAndDropFourField.visibility = View.VISIBLE
        dragAndDropFourField.setBackgroundColor(Color.parseColor("#C8C8C8"))
        dragAndDropFourField.setTag(wordsList[3].wordName)
        dragAndDropFourNameField.setText(wordsList[3].wordName)
        dragAndDropFourAudio

        dragAndDropFirstItem.setTag(wordsList[0].wordName)
        dragAndDropSecondItem.setTag(wordsList[1].wordName)
        dragAndDropThirdItem.setTag(wordsList[2].wordName)
        dragAndDropFourItem.setTag(wordsList[3].wordName)
    }

    fun setAmountOfWrongAnswers()
    {
        dragAndDropShots.setText(shotsCounter.toString())
    }
    fun setImagesIntoFields()
    {
        picasso.load(wordsList[0].imageId!!.imageDownloadUri)
                .resize(75,75)
                .into(dragAndDropFirstItem)

        picasso.load(wordsList[1].imageId!!.imageDownloadUri)
                .resize(75,75)
                .into(dragAndDropSecondItem)

        dragAndDropSecondItem.setOnDragListener(dragListener())
        picasso.load(wordsList[2].imageId!!.imageDownloadUri)
                .resize(75,75)
                .into(dragAndDropThirdItem)

        dragAndDropThirdItem.setOnDragListener(dragListener())
        picasso.load(wordsList[3].imageId!!.imageDownloadUri)
                .resize(75,75)
                .into(dragAndDropFourItem)

    }
    fun onLongClickListener():View.OnLongClickListener
    {
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
            var draggedItem =  dragEvent.localState as ImageView
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
                    //view.invalidate()
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    view.invalidate()
                    true
                }
                DragEvent.ACTION_DROP -> {
                    when (view.id) {
                        dragAndDropFirstField.id -> {
                            if (view.tag == draggedItem.tag) {
                                dragAndDropFirstField.visibility = View.VISIBLE
                                dragAndDropFirstField.setImageDrawable(draggedItem.drawable)
                                draggedItem.visibility = View.GONE
                                fieldWithList[0] = true
                                isGameEnd()
                            } else {
                                draggedItem.visibility = View.VISIBLE
                                increaseShots()
                                setAmountOfWrongAnswers()
                            }
                        }
                        dragAndDropSecondField.id -> {
                            if (view.tag == draggedItem.tag) {
                                dragAndDropSecondField.visibility = View.VISIBLE
                                dragAndDropSecondField.setImageDrawable(draggedItem.drawable)
                                draggedItem.visibility = View.GONE
                                fieldWithList[1] = true
                                isGameEnd()
                            } else {
                                draggedItem.visibility = View.VISIBLE
                                increaseShots()
                                setAmountOfWrongAnswers()
                            }
                        }
                        dragAndDropThirdField.id -> {
                            if (view.tag == draggedItem.tag) {
                                dragAndDropThirdField.visibility = View.VISIBLE
                                dragAndDropThirdField.setImageDrawable(draggedItem.drawable)
                                draggedItem.visibility = View.GONE
                                fieldWithList[2] = true
                                isGameEnd()
                            } else {
                                draggedItem.visibility = View.VISIBLE
                                increaseShots()
                                setAmountOfWrongAnswers()
                            }
                        }
                        dragAndDropFourField.id -> {
                            if (view.tag == draggedItem.tag) {
                                dragAndDropFourField.visibility = View.VISIBLE
                                dragAndDropFourField.setImageDrawable(draggedItem.drawable)
                                draggedItem.visibility = View.GONE
                                fieldWithList[3] = true
                                isGameEnd()
                            } else {
                                draggedItem.visibility = View.VISIBLE
                                increaseShots()
                                setAmountOfWrongAnswers()
                            }
                        }
                    }
                    view.invalidate()
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {

                    true
                }
                else -> false
            }

        }
    }
    fun getDataFromSharedPref() {
        savedCategory = jacksonMapper.readValue(sharedPreferences.getString("category_selected", ""), CategoryTeacher::class.java)
    }

    fun saveStatisticToShared()
    {
        var gameTimeObj = jacksonMapper.writeValueAsString(dragAndDropGameTime.text)
        sharedPreferencesEditor.putString("dad_time",gameTimeObj).apply()
        sharedPreferencesEditor.putString("dad_shots",shotsCounter.toString()).apply()
    }

    fun isGameEnd()
    {
        if(fieldWithList[0] && fieldWithList[1] && fieldWithList[2] && fieldWithList[3])
        {
            endGame = true
            if(endGame) {
                val intent = Intent(this, WinViewActivity::class.java)
                startActivity(intent)
                saveStatisticToShared()
            }
        }
    }
}