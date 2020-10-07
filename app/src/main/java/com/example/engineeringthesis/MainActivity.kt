package com.example.engineeringthesis

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : DaggerAppCompatActivity() {

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
                Toast.makeText(applicationContext,"logowanie",Toast.LENGTH_SHORT).show()
            }

            if(menuItem.itemId == R.id.register)
            {
                Toast.makeText(applicationContext,"rejestracja",Toast.LENGTH_SHORT).show()
            }
            true
        }

        select_language_button.setOnClickListener {
            val selectLang = Intent(this@MainActivity, SelectLanguageActivity::class.java)
            startActivity(selectLang)
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
}