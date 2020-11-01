package com.example.engineeringthesis

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.engineeringthesis.utils.UserNameDialog
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigationdrawer.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : DaggerAppCompatActivity(),UserNameDialog.UserNameDialogListener{
    lateinit var userNameDialog : UserNameDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = drawerlayout
        // obsługa przycisku wysuwającego menu
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigation_view.setNavigationItemSelectedListener { menuItem ->
            if(menuItem.itemId == R.id.signup)
            {
                val loginActivity = Intent(this, LoginActivity::class.java)
                startActivity(loginActivity)
            }

            if(menuItem.itemId == R.id.register)
            {
                val registerActivity = Intent(this, RegisterActivitySelector::class.java)
                startActivity(registerActivity)
            }
            true
        }

        select_language_button.setOnClickListener {
            val selectLang = Intent(this@MainActivity, LanguageActivity::class.java)
            startActivity(selectLang)
        }

        openDialog()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu_list, menu)
       return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId)
        {
            R.id.action_sort -> {
                Toast.makeText(applicationContext, "click on setting", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun openDialog()
    {
        userNameDialog = UserNameDialog()
        userNameDialog.show(getSupportFragmentManager(), "Dialog")
    }

    override fun yesClicked(message: String) {
       // val editor = getSharedPreferences(packageName + "_preferences", Context.MODE_PRIVATE).edit().putString("username",userNameDialog.edittext_dialog_username.text.toString())
        //editor.apply()
        //Log.e("Username",userNameDialog.edittext_dialog_username.text.toString())
        Toast.makeText(this, "Nazwa użytkownika = " + message , Toast.LENGTH_LONG).show()
    }

    override fun noClicked(message: String) {
        Toast.makeText(this, "Nazwa użytkownika = " + message, Toast.LENGTH_LONG).show()
    }





}