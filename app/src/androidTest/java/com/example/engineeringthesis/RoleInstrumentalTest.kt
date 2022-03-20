package com.example.engineeringthesis

import androidx.test.core.app.ApplicationProvider
import com.example.engineeringthesis.viewmodel.RoleViewModel
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Test

class RoleInstrumentalTest {
    private lateinit var roleViewModel: RoleViewModel

    @Test
    fun shouldGetRoleByName()
    {
        roleViewModel = RoleViewModel(ApplicationProvider.getApplicationContext())
        var role  = roleViewModel.getRoleByName("administrator")
        assertThat(role, Matchers.notNullValue())
    }
}