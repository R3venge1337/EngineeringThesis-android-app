package com.example.engineeringthesis

import android.content.Intent
import android.content.SharedPreferences
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
import kotlinx.android.synthetic.main.username_dialog.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(),UserNameDialog.UserNameDialogListener{

    lateinit var userNameDialog : UserNameDialog
    @Inject
    lateinit var sharedPreferences: SharedPreferences;
    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor;
    private var username:String = ""

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
        username = sharedPreferences.getString("username","null").toString()
        if(username.equals(""))
        {
            openDialog()
        }



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
        userNameDialog.show(supportFragmentManager, "Dialog")
    }

    override fun yesClicked(message: String) {
        Toast.makeText(this, "Nazwa użytkownika = " + message , Toast.LENGTH_LONG).show()
        sharedPreferencesEditor.putString("username",message).apply()
    }

    override fun noClicked(message: String) {
        Toast.makeText(this, "Nazwa użytkownika = " + message, Toast.LENGTH_LONG).show()
    }





}