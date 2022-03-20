package com.example.engineeringthesis

import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.engineeringthesis.model.Language
import com.example.engineeringthesis.viewmodel.LanguageViewModel
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime


class LanguageInstrumentedTest {
    private lateinit var langViewModel:LanguageViewModel
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.engineeringthesis", appContext.packageName)
    }

    @Test
    fun shouldGetListLanguages() {
        langViewModel = LanguageViewModel(ApplicationProvider.getApplicationContext())
        var lang:List<Language>? = langViewModel.allLanguages().value

        if (lang != null) {
            assertThat(lang, Matchers.notNullValue());
        }
        assertThat(lang, Matchers.nullValue());
    }
    @Test
    fun shouldGetLanguageByName()
    {
        langViewModel = LanguageViewModel(ApplicationProvider.getApplicationContext())
        var lang:Language? = langViewModel.getLanguageByName("Angielski")

        if (lang == null) {
            assertThat(lang, Matchers.nullValue())
        }
        assertThat(lang, Matchers.notNullValue());
    }

    @Test
    fun shouldGetLanguageById()
    {
        langViewModel = LanguageViewModel(ApplicationProvider.getApplicationContext())
        var lang:Language? = langViewModel.getLanguageById(1)

        if (lang == null) {
            assertThat(lang, Matchers.nullValue())
        }
        assertThat(lang, Matchers.notNullValue());

    }
    @Test
    fun shouldSaveLanguage()
    {
        var langObject:Language = Language(0,"Grecki", LocalDateTime.now(),false,false)
        langViewModel = LanguageViewModel(ApplicationProvider.getApplicationContext())
        langViewModel.saveLanguage(langObject)
    }
}
