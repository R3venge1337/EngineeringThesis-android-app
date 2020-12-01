package com.example.engineeringthesis

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.database.Retrofit.OkhttpClient
import com.example.engineeringthesis.database.Retrofit.PicassoClient
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.viewmodel.WordViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_find_out_picture.*


class FindOutPictureActivity : DaggerAppCompatActivity() {
    private var wordViewModel: WordViewModel? = null
    private var shotsCounter = 0
    private lateinit var wordsList : List<Word>
    private lateinit var selectedWord : Word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_out_picture)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordsList =  wordViewModel!!.getWordsFromCategory("Sport",0,4)
        //Toast.makeText(this,Integer.toString(wordsList.size),Toast.LENGTH_LONG).show()
        getWordFromList()
       // Toast.makeText(this, wordsList[0].imageId.imageDownloadUri, Toast.LENGTH_SHORT).show()

        val picasso = PicassoClient.getPiccassoInstance(applicationContext,OkhttpClient)
        picasso.load(selectedWord.imageId.imageDownloadUri)
                .into(findOutPictureImageGame)
        findOutPictureShots.setText(shotsCounter.toString())

        findOutPictureFirstItem.setText(wordsList[0].wordName)
        findOutPictureFirstItem.setOnClickListener()
        {
            if(selectedWord.wordName.equals(wordsList[0].wordName))
            {
                Toast.makeText(this, "Trafiles odpowiedz", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Odpowiedz nie poprawna", Toast.LENGTH_SHORT).show()
                increaseShots()
                findOutPictureShots.setText(shotsCounter.toString())
            }
            //Toast.makeText(this, wordsList[0].wordName, Toast.LENGTH_LONG).show()
        }
        //findOutPictureFirstAudio

        findOutPictureSecondItem.setText(wordsList[1].wordName)
        findOutPictureSecondItem.setOnClickListener()
        {
            if(selectedWord.wordName.equals(wordsList[1].wordName))
            {
                Toast.makeText(this, "Trafiles odpowiedz", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Oppowiedz nie poprawna", Toast.LENGTH_SHORT).show()
                increaseShots()
                findOutPictureShots.setText(shotsCounter.toString())
            }
            // Toast.makeText(this, wordsList[1].wordName, Toast.LENGTH_LONG).show()
        }
        //findOutPictureSecondAudio

        findOutPictureThirdItem.setText(wordsList[2].wordName)
        findOutPictureThirdItem.setOnClickListener()
        {
            if(selectedWord.wordName.equals(wordsList[2].wordName))
            {
                Toast.makeText(this, "Trafiles odpowiedz", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Odpowiedz nie poprawna", Toast.LENGTH_SHORT).show()
                increaseShots()
                findOutPictureShots.setText(shotsCounter.toString())
            }
           // Toast.makeText(this, wordsList[2].wordName, Toast.LENGTH_LONG).show()
        }
       // findOutPictureThirdAudio


        findOutPictureFourItem.setText(wordsList[3].wordName)
        findOutPictureFourItem.setOnClickListener()
        {
            if(selectedWord.wordName.equals(wordsList[3].wordName))
            {
                Toast.makeText(this, "Trafiles odpowiedz", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Odpowiedz nie poprawna", Toast.LENGTH_SHORT).show()
                increaseShots()
                findOutPictureShots.setText(shotsCounter.toString())
            }
           // Toast.makeText(this, wordsList[3].wordName, Toast.LENGTH_LONG).show()
        }
        //findOutPictureFourAudio.
        //dodac dane w odpowiednie pola
        //dodac logike sprawdzajaca
        //dodac logike klikania
        //dodac logike zliczania blednych odpowiedzi
        //dodac czas startowy i czas zakonczenia
        //dodac logike wyjscia z gry
        //dodac logike zapisu stanu gry
        //dodac logike odsluchiwania glosow
        //dodac logike wygenerowania nowego zestawu danych

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