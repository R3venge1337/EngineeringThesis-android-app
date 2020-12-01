package com.example.engineeringthesis

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.database.Retrofit.OkhttpClient
import com.example.engineeringthesis.database.Retrofit.PicassoClient
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_find_out_vocabulary_actvity.*

class FindOutVocabularyActvity : AppCompatActivity() {

    private var wordViewModel: WordViewModel? = null
    private var shotsCounter = 0
    private lateinit var wordsList : List<Word>
    private lateinit var selectedWord : Word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_out_vocabulary_actvity)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordsList =  wordViewModel!!.getWordsFromCategory("Sport",0,4)
        getWordFromList()
        val picasso = PicassoClient.getPiccassoInstance(applicationContext, OkhttpClient)

        findOutVocabularyWord.setText(selectedWord.wordName)
        findOutPictureFirstAudio.setOnClickListener()
        {
            Toast.makeText(this,"Naciśnieto na przycisk aby odsluchac", Toast.LENGTH_SHORT).show()
        }

        picasso.load(wordsList[0].imageId.imageDownloadUri)
                .into(findOutPictureFirstItem)
        findOutPictureFirstItem.setOnClickListener()
        {
            if(selectedWord.equals(wordsList[0]))
            {
                Toast.makeText(this, "Trafiles odpowiedz", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Odpowiedz nie poprawna", Toast.LENGTH_SHORT).show()
                increaseShots()
                findOutVocabularyShots.setText(shotsCounter.toString())
            }
        }
        picasso.load(wordsList[1].imageId.imageDownloadUri)
                .into(findOutPictureSecondItem)
        findOutPictureSecondItem.setOnClickListener()
        {
            if(selectedWord.imageId.equals(wordsList[1].imageId))
            {
                Toast.makeText(this, "Trafiles odpowiedz", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Odpowiedz nie poprawna", Toast.LENGTH_SHORT).show()
                increaseShots()
                findOutVocabularyShots.setText(shotsCounter.toString())
            }
        }
        picasso.load(wordsList[2].imageId.imageDownloadUri)
                .into(findOutPictureThirdItem)
        findOutPictureThirdItem.setOnClickListener()
        {
            if(selectedWord.imageId.equals(wordsList[2].imageId))
            {
                Toast.makeText(this, "Trafiles odpowiedz", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Odpowiedz nie poprawna", Toast.LENGTH_SHORT).show()
                increaseShots()
                findOutVocabularyShots.setText(shotsCounter.toString())
            }
        }
        picasso.load(wordsList[3].imageId.imageDownloadUri)
                .into(findOutPictureFourItem)
        findOutPictureFourItem.setOnClickListener()
        {
            if(selectedWord.imageId.equals(wordsList[3].imageId))
            {
                Toast.makeText(this, "Trafiles odpowiedz", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Odpowiedz nie poprawna", Toast.LENGTH_SHORT).show()
                increaseShots()
                findOutVocabularyShots.setText(shotsCounter.toString())
            }
        }
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