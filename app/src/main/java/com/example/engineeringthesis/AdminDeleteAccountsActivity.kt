package com.example.engineeringthesis

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.adapter.AccountAdapter
import com.example.engineeringthesis.model.Account
import com.example.engineeringthesis.utils.GlobalValues.maxAppAge
import com.example.engineeringthesis.viewmodel.AccountViewModel
import dagger.android.support.DaggerAppCompatActivity

class AdminDeleteAccountsActivity  : DaggerAppCompatActivity() , AccountAdapter.OnAccountItemListener{
    private var accountViewModel: AccountViewModel? = null
    lateinit var recyclerView: RecyclerView
    lateinit var accountAdapter:AccountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_delete_accounts)
        buildRecyclerView()
        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        accountViewModel!!.getAllExpiresAccounts(maxAppAge).observe(this, { accounts ->accountAdapter.setAccountList(accounts)})
    }

    fun buildRecyclerView()
    {
        recyclerView = findViewById(R.id.accountRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        accountAdapter = AccountAdapter(this)
        recyclerView.adapter =  accountAdapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        return
    }

    override fun onChildResultsItemClick(position: Int) {
        var acc: Account = accountAdapter.getItem(position);
        accountViewModel!!.deleteAccount(acc.accountId)

    }
}