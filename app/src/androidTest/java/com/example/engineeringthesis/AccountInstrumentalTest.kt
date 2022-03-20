package com.example.engineeringthesis

import androidx.test.core.app.ApplicationProvider
import com.example.engineeringthesis.model.Account
import com.example.engineeringthesis.viewmodel.AccountViewModel
import com.example.engineeringthesis.viewmodel.RoleViewModel
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import java.time.LocalDateTime

class AccountInstrumentalTest {
    private lateinit var accountViewModel: AccountViewModel
    private lateinit var roleViewModel: RoleViewModel



    @Test
    fun shouldGetAccountByName() {
        accountViewModel = AccountViewModel(ApplicationProvider.getApplicationContext())
        var acc: Account = accountViewModel.getAccountByName("admin")

        if (acc == null) {
            MatcherAssert.assertThat(acc, Matchers.nullValue());
        }

        MatcherAssert.assertThat(acc, Matchers.notNullValue());
    }
    @Test
    fun shouldSaveAccount()
    {
        accountViewModel = AccountViewModel(ApplicationProvider.getApplicationContext())
        roleViewModel = RoleViewModel(ApplicationProvider.getApplicationContext())
        var role = roleViewModel.getRoleByName("nauczyciel")
        var accObj = Account(0,"bizar12","bizar123!Z",
                LocalDateTime.now(),"bizar12@wp.pl",role)
        accountViewModel.saveAccount(accObj)
    }
    @Test
    fun shouldUpdateAccount()
    {
        accountViewModel = AccountViewModel(ApplicationProvider.getApplicationContext())
        var accObj = accountViewModel.getAccountByName("marian98")
        accObj.accountName = "marian112"
        accountViewModel.updateAccount(accObj.accountId,accObj)
    }

    @Test
    fun shouldAuthenticate()
    {
        accountViewModel = AccountViewModel(ApplicationProvider.getApplicationContext())
        accountViewModel.authenticate("admin","admin123!Z")
    }

    @Test
    fun shouldAuthenticateWrongFields()
    {
        accountViewModel = AccountViewModel(ApplicationProvider.getApplicationContext())
        accountViewModel.authenticate("admin22","admin123!Z")
    }


}