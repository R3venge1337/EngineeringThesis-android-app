package com.example.engineeringthesis

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.database.Retrofit.OkhttpClient
import com.example.engineeringthesis.database.Retrofit.PicassoClient
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.viewmodel.WordViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_drag_and_drop.*

class DragAndDropActivity : DaggerAppCompatActivity() {
    private var wordViewModel: WordViewModel? = null
    private var shotsCounter = 0
    private lateinit var wordsList : List<Word>
    private lateinit var selectedWord : Word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_and_drop)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordsList =  wordViewModel!!.getWordsFromCategory("Sport", 0, 4)
        getWordFromList()
        val picasso = PicassoClient.getPiccassoInstance(applicationContext, OkhttpClient)
        dragAndDropShots.setText(shotsCounter.toString())
        dragAndDropFirstField.visibility =  View.VISIBLE
        dragAndDropFirstField.setBackgroundColor(Color.parseColor("#C8C8C8"))
        dragAndDropFirstNameField.setText(wordsList[0].wordName)
        dragAndDropFirstAudio

        dragAndDropSecondField.visibility = View.VISIBLE
        dragAndDropSecondField.setBackgroundColor(Color.parseColor("#C8C8C8"))
        dragAndDropSecondNameField.setText(wordsList[1].wordName)
        dragAndDropSecondAudio

        dragAndDropThirdField.visibility = View.VISIBLE
        dragAndDropThirdField.setBackgroundColor(Color.parseColor("#C8C8C8"))
        dragAndDropThirdNameField.setText(wordsList[2].wordName)
        dragAndDropThirdAudio

        dragAndDropFourField.visibility = View.VISIBLE
        dragAndDropFourField.setBackgroundColor(Color.parseColor("#C8C8C8"))
        dragAndDropFourNameField.setText(wordsList[3].wordName)
        dragAndDropFourAudio

        picasso.load(wordsList[0].imageId.imageDownloadUri)
                .into(dragAndDropFirstItem)
        dragAndDropFirstItem.setOnTouchListener(touchListener())
        dragAndDropFirstItem.setOnDragListener(dragListener())
        picasso.load(wordsList[1].imageId.imageDownloadUri)
                .into(dragAndDropSecondItem)
        dragAndDropSecondItem.setOnTouchListener(touchListener())
        dragAndDropSecondItem.setOnDragListener(dragListener())
        picasso.load(wordsList[2].imageId.imageDownloadUri)
                .into(dragAndDropThirdItem)
        dragAndDropThirdItem.setOnTouchListener(touchListener())
        dragAndDropThirdItem.setOnDragListener(dragListener())
        picasso.load(wordsList[3].imageId.imageDownloadUri)
                .into(dragAndDropFourItem)
        dragAndDropFourItem.setOnTouchListener(touchListener())
        dragAndDropFourItem.setOnDragListener(dragListener())
    }
    override fun onBackPressed() {
        val alertDialogBuilder = android.app.AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Czy chcesz wyjść z gry?")
                .setCancelable(false)
                .setPositiveButton("Tak", DialogInterface.OnClickListener { dialog, id ->
                    val selectGame = Intent(this, GameActivity::class.java)
                    startActivity(selectGame)
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
    fun touchListener() :View.OnTouchListener  {
       return   View.OnTouchListener(function = { view, motionEvent ->
           if (motionEvent.action == MotionEvent.ACTION_MOVE) {

               view.y = motionEvent.rawY - view.height / 2
               view.x = motionEvent.rawX - view.width / 2
           }
           true
       })
    }

    fun dragListener() : View.OnDragListener
    {
        return View.OnDragListener { view, dragEvent ->
            when(dragEvent.action)
            {
                DragEvent.ACTION_DRAG_STARTED -> {
                    Log.d("Drager", "Action is DragEvent.ACTION_DRAG_STARTED")
                    true
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    Log.d("Drager", "Action is DragEvent.ACTION_DRAG_LOCATION")
                    true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    Log.d("Drager", "Action is DragEvent.ACTION_DRAG_ENTERED")
                    true
                }
                DragEvent.ACTION_DROP -> {
                    Log.d("Drager", "Action is DragEvent.ACTION_DRAG_DROP")
                    true
                }
                else -> false
            }

        }
    }
}