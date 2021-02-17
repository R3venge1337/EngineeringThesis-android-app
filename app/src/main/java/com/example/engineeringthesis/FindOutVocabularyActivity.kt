package com.example.engineeringthesis

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.database.Retrofit.OkhttpClient
import com.example.engineeringthesis.database.Retrofit.PicassoClient
import com.example.engineeringthesis.model.CategoryTeacher
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.utils.GlobalValues.pageNumberFOV
import com.example.engineeringthesis.utils.GlobalValues.sizeFOV
import com.example.engineeringthesis.viewmodel.WordViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.android.support.DaggerAppCompatActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_find_out_vocabulary.*
import javax.inject.Inject

class FindOutVocabularyActivity : DaggerAppCompatActivity() {

    private var wordViewModel: WordViewModel? = null
    private var shotsCounter = 0
    private lateinit var wordsList : List<Word>
    private lateinit var selectedWord : Word
    private lateinit var savedCategory: CategoryTeacher
    
    @Inject
    lateinit var sharedPreferences: SharedPreferences;
    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor;
    @Inject
    lateinit var jacksonMapper: ObjectMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_out_vocabulary)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        getDataFromSharedPref()
        wordsList =  wordViewModel!!.getWordsFromCategory(savedCategory.categoryId.categoryName,pageNumberFOV,sizeFOV)
        getWordFromList()
        val picasso = PicassoClient.getPiccassoInstance(applicationContext, OkhttpClient)

        findOutVocabularyShots.setText(shotsCounter.toString())
        findOutVocabularyGameTime.start()

        findOutVocabularyWord.setText(selectedWord.wordName)
        findOutVocabularyFirstAudio.setOnClickListener()
        {
            Toast.makeText(this,"Naciśnięto na przycisk, aby odsłuchać", Toast.LENGTH_SHORT).show()
        }

        picasso.load(wordsList[0].imageId!!.imageDownloadUri)
                .into(findOutVocabularyFirstItem)
        findOutVocabularyFirstItem.setOnClickListener()
        {
            if(selectedWord.equals(wordsList[0]))
            {
                val intent = Intent(this, WinViewActivity::class.java)
                startActivity(intent)
                saveStatisticToShared()
            }
            else
            {
                answerAfterClickWord()
                increaseShots()
                findOutVocabularyShots.setText(shotsCounter.toString())
            }
        }
        picasso.load(wordsList[1].imageId!!.imageDownloadUri)
                .into(findOutVocabularySecondItem)
        findOutVocabularySecondItem.setOnClickListener()
        {
            if(selectedWord.imageId!!.equals(wordsList[1].imageId))
            {
                val intent = Intent(this, WinViewActivity::class.java)
                startActivity(intent)
                saveStatisticToShared()
            }
            else
            {
                answerAfterClickWord()
                increaseShots()
                findOutVocabularyShots.setText(shotsCounter.toString())
            }
        }
        picasso.load(wordsList[2].imageId!!.imageDownloadUri)
                .into(findOutVocabularyThirdItem)
        findOutVocabularyThirdItem.setOnClickListener()
        {
            if(selectedWord.imageId!!.equals(wordsList[2].imageId))
            {
                val intent = Intent(this, WinViewActivity::class.java)
                startActivity(intent)
                saveStatisticToShared()
            }
            else
            {
                answerAfterClickWord()
                increaseShots()
                findOutVocabularyShots.setText(shotsCounter.toString())
            }
        }
        picasso.load(wordsList[3].imageId!!.imageDownloadUri)
                .into(findOutVocabularyFourItem)
        findOutVocabularyFourItem.setOnClickListener()
        {
            if(selectedWord.imageId!!.equals(wordsList[3].imageId))
            {
                val intent = Intent(this, WinViewActivity::class.java)
                startActivity(intent)
                saveStatisticToShared()
            }
            else
            {
                answerAfterClickWord()
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

    fun saveStatisticToShared()
    {
        var gameTimeObj = jacksonMapper.writeValueAsString(findOutVocabularyGameTime.text)
        sharedPreferencesEditor.putString("fov_time",gameTimeObj).apply()
        sharedPreferencesEditor.putString("fov_shots",shotsCounter.toString()).apply()
    }

    fun getDataFromSharedPref() {
        savedCategory = jacksonMapper.readValue(sharedPreferences.getString("category_selected", ""), CategoryTeacher::class.java)
    }

    fun answerAfterClickWord() {
        Toasty.info(this, "Odpowiedż niepoprawna", Toast.LENGTH_SHORT).show()
    }


}