package com.example.engineeringthesis

import androidx.test.core.app.ApplicationProvider
import com.example.engineeringthesis.model.Child
import com.example.engineeringthesis.viewmodel.AccountViewModel
import com.example.engineeringthesis.viewmodel.ChildViewModel
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class ChildInstrumentalTest {
    private lateinit var childViewModel: ChildViewModel
    private lateinit var accountViewModel:AccountViewModel

    @Test
    fun shouldGetAccountByName() {
        childViewModel = ChildViewModel(ApplicationProvider.getApplicationContext())
        var child: Child = childViewModel.getChildWithAccountDetails("pawel12")

        if (child == null) {
            MatcherAssert.assertThat(child, Matchers.nullValue());
        }
        MatcherAssert.assertThat(child, Matchers.notNullValue());
    }

    @Test
    fun shouldUpdateChild() {
        childViewModel = ChildViewModel(ApplicationProvider.getApplicationContext())
        var child: Child = childViewModel.getChildWithAccountDetails("pawel12")
        childViewModel.updateChild(child,child.childId)
    }

    /*
    @Test
    fun shouldsaveChild() {
        childViewModel = ChildViewModel(ApplicationProvider.getApplicationContext())
        accountViewModel = AccountViewModel(ApplicationProvider.getApplicationContext())
        var acc = accountViewModel.getAccountByName("marian112")
        var child = Child(0,"Antonio","Banderas",1997,"Poznan",acc)
        childViewModel.saveChild(child)
    }
    */


}