package com.example.engineeringthesis

import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.viewmodel.WordViewModel
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test

class WordInstrumentalTest {

    private lateinit var wordViewModel: WordViewModel

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.engineeringthesis", appContext.packageName)
    }

    @Test
    fun shouldGetListWordsFromCategory() {
        wordViewModel = WordViewModel(ApplicationProvider.getApplicationContext())
        var word:List<Word>? = wordViewModel.getWordsFromCategory("Sport",0,10)

        if (word == null) {
            MatcherAssert.assertThat(word, Matchers.nullValue())
        }
        MatcherAssert.assertThat(word, Matchers.notNullValue())
    }
}