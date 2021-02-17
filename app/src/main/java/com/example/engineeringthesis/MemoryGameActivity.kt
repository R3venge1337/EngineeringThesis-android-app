package com.example.engineeringthesis

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.database.Retrofit.OkhttpClient
import com.example.engineeringthesis.database.Retrofit.PicassoClient
import com.example.engineeringthesis.model.CategoryTeacher
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.utils.GlobalValues.pageNumberMGA
import com.example.engineeringthesis.utils.GlobalValues.sizeMGA
import com.example.engineeringthesis.utils.ImageViewFieldState
import com.example.engineeringthesis.viewmodel.WordViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_memory_game.*
import javax.inject.Inject

class MemoryGameActivity : DaggerAppCompatActivity() {
    private var wordViewModel: WordViewModel? = null
    private var shotsCounter = 0
    private lateinit var wordsList : List<Word>
    private lateinit var wordimages : ArrayList<Word>
    private lateinit var randomWordimages2 : ArrayList<Word>
    private lateinit var objectWithFeatures : ArrayList<ImageViewFieldState>
    private lateinit var buttons: List<ImageView>
    private lateinit var cards: List<ImageViewFieldState>
    private lateinit var picasso : Picasso
    private var indexOfSingleSelectedCard: Int? = null
    private lateinit var savedCategory: CategoryTeacher
    private  var endGame:Boolean = false
    @Inject
    lateinit var sharedPreferences: SharedPreferences;
    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor;
    @Inject
    lateinit var jacksonMapper: ObjectMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_game)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        getDataFromSharedPref()
        //pobranie słów ze zdjeciami
        wordsList = wordViewModel!!.getWordsFromCategory(savedCategory.categoryId.categoryName, pageNumberMGA, sizeMGA)
        picasso = PicassoClient.getPiccassoInstance(applicationContext, OkhttpClient)

        memoryGameShots.setText(shotsCounter.toString())
        memoryGameTime.start()

        buttons = listOf(fieldOne, fieldTwo, fieldThree, fieldFour, fieldFive,
                fieldSix, fieldSeven, fieldEight, fieldNine, fieldTen, fieldEleven, fieldTwelve,
                fieldThirteen, fieldFourteen, fieldFifteen, fieldSixteen)

        getRandomListWords()

        cards = objectWithFeatures

        buttons.forEachIndexed { index, button ->
                button.setOnClickListener {
                    // Update models
                    updateModels(index)
                    // Update the UI for the game
                    updateViews()
                    //Check if game ends
                    endGame()
                }
            }
        }
    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]

            if (card.isMatched) {
                button.alpha = 0.1f
            }
            if (card.isFlipped) {
                if(card.isWordImage == false) {
                    picasso.load(card.Word.imageId!!.imageDownloadUri)
                            .resize(75, 75)
                            .centerCrop()
                            .into(button)
                }
                if(card.isWordImage == true) {
                    picasso.load(card.Word.wordDownloadUri)
                            .resize(75, 75)
                            .centerCrop()
                            .into(button)
                }
            }
            else
            {
                button.setImageResource(R.color.cardview_dark_background)
            }
        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position]
        // Error checking:
        if (card.isFlipped) {
            answerAfterWrongMove()
            return
        }
        // Three cases
        // 0 cards previously flipped over => restore cards + flip over the selected card
        // 1 card previously flipped over => flip over the selected card + check if the images match
        // 2 cards previously flipped over => restore cards + flip over the selected card
        if (indexOfSingleSelectedCard == null) {
            // 0 or 2 selected cards previously
            restoreCards()
            indexOfSingleSelectedCard = position

        } else {
            // exactly 1 card was selected previously
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
            memoryGameShots.setText(shotsCounter.toString())


        }
        card.isFlipped = !card.isFlipped
    }

    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFlipped = false
            }
        }
    }

    private fun checkForMatch(position1: Int, position2: Int) {
        if (cards[position1].Word.wordId == cards[position2].Word.wordId) {
            answerAfterFindPair()
            cards[position1].isMatched = true
            cards[position2].isMatched = true
        }
        else {
            increaseShots()
        }
    }
    fun increaseShots()
    {
        shotsCounter++
    }
    fun getDataFromSharedPref() {
        savedCategory = jacksonMapper.readValue(sharedPreferences.getString("category_selected", ""), CategoryTeacher::class.java)
    }
    fun saveStatisticToShared()
    {
        var gameTimeObj = jacksonMapper.writeValueAsString(memoryGameTime.text)
        sharedPreferencesEditor.putString("mg_time", gameTimeObj).apply()
        sharedPreferencesEditor.putString("mg_shots", shotsCounter.toString()).apply()
    }
    fun endGame() {
        if ((cards[0].isMatched && cards[1].isMatched && cards[2].isMatched && cards[3].isMatched
                && cards[4].isMatched && cards[5].isMatched && cards[6].isMatched
                && cards[7].isMatched && cards[8].isMatched && cards[9].isMatched
                && cards[10].isMatched && cards[11].isMatched && cards[12].isMatched
                && cards[13].isMatched && cards[14].isMatched && cards[15].isMatched)==true
        ) {
            endGame = true
            if (endGame) {
                val intent = Intent(this, WinViewActivity::class.java)
                startActivity(intent)
                saveStatisticToShared()
            }
        }
    }

    fun getRandomListWords()
    {
        wordimages = ArrayList()
        randomWordimages2 = ArrayList()
        objectWithFeatures =  ArrayList()
        wordimages.addAll(wordsList)
        wordimages.addAll(wordsList)

        wordimages.forEachIndexed { index, word ->
            objectWithFeatures.add(ImageViewFieldState(buttons[index], false, wordimages[index]))
        }
       //Toast.makeText(this,  objectWithFeatures.forEach{w-> println(w.idButton)}.toString(),Toast.LENGTH_SHORT).show()
        setIsWordImage()
        objectWithFeatures.shuffle()
        objectWithFeatures.forEachIndexed{ index, w ->
            //println("$index : ${w.Word.wordId}  : ${w.isWordImage} ").toString()
            randomWordimages2.add(w.Word)
        }
    }
    fun setIsWordImage()
    {
        objectWithFeatures.forEachIndexed { index, imageViewFieldState ->
           // println(" index = $index ")
            if(index >= 8 )
            {
                objectWithFeatures[index].isWordImage = true
            }
        }
    }
    fun answerAfterFindPair() {
        Toasty.info(this, "Znaleziono parę", Toast.LENGTH_SHORT).show()
    }

    fun answerAfterWrongMove() {
        Toasty.warning(this, "Nie prawidłowy ruch!", Toast.LENGTH_SHORT).show()
    }
}

