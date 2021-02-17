package com.example.engineeringthesis

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.database.Retrofit.OkhttpClient
import com.example.engineeringthesis.database.Retrofit.PicassoClient
import com.example.engineeringthesis.model.CategoryTeacher
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.utils.GlobalValues.pageNumberFOP
import com.example.engineeringthesis.utils.GlobalValues.sizeFOP
import com.example.engineeringthesis.viewmodel.AudioViewModel
import com.example.engineeringthesis.viewmodel.WordViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.android.support.DaggerAppCompatActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_find_out_picture.*
import javax.inject.Inject


class FindOutPictureActivity() : DaggerAppCompatActivity() {
    private var wordViewModel: WordViewModel? = null
    private var audioViewModel: AudioViewModel? = null
    private var shotsCounter = 0
    private lateinit var wordsList: List<Word>
    private var wordsAll: ArrayList<Word> = ArrayList()
    private lateinit var selectedWord: Word
    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private lateinit var savedCategory: CategoryTeacher

    @Inject
    lateinit var sharedPreferences: SharedPreferences;

    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor;

    @Inject
    lateinit var jacksonMapper: ObjectMapper

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_out_picture)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        audioViewModel = ViewModelProvider(this).get(AudioViewModel::class.java)
        getDataFromSharedPref()
        wordsList = wordViewModel!!.getWordsFromCategory(savedCategory.categoryId.categoryName, pageNumberFOP, sizeFOP)
        wordsAll.addAll(wordsList)
       // Toast.makeText(this, wordsAll.size.toString(), Toast.LENGTH_LONG).show()
        getWordFromList()
        // Toast.makeText(this, wordsList[0].imageId.imageDownloadUri, Toast.LENGTH_SHORT).show()

        val picasso = PicassoClient.getPiccassoInstance(applicationContext, OkhttpClient)
        picasso.load(selectedWord.imageId!!.imageDownloadUri)
                .into(findOutPictureImageGame)

        findOutPictureShots.setText(shotsCounter.toString())
        findOutPictureGameTime.start()

        findOutPictureFirstItem.setText(wordsAll[0].wordName)
        findOutPictureFirstItem.setOnClickListener()
        {
            if (selectedWord.wordName.equals(wordsAll[0].wordName)) {

                val intent = Intent(this, WinViewActivity::class.java)
                startActivity(intent)
                saveStatisticToShared()
                resetTimer()
            } else {
                answerAfterClickWord()
                increaseShots()
                findOutPictureShots.setText(shotsCounter.toString())
            }
            //Toast.makeText(this, wordsList[0].wordName, Toast.LENGTH_LONG).show()
        }

        findOutPictureFirstAudio.setOnClickListener {
            //audioViewModel!!.downloadAudio(wordsList[0].audioId!!.audioFileTable.audioFileTableName).body()
            setMediaPlayerConf(wordsAll[0].audioId!!.audioDownloadUri)
            Toast.makeText(this, wordsAll[0].audioId!!.audioFileTable.audioFileTableName, Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Zostal nacisniety przycisk", Toast.LENGTH_SHORT).show()
        }

        findOutPictureSecondItem.setText(wordsAll[1].wordName)
        findOutPictureSecondItem.setOnClickListener()
        {
            if (selectedWord.wordName.equals(wordsAll[1].wordName)) {
                val intent = Intent(this, WinViewActivity::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Trafiles odpowiedz", Toast.LENGTH_SHORT).show()
                saveStatisticToShared()
                resetTimer()
            } else {
                answerAfterClickWord()
                increaseShots()
                findOutPictureShots.setText(shotsCounter.toString())
            }
            // Toast.makeText(this, wordsList[1].wordName, Toast.LENGTH_LONG).show()
        }
        findOutPictureSecondAudio.setOnClickListener()
        {
            setMediaPlayerConf(wordsAll[1].audioId!!.audioDownloadUri)
        }

        findOutPictureThirdItem.setText(wordsList[2].wordName)
        findOutPictureThirdItem.setOnClickListener()
        {
            if (selectedWord.wordName.equals(wordsAll[2].wordName)) {
                val intent = Intent(this, WinViewActivity::class.java)
                startActivity(intent)
                saveStatisticToShared()
                resetTimer()
                //Toast.makeText(this, "Trafiles odpowiedz", Toast.LENGTH_SHORT).show()
            } else {
                answerAfterClickWord()
                increaseShots()
                findOutPictureShots.setText(shotsCounter.toString())
            }
            // Toast.makeText(this, wordsList[2].wordName, Toast.LENGTH_LONG).show()
        }
         findOutPictureThirdAudio.setOnClickListener {
             setMediaPlayerConf(wordsAll[2].audioId!!.audioDownloadUri)
         }

        findOutPictureFourItem.setText(wordsAll[3].wordName)
        findOutPictureFourItem.setOnClickListener()
        {
            if (selectedWord.wordName.equals(wordsAll[3].wordName)) {
                val intent = Intent(this, WinViewActivity::class.java)
                startActivity(intent)
                saveStatisticToShared()
                resetTimer()
                //Toast.makeText(this, "Trafiles odpowiedz", Toast.LENGTH_SHORT).show()
            } else {
                answerAfterClickWord()
                increaseShots()
                findOutPictureShots.setText(shotsCounter.toString())
            }
            // Toast.makeText(this, wordsList[3].wordName, Toast.LENGTH_LONG).show()
        }
        findOutPictureFourAudio.setOnClickListener {
            setMediaPlayerConf(wordsAll[3].audioId!!.audioDownloadUri)
        }
    }

    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Czy chcesz wyjść z gry?")
                .setCancelable(false)
                .setPositiveButton("Tak", DialogInterface.OnClickListener { dialog, id ->
                    super.onBackPressed()
                })
                .setNegativeButton("Nie", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        val alert: AlertDialog? = alertDialogBuilder.create()
        alert!!.show()
    }

    fun getWordFromList() {
        selectedWord = wordsList.random()
    }

    fun increaseShots() {
        shotsCounter++
    }

    fun getDataFromSharedPref() {
        savedCategory = jacksonMapper.readValue(sharedPreferences.getString("category_selected", ""), CategoryTeacher::class.java)
    }

    fun saveStatisticToShared()
    {
        var gameTimeObj = jacksonMapper.writeValueAsString(findOutPictureGameTime.text)
        sharedPreferencesEditor.putString("fop_time",gameTimeObj).apply()
        sharedPreferencesEditor.putString("fop_shots",shotsCounter.toString()).apply()
    }

    fun resetTimer()
    {
        findOutPictureGameTime.setBase(SystemClock.elapsedRealtime());
        findOutPictureGameTime.stop();
    }

    fun setMediaPlayerConf(dataSource: String) {
        mediaPlayer.stop()
        mediaPlayer.reset()
        mediaPlayer.setDataSource(dataSource)
        mediaPlayer.setAudioAttributes(AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
                .build())
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
        }
    }
    fun answerAfterClickWord() {
        Toasty.info(this, "Odpowiedż niepoprawna", Toast.LENGTH_SHORT).show()
    }

}