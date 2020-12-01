package com.example.engineeringthesis

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.database.Retrofit.OkhttpClient
import com.example.engineeringthesis.database.Retrofit.PicassoClient
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_drag_and_drop.*

class DragAndDropActivity : AppCompatActivity() {
    private var wordViewModel: WordViewModel? = null
    private var shotsCounter = 0
    private lateinit var wordsList : List<Word>
    private lateinit var selectedWord : Word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_and_drop)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordsList =  wordViewModel!!.getWordsFromCategory("Sport",0,4)
        getWordFromList()
        val picasso = PicassoClient.getPiccassoInstance(applicationContext, OkhttpClient)


        dragAndDropShots.setText(shotsCounter.toString())

        dragAndDropFirstField.visibility =  View.VISIBLE
        dragAndDropFirstNameField.setText(wordsList[0].wordName)
        dragAndDropFirstAudio

        dragAndDropSecondField.visibility = View.VISIBLE
        dragAndDropSecondNameField.setText(wordsList[1].wordName)
        dragAndDropSecondAudio

        dragAndDropThirdField.visibility = View.VISIBLE
        dragAndDropThirdNameField.setText(wordsList[2].wordName)
        dragAndDropThirdAudio

        dragAndDropFourField.visibility = View.VISIBLE
        dragAndDropFourNameField.setText(wordsList[3].wordName)
        dragAndDropFourAudio

        picasso.load(wordsList[0].imageId.imageDownloadUri)
                .into(dragAndDropFirstItem)
        dragAndDropFirstItem
        picasso.load(wordsList[1].imageId.imageDownloadUri)
                .into(dragAndDropSecondItem)
        dragAndDropSecondItem
        picasso.load(wordsList[2].imageId.imageDownloadUri)
                .into(dragAndDropThirdItem)
        dragAndDropThirdItem
        picasso.load(wordsList[3].imageId.imageDownloadUri)
                .into(dragAndDropFourItem)
        dragAndDropFourItem
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
}